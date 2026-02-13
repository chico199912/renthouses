package com.renthouses.billing.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rent_installments")
public class RentInstallment {
  @Id
  private String id;
  private String contractId;
  private String landlordEmail;
  private String tenantEmail;
  private String guarantorEmail;
  private String monthRef;
  private BigDecimal amount;
  private LocalDate dueDate;
  private String status;
  private String paymentReference;
  private String paymentChannel;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getContractId() { return contractId; }
  public void setContractId(String contractId) { this.contractId = contractId; }
  public String getLandlordEmail() { return landlordEmail; }
  public void setLandlordEmail(String landlordEmail) { this.landlordEmail = landlordEmail; }
  public String getTenantEmail() { return tenantEmail; }
  public void setTenantEmail(String tenantEmail) { this.tenantEmail = tenantEmail; }
  public String getGuarantorEmail() { return guarantorEmail; }
  public void setGuarantorEmail(String guarantorEmail) { this.guarantorEmail = guarantorEmail; }
  public String getMonthRef() { return monthRef; }
  public void setMonthRef(String monthRef) { this.monthRef = monthRef; }
  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }
  public LocalDate getDueDate() { return dueDate; }
  public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public String getPaymentReference() { return paymentReference; }
  public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }
  public String getPaymentChannel() { return paymentChannel; }
  public void setPaymentChannel(String paymentChannel) { this.paymentChannel = paymentChannel; }
}
