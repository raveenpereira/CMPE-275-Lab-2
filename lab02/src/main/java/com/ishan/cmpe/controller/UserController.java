package com.ishan.cmpe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishan.cmpe.model.Address;
import com.ishan.cmpe.model.Phone;
import com.ishan.cmpe.model.RequestClass;
import com.ishan.cmpe.model.User;
import com.ishan.cmpe.service.PhoneService;
import com.ishan.cmpe.service.UserService;

@Controller
public class UserController {

	/**
	 * Dependency injection of these class objects.
	 */

	@Autowired
	PhoneService phoneService;

	@Autowired
	UserService userService;



	/**
	 * returns an HTML page of a user's info
	 */

	//Lab02 requirement 1
	//Returns a user as HTML
	@RequestMapping(
			value = "/user/{userId}",
			method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getUser(@PathVariable("userId")  String id) {
		User user = userService.findOne(id);
		
		if(user == null){
			ModelMap model = new ModelMap();
			model.addAttribute("id", id);
			model.addAttribute("message", "User not found");
			return new ModelAndView("error","id", id);
		}
		ModelAndView modelAndView = new ModelAndView("userDetails", "user", user);
		//modelAndView.addObject("listOfPhones",user.getPhones());
		return modelAndView;

	}
	

	
	/**
	 * Returns a user as JSON
	 */ 
	
	//Returns User as JSON
	//Lab02 requirement 2
	@JsonIgnore
	@RequestMapping(
			value="/user/{userid}",
			params="json",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<HashMap<String, Object>> getUserX(
			@PathVariable("userid") String userId,
			@RequestParam(name="json", required=true) boolean json){
		if(json){
			System.out.println("Yup, json param is true");
			User user = userService.findOne(userId);
			if(user == null){
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			HashMap<String,Object> topLevelHashMap = new HashMap();
			ArrayList users = new ArrayList();
			topLevelHashMap.put("id", user.getId());
			topLevelHashMap.put("firstname", user.getFirstname());
			topLevelHashMap.put("lastname", user.getLastname());
			topLevelHashMap.put("title", user.getTitle());
			topLevelHashMap.put("address", user.getAddress());
			for(int i=0;i<user.getPhones().size();i++){
				HashMap<String,Object> phone = new HashMap();
				phone.put("id", user.getPhones().get(i).getId());
				phone.put("number", user.getPhones().get(i).getNumber());
				phone.put("description", user.getPhones().get(i).getDescription());
				users.add(phone);
			}
			topLevelHashMap.put("phones", users);
			return new ResponseEntity(topLevelHashMap, HttpStatus.OK);	

		}else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	
	/**
	 * Returns a user creation form
	 */

	//Lab02 requirement 3
	//Sending HTML Form for creating a new user
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView showUserCreationForm() {
		System.out.println("sending the create user form over");
		return new ModelAndView("createUser");
	}

	
	/**
	 *Controller services user creation and auto-generates an ID
	 */
	//Lab02 request 4
	//create a user by post with Request Params
	@RequestMapping(value = "/user",
			method = RequestMethod.POST,
			params={"firstname", "lastname", "title", 
					"street", "city", "state", "zip"})
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String createUser(
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("title") String title, 
			@RequestParam("city") String city, 
			@RequestParam("state") String state, 
			@RequestParam("zip") String zip, 
			@RequestParam("street") String street, 
			Model model) {
		//Generating a unique userId
		String Id = String.valueOf(System.currentTimeMillis()).substring(0, 10);
		User user = new User();
		System.out.println("creating a new user!");
		user.setId(Id);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setTitle(title);
		Address address=new Address();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		user.setAddress(address);
		userService.create(user);	
		return user.getId();
	}

	/**
	 * Controller creates or updates a user
	 */
	//Lab02 request 4
	//create or update a user by post with Request Params
	@RequestMapping(value = "/user/{id}",
			method = RequestMethod.POST,
			params={"firstname", "lastname", "title", 
					"street", "city", "state", "zip"})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String updateUser(
			@Valid @PathVariable("id") String id,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("title") String title, 
			@RequestParam("city") String city, 
			@RequestParam("state") String state, 
			@RequestParam("zip") String zip, 
			@RequestParam("street") String street) {
		User user=userService.findOne(id);
		if(user == null){
			user = new User();
			user.setId(id);
		}
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setTitle(title);
		Address address=new Address();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		user.setAddress(address);
		userService.create(user);	
		return user.getId();
	}


	private ModelAndView modelAndView(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This controller services deletion of a user
	 */

	//Lab02 requirement 5
	//Delete user when response is sent from the form/API request
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteUser(@Valid @PathVariable("id") String id,
			HttpServletResponse response) throws Exception {
		System.out.println("It actually worked!");
		
		if(userService.findOne(id)==null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return "error";
		}
		
		userService.delete(id);
		response.setStatus(HttpStatus.NO_CONTENT.value());
		return "no error";
	}

}
