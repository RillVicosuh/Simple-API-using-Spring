package com.pmatcodetest.pmattest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmatcodetest.pmattest.model.OtherContactInfo;

public interface ContactInfoRepository extends JpaRepository<OtherContactInfo, Long> {

}
