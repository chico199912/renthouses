package com.renthouses.asset.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("properties")
public class Property {
  @Id
  private String id;
  private String landlordId;
  private String tenantId;
  private String address;
  private String city;
  private String postalCode;
  private int rooms;
  private boolean rented;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getLandlordId() { return landlordId; }
  public void setLandlordId(String landlordId) { this.landlordId = landlordId; }
  public String getTenantId() { return tenantId; }
  public void setTenantId(String tenantId) { this.tenantId = tenantId; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
  public int getRooms() { return rooms; }
  public void setRooms(int rooms) { this.rooms = rooms; }
  public boolean isRented() { return rented; }
  public void setRented(boolean rented) { this.rented = rented; }
}
