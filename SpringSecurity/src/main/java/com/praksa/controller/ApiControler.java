package com.praksa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.converter.UserConvertert;
import com.praksa.dto.UserDTO;
import com.praksa.model.User;
import com.praksa.service.UserService;


@RequestMapping("/api")
@RestController
public class ApiControler {

	@Autowired
	private UserConvertert converter;

	@Autowired
	private UserService userService;
		
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getUsers() {		
		return new ResponseEntity<>(converter.getUsersDTO(userService.getAllUsers()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/users/new")
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		User user = new User();
		user = converter.convertUserDTOToUser(userDTO);
		userService.saveUser(user);		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		User user = userService.getUserById(id);				
		if (user == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		userDTO.setId(id);
		user = converter.convertUserDTOToUser(userDTO);			
		userService.saveUser(user);				
		return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		User user = userService.getUserById(id);
		if (user != null) {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}

}	 
