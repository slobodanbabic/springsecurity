package com.praksa.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.praksa.dto.LoginDTO;
import com.praksa.dto.UserDTO;
import com.praksa.model.User;
@Service
public class UserConvertert {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public List<LoginDTO> convertUsersLoginToDTO(List<User> users) {
		List<LoginDTO> retVal = new ArrayList<LoginDTO>();		
		for (User user: users) {
			LoginDTO countryDTO = new LoginDTO(user); 
			retVal.add(countryDTO);
		}
		return retVal;		
	}
	
	public List<UserDTO> getUsersDTO(List<User> users){
		List<UserDTO> usersDTO = new ArrayList<>();
		for(User user : users)
			usersDTO.add(convertToUserDTO(user));
		return usersDTO;
	}
	
	public UserDTO convertToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setUsername(user.getUsername());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setPassword(user.getPassword());
		userDTO.setImage_url(user.getImage_url());
		return userDTO;
		
	}
	public User convertUserDTOToUser(UserDTO userDTO) {		
		User user = new User();		
		if(userDTO.getId() != null)
			user.setId(userDTO.getId());		
		user.setUsername(userDTO.getUsername());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		user.setImage_url(userDTO.getImage_url());
		return user;
	}
}
