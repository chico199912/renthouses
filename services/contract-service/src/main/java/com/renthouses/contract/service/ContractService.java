package com.renthouses.contract.service;

import com.renthouses.contract.domain.RentalContract;
import com.renthouses.contract.repository.RentalContractRepository;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContractService {

  private final RentalContractRepository repository;
  private final RestTemplate restTemplate;

  @Value("${services.notification.url:http://localhost:8084}")
  private String notificationUrl;

  @Value("${services.billing.url:http://localhost:8083}")
  private String billingUrl;

  public ContractService(RentalContractRepository repository, RestTemplate restTemplate) {
    this.repository = repository;
    this.restTemplate = restTemplate;
  }

  public RentalContract createContract(RentalContract contract) {
    contract.setStatus("AWAITING_SIGNATURE");
    contract.setContractPdfContent(generatePdf("Contrato de arrendamento", contract));
    RentalContract saved = repository.save(contract);

    notifyContract(saved);
    createInitialCharges(saved);
    return saved;
  }

  public RentalContract terminateContract(String id) {
    RentalContract contract = repository.findById(id).orElseThrow();
    contract.setStatus("TERMINATED");
    contract.setTerminationPdfContent(generatePdf("Termo de rescisão", contract));
    RentalContract saved = repository.save(contract);

    Map<String, String> payload = Map.of(
        "recipient", saved.getLandlordEmail(),
        "subject", "Contrato terminado",
        "message", "Foi gerado termo de rescisão para assinatura do inquilino. Envio por email/correio registado disponível.");
    restTemplate.postForEntity(notificationUrl + "/api/notifications/email", payload, Map.class);
    return saved;
  }

  public List<RentalContract> listAll() { return repository.findAll(); }

  private void notifyContract(RentalContract contract) {
    Map<String, String> emailTenant = Map.of(
        "recipient", contract.getTenantEmail(),
        "subject", "Contrato para assinatura",
        "message", "Contrato gerado e pronto para assinatura.");
    restTemplate.postForEntity(notificationUrl + "/api/notifications/email", emailTenant, Map.class);

    if (contract.getGuarantorEmail() != null && !contract.getGuarantorEmail().isBlank()) {
      Map<String, String> emailGuarantor = Map.of(
          "recipient", contract.getGuarantorEmail(),
          "subject", "Contrato com fiador para assinatura",
          "message", "Recebeu contrato onde consta como fiador. Favor assinar.");
      restTemplate.postForEntity(notificationUrl + "/api/notifications/email", emailGuarantor, Map.class);
    }

    Map<String, String> smsTenant = Map.of(
        "recipient", contract.getTenantEmail(),
        "subject", "Assinar contrato",
        "message", "Verifique o email e assine o contrato de arrendamento.");
    restTemplate.postForEntity(notificationUrl + "/api/notifications/sms", smsTenant, Map.class);
  }

  private void createInitialCharges(RentalContract contract) {
    LocalDate now = LocalDate.now();
    Map<String, Object> advance = Map.of(
        "contractId", contract.getId(),
        "landlordEmail", contract.getLandlordEmail(),
        "tenantEmail", contract.getTenantEmail(),
        "guarantorEmail", contract.getGuarantorEmail() == null ? "" : contract.getGuarantorEmail(),
        "monthRef", "ADVANCE",
        "amount", contract.getAdvanceRent(),
        "dueDate", now.plusDays(5));
    restTemplate.postForEntity(billingUrl + "/api/billing/installments", advance, Map.class);

    Map<String, Object> deposit = Map.of(
        "contractId", contract.getId(),
        "landlordEmail", contract.getLandlordEmail(),
        "tenantEmail", contract.getTenantEmail(),
        "guarantorEmail", contract.getGuarantorEmail() == null ? "" : contract.getGuarantorEmail(),
        "monthRef", "DEPOSIT",
        "amount", contract.getSecurityDeposit(),
        "dueDate", now.plusDays(5));
    restTemplate.postForEntity(billingUrl + "/api/billing/installments", deposit, Map.class);
  }

  private String generatePdf(String title, RentalContract contract) {
    String payload = title + "\nLandlord=" + contract.getLandlordId() + " Tenant=" + contract.getTenantId() +
        " Rent=" + contract.getMonthlyRent() + " Clauses=" + String.join(";", contract.getClauses());
    return Base64.getEncoder().encodeToString(payload.getBytes());
  }
}
