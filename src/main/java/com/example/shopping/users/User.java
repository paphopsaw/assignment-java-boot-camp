package com.example.shopping.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User{
	@Id
	private int id;

	private String name;

	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Contact contact;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CartItem> cart;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;

	public User() {
		this.contact = new Contact();
		this.cart = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.contact = new Contact();
		this.cart = new ArrayList<>();
		this.orders = new ArrayList<>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}