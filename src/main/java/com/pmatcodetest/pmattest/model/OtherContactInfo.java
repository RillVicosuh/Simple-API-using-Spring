package com.pmatcodetest.pmattest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.pmatcodetest.pmattest.enums.ContactMethod;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "contactInfos")
public class OtherContactInfo {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "preferredContactMethod")
  private ContactMethod preferredContactMethod;

  @JdbcTypeCode(SqlTypes.JSON)
  @OneToOne
  @PrimaryKeyJoinColumn()
  private PhoneNumber phone;

  @JdbcTypeCode(SqlTypes.JSON)
  @OneToOne
  @PrimaryKeyJoinColumn()
  private PhoneNumber alternatePhone; 

  @Column(name = "email")
  private String email;

  @Column(name = "preferredLanguage")
  private String preferredLanguage;

  //@OneToOne
  @OneToOne  
  @JoinColumn(name = "customer_id", unique = true) 
  private CustomerBasicInfo customer;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ContactMethod getPreferredContactMethod() {
    return preferredContactMethod;
  }

  public void setPreferredContactMethod(ContactMethod preferredContactMethod) {
    this.preferredContactMethod = preferredContactMethod;
  }

  public PhoneNumber getPhone() {
    return phone;
  }

  public void setPhone(PhoneNumber phone) {
    this.phone = phone;
  }

  public PhoneNumber getAlternatePhone() {
    return alternatePhone;
  }

  public void setAlternatePhone(PhoneNumber alternatePhone) {
    this.alternatePhone = alternatePhone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage(String preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }
 
}
