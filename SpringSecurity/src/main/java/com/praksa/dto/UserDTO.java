package com.praksa.dto;

import com.praksa.model.Role;
import com.praksa.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String image_url;
	private Role role;
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		image_url = user.getImage_url();
		role = user.getRole();
	}
	
	
	
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
}
