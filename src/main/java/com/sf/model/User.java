package com.sf.model;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

public class User {
	private int userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private byte[] profileImage;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String password;
	private String userType;
	private String base64encodedDP = "";

	private void initialise() {
		userId = 0;
		firstName = "";
		lastName = "";
		dob = "";
		gender = "";
		profileImage = null;
		city = "";
		state = "";
		country = "";
		zip = "";
		userType = "";
	}

	public User(String emailId) {
		this.initialise();
		this.setEmailId(emailId);
	}

	public User(int userId) {
		initialise();
		this.setUserId(userId);
	}
	
	public User(String emailId, String firstName, String lastName) {
		this.initialise();
		this.setEmailId(emailId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public void setName(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public void setAddress(String city, String state, String country) {
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
	}

	public void setAddress(String city, String state, String country, String zip) {
		this.setAddress(city, state, country);
		this.setZip(zip);
	}

	public int getUserId() {
		return userId;
	}

	private void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
		setBase64EncodedImage(profileImage);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setBase64EncodedImage(byte[] dp) {
//		byte[] encodeBase64 = Base64.getEncoder().encode(dp);
        String base64Encoded = null;
		try {
//			base64Encoded = new String(encodeBase64, "UTF-8");
			base64Encoded = DatatypeConverter.printBase64Binary("duke".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setBase64encodedDP(base64Encoded);
	}

	public String getBase64encodedDP() {
		return base64encodedDP;
	}

	private void setBase64encodedDP(String base64encodedDP) {
		this.base64encodedDP = base64encodedDP;
	}
}
