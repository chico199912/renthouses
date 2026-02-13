package com.renthouses.asset.repository;

import com.renthouses.asset.domain.Property;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
  List<Property> findByLandlordId(String landlordId);
  List<Property> findByTenantId(String tenantId);
}
