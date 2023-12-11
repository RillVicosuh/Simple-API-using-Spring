package com.pmatcodetest.pmattest.controller;

import com.pmatcodetest.pmattest.service.CustomerService;
import com.pmatcodetest.pmattest.exceptions.DuplicateIdException;
import com.pmatcodetest.pmattest.exceptions.InvalidInputException;
import com.pmatcodetest.pmattest.model.CustomerBasicInfo;
import com.pmatcodetest.pmattest.model.OtherContactInfo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/customers")
public class CustomerController{

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> add(@RequestBody CustomerBasicInfo customer, @RequestBody OtherContactInfo contactInfo) {
    try {
      customerService.addCustomer(customer, contactInfo);  
      return ResponseEntity.status(201).build();
    } catch (InvalidInputException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (DuplicateIdException ex) {
      return ResponseEntity.status(409).body(ex.getMessage());
    }
  }
  @PostMapping("/add-no-contact")
  public ResponseEntity<String> add1(@RequestBody CustomerBasicInfo customer) {
    try {
      customerService.addCustomer(customer);  
      return ResponseEntity.status(201).build();
    } catch (InvalidInputException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (DuplicateIdException ex) {
      return ResponseEntity.status(409).body(ex.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerBasicInfo> getCustomer(@PathVariable Long id) {
    CustomerBasicInfo customer = customerService.getCustomer(id);
    return ResponseEntity.ok(customer);
  }

}
