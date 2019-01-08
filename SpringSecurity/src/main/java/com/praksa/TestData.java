package com.praksa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.praksa.model.Role;
import com.praksa.model.User;
import com.praksa.service.UserService;



@Component
public class TestData {

	@Autowired
	UserService userService;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	

	@PostConstruct
	public void createData() {
		
		User peraUser =  new User("pera",passwordEncoder.encode("pera"),"Pera","Peric",Role.ROLE_ADMIN,null);
		peraUser = userService.saveUser(peraUser);

		User mika = new User("mika", passwordEncoder.encode("mika"), "Mika", "Mikic", Role.ROLE_USER,null);
	    userService.saveUser(mika);
	
	}

}