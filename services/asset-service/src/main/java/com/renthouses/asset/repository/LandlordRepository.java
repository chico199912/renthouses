package com.renthouses.asset.repository;

import com.renthouses.asset.domain.Landlord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LandlordRepository extends MongoRepository<Landlord, String> {}
