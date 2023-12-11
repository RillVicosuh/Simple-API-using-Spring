package com.pmatcodetest.pmattest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.Month;
import com.pmatcodetest.pmattest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pmatcodetest.pmattest.controller.CustomerController;
import com.pmatcodetest.pmattest.enums.ContactMethod;
import com.pmatcodetest.pmattest.enums.Gender;
import com.pmatcodetest.pmattest.model.CustomerBasicInfo;
import com.pmatcodetest.pmattest.model.OtherContactInfo;
import com.pmatcodetest.pmattest.model.PhoneNumber;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTests {

  @Mock
  private CustomerService service;

  @InjectMocks 
  private CustomerController controller;

  @Test
  public void testAddCustomer() throws Exception {

    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    PhoneNumber phone = new PhoneNumber();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setPreferredContactMethod(ContactMethod.EMAIL);
    contactInfo.setEmail("ricardovill77@gmail.com");
    customer.setContactInfo(contactInfo);
    phone.setAreaCode("818");
    phone.setPrefix("421");
    phone.setLineNumber("6241");
    contactInfo.setPhone(phone);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    String customerJson = mapper.writeValueAsString(customer);

  // Act
  MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/add-no-contact")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(customerJson);

  MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  
  // Assert
  mockMvc.perform(request)
         .andExpect(status().isCreated());
  }

  @Test
  public void testAddCustomer2() throws Exception {

    CustomerBasicInfo customer = new CustomerBasicInfo();
    OtherContactInfo contactInfo = new OtherContactInfo();
    PhoneNumber phone = new PhoneNumber();
    customer.setFirstName("Ricardo"); 
    customer.setLastName("Villacana");
    customer.setDateOfBirth(LocalDate.of(2000, Month.MARCH, 9));
    customer.setGender(Gender.MALE);
    customer.setCounty("San Diego");
    contactInfo.setPreferredContactMethod(ContactMethod.EMAIL);
    contactInfo.setEmail("ricardovill77@gmail.com");
    phone.setAreaCode("818");
    phone.setPrefix("421");
    phone.setLineNumber("6241");
    contactInfo.setPhone(phone);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    String customerJson = mapper.writeValueAsString(customer);
    String contactJson = mapper.writeValueAsString(contactInfo);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/add-no-contact")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(customerJson)
                                        .content(contactJson);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  
    mockMvc.perform(request)
         .andExpect(status().isCreated());
  }

}