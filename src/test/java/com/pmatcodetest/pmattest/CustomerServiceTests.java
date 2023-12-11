package com.pmatcodetest.pmattest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import com.pmatcodetest.pmattest.repository.CustomerRepository;
import com.pmatcodetest.pmattest.service.CustomerService;
import com.pmatcodetest.pmattest.enums.ContactMethod;
import com.pmatcodetest.pmattest.enums.Gender;
import com.pmatcodetest.pmattest.exceptions.InvalidInputException;
import com.pmatcodetest.pmattest.model.CustomerBasicInfo;
import com.pmatcodetest.pmattest.model.OtherContactInfo;
import com.pmatcodetest.pmattest.model.PhoneNumber;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTests {

  @Mock
  private CustomerRepository repo;

  @InjectMocks
  private CustomerService service;

  @Test
  public void testAddInvalidFirstName() {
    // Arrange
    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    customer.setFirstName(""); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setEmail("ricardovill77@gmail.com");
    
    // Assert exception 
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      service.addCustomer(customer, contactInfo);
    });

    String expectedMessage = "First name is required";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testAddInvalidLastName() {
    // Arrange
    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setEmail("ricardovill77@gmail.com");
    
    // Assert exception 
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      service.addCustomer(customer, contactInfo);
    });

    String expectedMessage = "Last name is required";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testAddInvalidEmail() {
    // Arrange
    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setPreferredContactMethod(ContactMethod.EMAIL);
    contactInfo.setEmail("ricardovill77gmail.com");
    
    // Assert exception 
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      service.addCustomer(customer, contactInfo);
    });

    String expectedMessage = "Email must be valid";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testAddInvalidPhone() {
    // Arrange
    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    PhoneNumber phone = new PhoneNumber();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setEmail("ricardovill77@gmail.com");
    contactInfo.setPreferredContactMethod(ContactMethod.PHONE);
    phone.setAreaCode("818");
    phone.setPrefix("421");
    phone.setLineNumber("624");
    contactInfo.setPhone(phone);
    
    // Assert exception 
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      service.addCustomer(customer, contactInfo);
    });

    String expectedMessage = "Phone must be valid";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

}
