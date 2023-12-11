package com.pmatcodetest.pmattest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmatcodetest.pmattest.enums.ContactMethod;
import com.pmatcodetest.pmattest.exceptions.DuplicateIdException;
import com.pmatcodetest.pmattest.exceptions.InvalidInputException;
import com.pmatcodetest.pmattest.model.CustomerBasicInfo;
import com.pmatcodetest.pmattest.model.OtherContactInfo;
import com.pmatcodetest.pmattest.model.PhoneNumber;
import com.pmatcodetest.pmattest.repository.ContactInfoRepository;
import com.pmatcodetest.pmattest.repository.CustomerRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepo;
  @Autowired
  private ContactInfoRepository contactInfoRepo;

  public void addCustomer(CustomerBasicInfo customer, OtherContactInfo contactInfo) {
    PhoneNumber phone = contactInfo.getPhone();

    // Validate first name
    if(customer.getFirstName() == null || customer.getFirstName().trim().equals("")) {
      throw new InvalidInputException("First name is required");
    }
    // Validate last name
    if(customer.getLastName() == null || customer.getLastName().trim().equals("")) {
      throw new InvalidInputException("Last name is required");  
    }
    // Validate date of birth
    if(customer.getDateOfBirth() == null) {
      throw new InvalidInputException("Date of birth is required");
    }
    // Validate gender
    if(customer.getGender() == null) {
      throw new InvalidInputException("Gender is required");
    }
    // Validate county
    if(customer.getCounty() == null || customer.getCounty().trim().equals("")) {
      throw new InvalidInputException("County is required");
    }
    //Validate Id is not a duplicate
    if(customerRepo.existsById(customer.getId())){
      throw new DuplicateIdException("Duplicate ID found");
    }
    //Validate email
    if(!isValidEmail(contactInfo.getEmail()) && contactInfo.getPreferredContactMethod() == ContactMethod.EMAIL ){
      throw new InvalidInputException("Email must be valid");
    }
    //Validate phone number
    if(!isPhoneValid(phone) && contactInfo.getPreferredContactMethod() == ContactMethod.PHONE){
      throw new InvalidInputException("Phone must be valid");
    }
    
    
    // Save customer if valid
    contactInfoRepo.save(contactInfo);
    customer.setContactInfo(contactInfo);
    customerRepo.save(customer);
  }

  public void addCustomer(CustomerBasicInfo customer) {
    OtherContactInfo contactInfo = customer.getContactInfo();
    PhoneNumber phone = contactInfo.getPhone();

    // Validate first name
    if(customer.getFirstName() == null || customer.getFirstName().trim().equals("")) {
      throw new InvalidInputException("First name is required");
    }
    // Validate last name
    if(customer.getLastName() == null || customer.getLastName().trim().equals("")) {
      throw new InvalidInputException("Last name is required");  
    }
    // Validate date of birth
    if(customer.getDateOfBirth() == null) {
      throw new InvalidInputException("Date of birth is required");
    }
    // Validate gender
    if(customer.getGender() == null) {
      throw new InvalidInputException("Gender is required");
    }
    // Validate county
    if(customer.getCounty() == null || customer.getCounty().trim().equals("")) {
      throw new InvalidInputException("County is required");
    }
    //Validate Id is not a duplicate
    if(customerRepo.existsById(customer.getId())){
      throw new DuplicateIdException("Duplicate ID found");
    }
    //Validate email
    if(!isValidEmail(contactInfo.getEmail()) && contactInfo.getPreferredContactMethod() == ContactMethod.EMAIL ){
      throw new InvalidInputException("Email must be valid");
    }
    //Validate phone number
    if(!isPhoneValid(phone) && contactInfo.getPreferredContactMethod() == ContactMethod.PHONE){
      throw new InvalidInputException("Phone must be valid");
    }
    
    
    // Save customer if valid
    contactInfoRepo.save(contactInfo);
    customerRepo.save(customer);
  }

  /*
    * Define a regex string for the email pattern
    * Compile the regex into a Pattern
    * Get a Matcher for the email input string
    * Call matches() on the matcher to test if it matches the pattern
  */
  public boolean isValidEmail(String email) {
    if(email == null) {
      return false; 
    }

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                          "[a-zA-Z0-9_+&*-]+)*@" +
                          "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                          
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /*
    * Similar to validating email, but defining a regex string for the phone pattern
  */
  public boolean isPhoneValid(PhoneNumber phone) {
    String phoneRegex = "^\\d{3}-\\d{3}-\\d{4}$";
    String phoneString = phone.getAreaCode() + "-" + phone.getPrefix() + "-" + phone.getLineNumber();
    
    Pattern pattern = Pattern.compile(phoneRegex);
    Matcher matcher = pattern.matcher(phoneString);
  
    return matcher.matches();
  }

  //Retrieves a customer by id
  public CustomerBasicInfo getCustomer(Long id) {
    return customerRepo.findById(id).orElseThrow(() -> new InvalidInputException("Customer not found with ID: " + id));
  }
}


