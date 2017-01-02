package com.ishan.cmpe.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name="Phone")
public class Phone {
	@Id
	@Column(name = "phoneid")
	private String id;
	
	@Column(name="number",unique=true)
	private String number;
	
	@Column(name="description")
	private String description;
	
	@Embedded
	private Address address;
	//@JsonManagedReference
	@ManyToMany(mappedBy="phones",fetch = FetchType.EAGER)
	//@JoinTable(name="phone_user", joinColumns={@JoinColumn(name="phoneid")},
	//inverseJoinColumns={@JoinColumn(name="userid")})
    private List<User> users;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
 
	

	
	

}







