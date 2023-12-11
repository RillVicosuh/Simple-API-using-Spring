package com.pmatcodetest.pmattest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.pmatcodetest.pmattest.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Table(name = "customers")
public class CustomerBasicInfo {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min=2)
  @Column(name = "firstName")
  private String firstName;

  @Column(name = "middleName")
  private String middleName;

  @NotNull
  @Size(min=2) 
  @Column(name = "lastName")
  private String lastName;

  @NotNull
  @Column(name = "dateOfBirth")
  private LocalDate dateOfBirth;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender;

  @NotNull
  @Column(name = "county")
  private String county;

  @Column(name = "fixedAddress")
  private boolean fixedAddress;

  @Column(name = "assistanceWithInsurance")
  private boolean assistanceWithInsurance;

  @Column(name = "familyPlanningBenefits")
  private boolean familyPlanningBenefits;

  @JdbcTypeCode(SqlTypes.JSON)
  @OneToOne
  @PrimaryKeyJoinColumn()
  private OtherContactInfo contactInfo;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName; 
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName; 
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public boolean isFixedAddress() {
    return fixedAddress;
  }

  public void setFixedAddress(boolean fixedAddress) {
    this.fixedAddress = fixedAddress;
  }

  public boolean isAssistanceWithInsurance() {
    return assistanceWithInsurance;
  }

  public void setAssistanceWithInsurance(boolean assistanceWithInsurance) {
    this.assistanceWithInsurance = assistanceWithInsurance;
  }

  public boolean isFamilyPlanningBenefits() {
    return familyPlanningBenefits;
  }

  public void setFamilyPlanningBenefits(boolean familyPlanningBenefits) {
    this.familyPlanningBenefits = familyPlanningBenefits;
  }

  public OtherContactInfo getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(OtherContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

}
