package com.ilya.art.services.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.dto.UserDto;

public interface UserService extends UserDetailsService {

	void registerNewUser(UserDetailsDto userDetailsDto);

	boolean validateUniqueByEmail(String emailString);

	boolean validateExistByEmail(String emailString);

	UserDto getUserDtoByEmail(String email);

	void changePassword(Long userId, String newPassword);

	void changePassword(String userEmail, String newPassword);

	void changeUserInfo(UserDto userDto);

	List<RoleDto> getUserRoles(Long userId);

	List<RoleDto> getUserRoles(String userEmail);

	void chageRole(String userEmail, List<Long> list);

}
