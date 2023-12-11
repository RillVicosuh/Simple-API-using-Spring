package com.pmatcodetest.pmattest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import com.pmatcodetest.pmattest.enums.Gender;
import com.pmatcodetest.pmattest.model.CustomerBasicInfo;
import com.pmatcodetest.pmattest.model.OtherContactInfo;
import com.pmatcodetest.pmattest.repository.ContactInfoRepository;
import com.pmatcodetest.pmattest.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {

  @Autowired
  private CustomerRepository repo;

  @Autowired 
  private ContactInfoRepository repoContact;

  CustomerBasicInfo customer;
  OtherContactInfo contactInfo;

  @Before
  public void setup() {
    // Save customer
    customer = new CustomerBasicInfo();
    contactInfo = new OtherContactInfo();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setEmail("ricardovill77@gmail.com");
    customer.setContactInfo(contactInfo);
    repo.save(customer);
    repoContact.save(contactInfo);
  } 
  
  @Test
  public void testSaveCustomer() {
    // Assert saved to database
    assertThat(repo.findAll()).hasSize(1);
    assertThat(repoContact.findAll()).hasSize(1);
  }

  @Test
  public void testFindById() {
    // Fetch from DB 
    CustomerBasicInfo fetched = repo.findById(customer.getId()).get();

    // Verify contents match
    assertThat(fetched.getFirstName()).isEqualTo("Ricardo");
  }

  @Test
  public void testUpdateCustomer() {
    // Update  
    customer.setLastName("Villa");
    CustomerBasicInfo updated = repo.save(customer);
     
    // Verify updated
    assertThat(updated.getLastName()).isEqualTo("Villa");
  }

  @Test
  public void testDeleteCustomer() {
    // Delete
    repo.deleteById(customer.getId());
    
    // Verify deleted
    CustomerBasicInfo deleted = repo.findById(customer.getId()).orElse(null);
    
    assertThat(deleted).isNull();
  }
}
