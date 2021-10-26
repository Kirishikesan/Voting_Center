package com.crypto.votingCenter.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String randomID;
	@Column(unique=true,nullable = false)
	private String deviceID;
	@Column(unique=true,nullable=false)
	private String email;
	@Column(unique=true)
	private String ballotID;
	public User(String randomID, String deviceID, String email, String ballotID) {
		super();
		this.randomID = randomID;
		this.deviceID = deviceID;
		this.email = email;
		this.ballotID = ballotID;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBallotID() {
		return ballotID;
	}
	public void setBallotID(String ballotID) {
		this.ballotID = ballotID;
	}
		
}
	
