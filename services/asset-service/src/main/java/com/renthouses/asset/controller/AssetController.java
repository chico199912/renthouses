package com.renthouses.asset.controller;

import com.renthouses.asset.domain.Landlord;
import com.renthouses.asset.domain.Property;
import com.renthouses.asset.domain.Tenant;
import com.renthouses.asset.repository.LandlordRepository;
import com.renthouses.asset.repository.PropertyRepository;
import com.renthouses.asset.repository.TenantRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

  private final LandlordRepository landlordRepository;
  private final TenantRepository tenantRepository;
  private final PropertyRepository propertyRepository;

  public AssetController(LandlordRepository landlordRepository, TenantRepository tenantRepository, PropertyRepository propertyRepository) {
    this.landlordRepository = landlordRepository;
    this.tenantRepository = tenantRepository;
    this.propertyRepository = propertyRepository;
  }

  @PostMapping("/landlords")
  public Landlord createLandlord(@RequestBody Landlord landlord) { return landlordRepository.save(landlord); }

  @GetMapping("/landlords")
  public List<Landlord> landlords() { return landlordRepository.findAll(); }

  @PostMapping("/tenants")
  public Tenant createTenant(@RequestBody Tenant tenant) { return tenantRepository.save(tenant); }

  @GetMapping("/tenants")
  public List<Tenant> tenants() { return tenantRepository.findAll(); }

  @PostMapping("/properties")
  public Property createProperty(@RequestBody Property property) { return propertyRepository.save(property); }

  @GetMapping("/properties")
  public List<Property> properties() { return propertyRepository.findAll(); }

  @GetMapping("/landlords/{landlordId}/properties")
  public List<Property> propertiesByLandlord(@PathVariable String landlordId) { return propertyRepository.findByLandlordId(landlordId); }

  @GetMapping("/tenants/{tenantId}/properties")
  public List<Property> propertiesByTenant(@PathVariable String tenantId) { return propertyRepository.findByTenantId(tenantId); }
}
