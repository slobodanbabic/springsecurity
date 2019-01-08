package com.praksa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.praksa.model.User;
import com.praksa.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;
		
	
	public List<User> getAllUsers(){		
		return userRepo.findAll();
	}
	public User getUserById(Long id) {
		return userRepo.getOne(id);
	}
	public User saveUser(User user) {		
		return userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepo.delete(getUserById(id));
	}
}
