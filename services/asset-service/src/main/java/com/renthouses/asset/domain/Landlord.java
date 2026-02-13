package com.renthouses.asset.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("landlords")
public class Landlord {
  @Id
  private String id;
  private String name;
  private String email;
  private String phone;
  private List<String> propertyIds = new ArrayList<>();

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public List<String> getPropertyIds() { return propertyIds; }
  public void setPropertyIds(List<String> propertyIds) { this.propertyIds = propertyIds; }
}
