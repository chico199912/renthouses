package com.renthouses.contract.repository;

import com.renthouses.contract.domain.RentalContract;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalContractRepository extends MongoRepository<RentalContract, String> {
  List<RentalContract> findByTenantId(String tenantId);
  List<RentalContract> findByLandlordId(String landlordId);
}
