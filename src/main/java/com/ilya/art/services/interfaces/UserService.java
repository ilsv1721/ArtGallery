package com.ilya.art.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.dto.UserDto;

public interface UserService extends UserDetailsService {

	void registerNewUser(UserDetailsDto userDetailsDto);

	public boolean validateUniqueByEmail(String emailString);

	public boolean validateExistByEmail(String emailString);

	public UserDto getUserDtoByEmail(String email);

	public void changePassword(Long userId, String newPassword);

	public void changePassword(String userEmail, String newPassword);

	public void changeUserInfo(UserDto userDto);

}
