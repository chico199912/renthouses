package com.renthouses.asset.repository;

import com.renthouses.asset.domain.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository extends MongoRepository<Tenant, String> {}
