package com.praksa.dto;

import com.praksa.model.User;

public class LoginDTO {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginDTO() {}
	
	public LoginDTO(User user) {
		username = user.getUsername();
		password = user.getPassword();
	}
}
