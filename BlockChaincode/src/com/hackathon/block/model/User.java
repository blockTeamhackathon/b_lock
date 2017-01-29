package com.hackathon.block.model;

import java.util.List;

public class User {

	private String id;

	private String name;

	private String role;

	private List<String> transactions;

	public User(String id, String name, String role, List<String> transactions) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.transactions = transactions;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}

	public String getRole() {
		return role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
