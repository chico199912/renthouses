package com.renthouses.billing.repository;

import com.renthouses.billing.domain.RentInstallment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentInstallmentRepository extends MongoRepository<RentInstallment, String> {
  List<RentInstallment> findByContractId(String contractId);
  List<RentInstallment> findByTenantEmail(String tenantEmail);
  List<RentInstallment> findByLandlordEmail(String landlordEmail);
  List<RentInstallment> findByStatus(String status);
}
