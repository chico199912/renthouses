package com.renthouses.billing.controller;

import com.renthouses.billing.domain.RentInstallment;
import com.renthouses.billing.service.BillingService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

  private final BillingService billingService;

  public BillingController(BillingService billingService) {
    this.billingService = billingService;
  }

  @PostMapping("/installments")
  public RentInstallment create(@RequestBody RentInstallment installment) {
    return billingService.createInstallment(installment);
  }

  @PostMapping("/installments/{id}/pay")
  public RentInstallment pay(@PathVariable String id, @RequestParam(defaultValue = "MBWAY") String channel) {
    return billingService.pay(id, channel);
  }

  @PostMapping("/jobs/mark-overdue")
  public void markOverdue() {
    billingService.markOverdueAndNotify();
  }

  @GetMapping("/installments")
  public List<RentInstallment> all() { return billingService.listAll(); }

  @GetMapping("/dashboard/tenant/{tenantEmail}")
  public List<RentInstallment> tenantDashboard(@PathVariable String tenantEmail) { return billingService.byTenant(tenantEmail); }

  @GetMapping("/dashboard/landlord/{landlordEmail}")
  public List<RentInstallment> landlordDashboard(@PathVariable String landlordEmail) { return billingService.byLandlord(landlordEmail); }

  @GetMapping("/contracts/{contractId}/installments")
  public List<RentInstallment> byContract(@PathVariable String contractId) { return billingService.byContract(contractId); }
}
