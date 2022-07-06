package com.yoyo.petgram.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {

	//==================== MEMBER VARIABLES ======================//
	
	@NotEmpty(message="Email required")
	@Email(message="Invalid email format")
	private String email;

	@NotEmpty(message="Password is required")
	@Size(min=2, max=255, message="Password must be between 2 and 255 characters")
	private String password;
	
	//==================== CONSTRUCTORS ======================//	
	
	public LoginUser() {
	}

	//==================== SETTERS AND GETTERS ======================//	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
