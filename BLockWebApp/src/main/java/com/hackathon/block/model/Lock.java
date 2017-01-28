package com.hackathon.block.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String flag;
	
	private String secretKey;
	
	private long location;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public long getLocation() {
		return location;
	}

	public void setLocation(long location) {
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}
