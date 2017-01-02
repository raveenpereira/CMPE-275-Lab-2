package com.ishan.cmpe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishan.cmpe.model.Address;
import com.ishan.cmpe.model.Phone;
import com.ishan.cmpe.model.RequestClass;
import com.ishan.cmpe.model.User;
import com.ishan.cmpe.service.PhoneService;
import com.ishan.cmpe.service.UserService;

@Controller
public class PhoneController {

	/**
	 * Dependency injection of these class objects.
	 */

	@Autowired
	PhoneService phoneService;
	@Autowired
	UserService userService;
	
	
/**
 * This controller return html page for a phone and return HTTP status 200.
 */

	//Requirement #6
	//Return phone number info by Id HTML
	@RequestMapping(
			value = "/phone/{phoneid}",
			method = RequestMethod.GET)
	public String getPhone(
			@PathVariable("phoneid")  String id,
			Model model) {
		
		
		List<User> notIncludedUsers =  (List<User>) userService.findAll();
		Phone phone = phoneService.findOne(id);

		if(phone == null){
			model.addAttribute("id", id);
			return "error/"+id;
		}
		
		List<User> includedUsers =  phone.getUsers();

		if(includedUsers == null)
			includedUsers = new ArrayList();

		System.out.println("the new Number is "+phone.getUsers().size());
		model.addAttribute("phone", phone);

		for(User user: includedUsers){
			notIncludedUsers.remove(user);
		}

		model.addAttribute("included", includedUsers);
		model.addAttribute("notIncluded", notIncludedUsers);
		System.out.println("Included" +includedUsers.size());
		return "phoneDetails";
	}


	/**
	 * This controller services an AJAX call to add user to a phone.
	 */
	//Service the createPhone to add user
	//6.1
	@RequestMapping(
			value = "/adduser",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String addUser(
			@RequestBody final RequestClass request,
			HttpServletResponse response) {
		if(request != null){
			Phone phone = phoneService.findOne(request.getPhoneId());
			User user = userService.findOne(request.getUserId());
			List<User> phoneUsers =  phone.getUsers();
			List<Phone> userPhones = user.getPhones();
			userPhones.add(phone);
			phoneUsers.add(user);
			phone.setUsers(phoneUsers);
			user.setPhones(userPhones);
			userService.create(user);
			phoneService.create(phone);
			System.out.println("The list has size "+phoneService.findOne(request.getPhoneId()).getUsers().size()+"fucker");
			response.setStatus(HttpStatus.OK.value());
			return phone.getId();
		}
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return "error";
	}
	
	/**
	 * This controller services an AJAX call to delete user to a phone.
	 */

	//Service the createPhone to add user
	//6.1
	@RequestMapping(
			value = "/deleteuser",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public String deleteUser(
			@RequestBody final RequestClass request,
			HttpServletResponse response) {
		if(request != null){
			Phone phone = phoneService.findOne(request.getPhoneId());
			User user = userService.findOne(request.getUserId());
			List<User> phoneUsers =  phone.getUsers();
			List<Phone> userPhones = user.getPhones();

			userPhones.remove(phone);
			phoneUsers.remove(user);
			phone.setUsers(phoneUsers);

			user.setPhones(userPhones);
			userService.create(user);
			phoneService.create(phone);
			
			response.setStatus(HttpStatus.NO_CONTENT.value());
			System.out.println("trying to delete");
			return request.getPhoneId();
		}
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return "error";
	}

	
	
	/**
	 * This controller services any 404 error for phones
	 */
	//Error mapping
		@RequestMapping(
				value = "/error/{id}",
				method = RequestMethod.GET)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public ModelAndView showErrorPage(@PathVariable("id") String id,Model model) {
			return new ModelAndView("error", "id", id );
		}
		


		/**
		 * This controller deletes a phone if there are no users attached to it.
		 */
	//Service the createPhone update button
	//6.1
	@RequestMapping(
			value = "/phone/{phoneId}",
			method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePhone(@PathVariable("phoneId") String phoneId,
			HttpServletResponse response){
		System.out.println("Tried to delete "+phoneId);

		ModelAndView modelAndView = new ModelAndView();
		if(phoneService.findOne(phoneId)==null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return "error";
		}		
		if (phoneService.findOne(phoneId).getUsers().size()==0){
			System.out.println("Tried to delete");
			phoneService.delete(phoneId);
			response.setStatus(HttpStatus.NO_CONTENT.value());
			return "";
		}

		System.out.println("No can do! this thing still has a user");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return "";
	}

	/**
	 * this controller returns a phone along with its users in JSON format
	 */
	//Returns User as JSON
	//Lab02 requirement 7
	@RequestMapping(
			value="/phone/{phoneid}",
			params="json",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<HashMap<String, Object>> getUserX(
			@PathVariable("phoneid") String phoneId,
			@RequestParam(name="json", required=true) boolean json){
		Phone phone=phoneService.findOne(phoneId);
		HashMap<String, Object> toplevelHashMap = new HashMap();
		ArrayList<HashMap> users = new ArrayList();
		HashMap<String, String> user;

		toplevelHashMap.put("id", phone.getId());
		toplevelHashMap.put("number", phone.getNumber());
		toplevelHashMap.put("description", phone.getDescription());
		toplevelHashMap.put("address", phone.getAddress());
		for (int i = 0; i < phone.getUsers().size(); i++) {
			user = new HashMap();
			user.put("id", phone.getUsers().get(i).getId());
			user.put("firstname", phone.getUsers().get(i).getFirstname());
			user.put("lastname", phone.getUsers().get(i).getLastname());
			users.add(user);
		}
		toplevelHashMap.put("users", users);
		return new ResponseEntity(toplevelHashMap, HttpStatus.OK);
	}

	/**
	 * This controller returns a phone creation html page
	 */
	//Lab02 request 8
	//Sending form for creating a new phone
	@RequestMapping(value = "/phone", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("createPhone");
	}


	/**
	 * This controller updates or creates a phone
	 */
	//Lab02 request 4
	//create or update a user by post with Request Params
	@ResponseBody
	@RequestMapping(value = "/phone/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			params={"number", "description", 
					"street", "city", "state", "zip","users[]"})
	public String updateUser(
			@PathVariable("id") String id,
			@RequestParam("number") String number,
			@RequestParam("description") String description,
			@RequestParam("city") String city, 
			@RequestParam("state") String state, 
			@RequestParam("zip") String zip, 
			@RequestParam("street") String street,
			@RequestParam("users[]") String[] userArray,
			HttpServletResponse response) {
		System.out.println("It actually worked!");

		Address address = new Address();
		List<User> users = new ArrayList();
		Phone phone = new Phone();

		phone.setId(id);
		phone.setNumber(number);
		phone.setDescription(description);
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		phone.setAddress(address);
		phoneService.create(phone);
		for (int i=0; i< userArray.length;i++){
			System.out.println("Passed users: "+userArray[i]);
			
			try{
				User user = userService.findOne(userArray[i]);
				if(user == null){
					response.setStatus(HttpStatus.NOT_FOUND.value());
					return "error";
				}
					
				if (user!=null && !phoneService.findOne(id).getUsers().contains(user)){
					users.add(user);
					user.getPhones().add(phone);
					userService.create(user);
					
				}
			}catch(Exception E){
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				E.printStackTrace();
				return "error";
			}
		}
		phone.setUsers(users);

		phoneService.create(phone);
		response.setStatus(HttpStatus.OK.value());
		return phone.getId();
	}

	
	/**
	 * This controller only creates a phone and allocates an auto-generated ID on submission
	 */
	//Lab02 request 4
	//create a user by post with Request Params
	@RequestMapping(value = "/phone",
			method = RequestMethod.POST,
			params={"number", "description", 
					"street", "city", "state", "zip"})
	@ResponseBody
	public String createPhone(
			@RequestParam("number") String number,
			@RequestParam("description") String description,
			@RequestParam("city") String city, 
			@RequestParam("state") String state, 
			@RequestParam("zip") String zip, 
			@RequestParam("street") String street,
			HttpServletResponse response) {
		System.out.println("It Hit Me!");
		String id ="P"+String.valueOf(System.currentTimeMillis()).substring(0, 10);

		Address address = new Address();
		Phone phone = new Phone();

		phone.setId(id);
		phone.setNumber(number);
		phone.setDescription(description);
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		phone.setAddress(address);
		phone.setUsers(new ArrayList<User>());
		phoneService.create(phone);
		System.out.println("It Hit Me2!");
		
		response.setStatus(HttpStatus.CREATED.value());
		return phone.getId();
	}
}

