package com.hackathon.block.model;

public class Lock {

	private String id;

	private String flag;

	private String secretKey;

	private String location;

	private String transactionId;

	public Lock(String id, String flag, String secretKey, String location, String transactionId) {
		super();
		this.id = id;
		this.flag = flag;
		this.secretKey = secretKey;
		this.location = location;
		this.transactionId = transactionId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

}
