package com.renthouses.billing.service;

import com.renthouses.billing.domain.RentInstallment;
import com.renthouses.billing.repository.RentInstallmentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BillingService {

  private final RentInstallmentRepository repository;
  private final RestTemplate restTemplate;

  @Value("${services.notification.url:http://localhost:8084}")
  private String notificationUrl;

  public BillingService(RentInstallmentRepository repository, RestTemplate restTemplate) {
    this.repository = repository;
    this.restTemplate = restTemplate;
  }

  public RentInstallment createInstallment(RentInstallment installment) {
    installment.setStatus("PENDING");
    installment.setPaymentReference("REF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
    return repository.save(installment);
  }

  public RentInstallment pay(String installmentId, String channel) {
    RentInstallment installment = repository.findById(installmentId).orElseThrow();
    installment.setStatus("PAID");
    installment.setPaymentChannel(channel);
    RentInstallment saved = repository.save(installment);

    notify(saved.getLandlordEmail(), "Renda paga", "O inquilino pagou a renda " + saved.getMonthRef() + ".");
    return saved;
  }

  public void markOverdueAndNotify() {
    List<RentInstallment> pending = repository.findByStatus("PENDING");
    for (RentInstallment installment : pending) {
      if (installment.getDueDate() != null && installment.getDueDate().isBefore(LocalDate.now())) {
        installment.setStatus("OVERDUE");
        repository.save(installment);
        notify(installment.getTenantEmail(), "Renda em atraso", "Tem valores em dívida no contrato " + installment.getContractId());
        notify(installment.getLandlordEmail(), "Inquilino em atraso", "O inquilino foi notificado do atraso.");
        if (installment.getGuarantorEmail() != null && !installment.getGuarantorEmail().isBlank()) {
          notify(installment.getGuarantorEmail(), "Contrato com atraso", "O contrato afiançado está com rendas em atraso.");
        }
      }
    }
  }

  public List<RentInstallment> listAll() { return repository.findAll(); }
  public List<RentInstallment> byTenant(String tenantEmail) { return repository.findByTenantEmail(tenantEmail); }
  public List<RentInstallment> byLandlord(String landlordEmail) { return repository.findByLandlordEmail(landlordEmail); }
  public List<RentInstallment> byContract(String contractId) { return repository.findByContractId(contractId); }

  private void notify(String recipient, String subject, String message) {
    if (recipient == null || recipient.isBlank()) return;
    Map<String, String> payload = Map.of("recipient", recipient, "subject", subject, "message", message);
    restTemplate.postForEntity(notificationUrl + "/api/notifications/email", payload, Map.class);
    restTemplate.postForEntity(notificationUrl + "/api/notifications/sms", payload, Map.class);
  }
}
