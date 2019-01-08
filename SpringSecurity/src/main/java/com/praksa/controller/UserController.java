package com.praksa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.praksa.dto.LoginDTO;
import com.praksa.security.TokenHelper;


@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenHelper tokenUtils;


	@ResponseBody
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		System.out.println("YO, JA USAO!");
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			
					
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());			
			return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<String>("{text: \"Invalid login\"}", HttpStatus.BAD_REQUEST);
		}
	}
	

	
	
	

}
