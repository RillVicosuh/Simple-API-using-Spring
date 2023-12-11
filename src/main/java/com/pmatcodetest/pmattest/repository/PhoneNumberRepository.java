package com.pmatcodetest.pmattest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmatcodetest.pmattest.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

}