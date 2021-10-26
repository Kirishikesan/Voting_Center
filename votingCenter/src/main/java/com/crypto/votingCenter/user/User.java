package com.crypto.votingCenter.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique=true)
	private String deviceID;
	@Column(unique=true)
	private String randomID;
	@Column(nullable = false,unique=true)
	private String email;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
		
	}
	
	public User(String id, String name, String deviceID, String randomID) {
		super();
		this.id = id;
		this.name = name;
		this.deviceID = deviceID;
		this.randomID = randomID;
	}


	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRandomID() {
		return randomID;
	}

	public void setRandomID(String randomID) {
		this.randomID = randomID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	
}
	
