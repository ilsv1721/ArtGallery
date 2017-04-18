package com.ilya.art.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ilya.art.domain.User;
import com.ilya.art.dto.UserDetailsDto;

public interface UserService extends UserDetailsService, BasicService<User, Long> {

	void registerNewUser(UserDetailsDto userDetailsDto);

}
