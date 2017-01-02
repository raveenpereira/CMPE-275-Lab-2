package com.ishan.cmpe.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.ishan.cmpe.model.User;
import com.ishan.cmpe.repository.UserRepository;

@Service
public class  UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(
	        propagation = Propagation.SUPPORTS,
	        readOnly=true)
	public Collection<User> findAll() {
		Collection<User> users = userRepository.findAll();
		return users;
	}

	@Override
	@Transactional(
	        propagation = Propagation.SUPPORTS,
	        readOnly=true)
	public User findOne(String id) {
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public User create(User user) {		
		User savedUser = userRepository.save(user);		
		return user;
	}

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public User update(User user) {
		User savedUser = userRepository.findOne(user.getId());
		if(savedUser == null)
			return null;
		
		return savedUser;
	}

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public boolean delete(String id) {
		userRepository.delete(id);
		return true;
	}
}
