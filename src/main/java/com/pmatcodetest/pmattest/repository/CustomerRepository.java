package com.pmatcodetest.pmattest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmatcodetest.pmattest.model.CustomerBasicInfo;

public interface CustomerRepository extends JpaRepository<CustomerBasicInfo, Long> {

}
