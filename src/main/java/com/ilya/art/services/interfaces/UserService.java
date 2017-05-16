package com.ilya.art.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ilya.art.dto.UserDetailsDto;

public interface UserService extends UserDetailsService {

	void registerNewUser(UserDetailsDto userDetailsDto);

	public boolean validateUniqueByEmail(String emailString);
	
	public boolean validateExistByEmail(String emailString);

}
