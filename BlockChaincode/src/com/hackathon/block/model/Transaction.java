package com.hackathon.block.model;

import java.util.List;

public class Transaction {

	private String id;

	private String state;

	private String lockId;

	private List<String> usersId;

	public Transaction(String id, String state, String lockId, List<String> usersId) {
		super();
		this.id = id;
		this.state = state;
		this.lockId = lockId;
		this.usersId = usersId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public List<String> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<String> usersId) {
		this.usersId = usersId;
	}
}
