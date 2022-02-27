package com.example.shopping.users;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Contact {
	@Id
	private int id;

	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String zipcode;
	private String province;
	private String city;
	private String phone;
	private String street;

	public Contact() {}
	public Contact(int id, User user, String zipcode, String province, String city, String street, String phone) {
		this.id = id;
		this.user = user;
		this.zipcode = zipcode;
		this.province = province;
		this.city = city;
		this.phone = phone;
		this.street = street;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}
}
