package com.ishan.cmpe.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ishan.cmpe.model.Phone;
import com.ishan.cmpe.model.User;
import com.ishan.cmpe.repository.PhoneRepository;
import com.ishan.cmpe.repository.UserRepository;

@Service
public class PhoneServiceImpl implements PhoneService{


	@Autowired
	PhoneRepository phoneRepository;

	@Override
	@Transactional(
	        propagation = Propagation.SUPPORTS,
	        readOnly=true)
	public Collection<Phone> findAll() {
		Collection<Phone> phones = phoneRepository.findAll();
		if(phones == null)
			return null;
		return phones;
	}

	@Override
	@Transactional(
	        propagation = Propagation.REQUIRED,
	        readOnly=true)
	public Phone findOne(String id) {
		Phone phone = phoneRepository.findOne(id);
		if(phone == null)
			return null;
		return phone;
	}

	@Override
	@Transactional(
	        propagation = Propagation.REQUIRED,
	        readOnly=false)
	public Phone create(Phone phone) {
		phone = phoneRepository.save(phone);
		return phone;
	}

	@Override
	@Transactional(
	        propagation = Propagation.REQUIRED,
	        readOnly=false)
	public Phone update(Phone phone) {
		phone = phoneRepository.save(phone);
		return phone;
	}

	@Override
	@Transactional(
	        propagation = Propagation.REQUIRED,
	        readOnly=false)
	public boolean delete(String id) {
		phoneRepository.delete(id);
		return true;
	}
	
}
