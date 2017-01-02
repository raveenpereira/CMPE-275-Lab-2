package com.ishan.cmpe.service;

import java.util.Collection;

import com.ishan.cmpe.model.Phone;

public interface PhoneService {
	
	Collection<Phone> findAll();
	
	Phone findOne(String id);
	
	Phone create(Phone phone);
	
	Phone update(Phone phone);
	
	boolean delete(String id);
}
