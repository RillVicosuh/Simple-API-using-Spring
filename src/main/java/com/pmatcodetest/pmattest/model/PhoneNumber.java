package com.pmatcodetest.pmattest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "phoneNumbers")
public class PhoneNumber {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="areaCcode")
  private String areaCode;

  @Column(name="prefix")  
  private String prefix;

  @Column(name="lineNumber")  
  private String lineNumber;
   
  @Enumerated(EnumType.STRING)
  @Column(name="phoneType")
  private PhoneType type;

  @OneToOne  
  @JoinColumn(name = "contactInfo_id", unique = true) 
  private OtherContactInfo contactInfo;

  
  // Getters and setters 
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(String lineNumber) {
    this.lineNumber = lineNumber;
  }

  public PhoneType getPhoneType() {
    return type;
  }

  public void setPhoneType(PhoneType type) {
    this.type = type;
  }


}

enum PhoneType {
  MOBILE, HOME, WORK
}
