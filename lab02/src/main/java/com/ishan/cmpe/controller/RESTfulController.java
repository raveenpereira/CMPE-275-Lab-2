package com.ishan.cmpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.Valid;

import com.ishan.cmpe.model.Address;
import com.ishan.cmpe.model.Phone;
import com.ishan.cmpe.model.User;
import com.ishan.cmpe.service.UserService;


/**
 * Class used for learning purposes.
 */
@RestController
public class RESTfulController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(
			value = "/api/users", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> users = userService.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(
			value = "/api/users", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUser(@RequestBody User user){
			User savedUser = userService.create(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}


	@RequestMapping(
			value = "/api/users", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User savedUser = userService.update(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	
}
