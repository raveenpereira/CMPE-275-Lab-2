package com.ishan.cmpe.service;

import java.util.Collection;

import com.ishan.cmpe.model.User;

public interface UserService {

	Collection<User> findAll();
	
	User findOne(String id);
	
	User create(User user);
	
	User update(User user);
	
	boolean delete(String id);
	
}
