package com.renthouses.contract.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rental_contracts")
public class RentalContract {
  @Id
  private String id;
  private String landlordId;
  private String landlordEmail;
  private String tenantId;
  private String tenantEmail;
  private String guarantorName;
  private String guarantorEmail;
  private String propertyId;
  private BigDecimal monthlyRent;
  private BigDecimal advanceRent;
  private BigDecimal securityDeposit;
  private int dueDay;
  private LocalDate startDate;
  private LocalDate endDate;
  private String status;
  private List<String> clauses = new ArrayList<>();
  private String contractPdfContent;
  private String terminationPdfContent;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getLandlordId() { return landlordId; }
  public void setLandlordId(String landlordId) { this.landlordId = landlordId; }
  public String getLandlordEmail() { return landlordEmail; }
  public void setLandlordEmail(String landlordEmail) { this.landlordEmail = landlordEmail; }
  public String getTenantId() { return tenantId; }
  public void setTenantId(String tenantId) { this.tenantId = tenantId; }
  public String getTenantEmail() { return tenantEmail; }
  public void setTenantEmail(String tenantEmail) { this.tenantEmail = tenantEmail; }
  public String getGuarantorName() { return guarantorName; }
  public void setGuarantorName(String guarantorName) { this.guarantorName = guarantorName; }
  public String getGuarantorEmail() { return guarantorEmail; }
  public void setGuarantorEmail(String guarantorEmail) { this.guarantorEmail = guarantorEmail; }
  public String getPropertyId() { return propertyId; }
  public void setPropertyId(String propertyId) { this.propertyId = propertyId; }
  public BigDecimal getMonthlyRent() { return monthlyRent; }
  public void setMonthlyRent(BigDecimal monthlyRent) { this.monthlyRent = monthlyRent; }
  public BigDecimal getAdvanceRent() { return advanceRent; }
  public void setAdvanceRent(BigDecimal advanceRent) { this.advanceRent = advanceRent; }
  public BigDecimal getSecurityDeposit() { return securityDeposit; }
  public void setSecurityDeposit(BigDecimal securityDeposit) { this.securityDeposit = securityDeposit; }
  public int getDueDay() { return dueDay; }
  public void setDueDay(int dueDay) { this.dueDay = dueDay; }
  public LocalDate getStartDate() { return startDate; }
  public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
  public LocalDate getEndDate() { return endDate; }
  public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public List<String> getClauses() { return clauses; }
  public void setClauses(List<String> clauses) { this.clauses = clauses; }
  public String getContractPdfContent() { return contractPdfContent; }
  public void setContractPdfContent(String contractPdfContent) { this.contractPdfContent = contractPdfContent; }
  public String getTerminationPdfContent() { return terminationPdfContent; }
  public void setTerminationPdfContent(String terminationPdfContent) { this.terminationPdfContent = terminationPdfContent; }
}
