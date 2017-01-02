package com.ishan.cmpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ishan.cmpe.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,String> {

}
