package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.User;
import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.dto.UserDto;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.RoleDao;
import com.ilya.art.repositories.interfaces.UserDao;

@Service
@Transactional
public class UserService implements com.ilya.art.services.interfaces.UserService {

	static public String DEFFAULT_ROLE = "ROLE_USER";

	private static Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		return new UserDetailsDto(user.getEmail(), user.getPassword(), user.getActiveStatus(), user.getRoles());
	}

	@Override
	public void registerNewUser(UserDetailsDto userDetailsDto) {
		if (validateUniqueByEmail(userDetailsDto.getEmail())) {
			User user = new User(userDetailsDto.getFirstName(), userDetailsDto.getLastName(), userDetailsDto.getEmail(),
					passwordEncoder.encode(userDetailsDto.getPassword()));
			user.getRoles().add(roleDao.getRole(DEFFAULT_ROLE));
			user.setActiveStatus(true);
			userDao.persist(user);
		} else {
			throw new EntityExistsException();
		}
	}

	@Override
	public boolean validateUniqueByEmail(String emailString) {
		try {
			userDao.findByEmail(emailString);
			return false;
		} catch (NotFoundException ex) {
			return true;
		}
	}

	@Override
	public boolean validateExistByEmail(String emailString) {
		try {
			userDao.findByEmail(emailString);
			return true;
		} catch (NotFoundException ex) {
			return false;
		}
	}

	@Override
	public UserDto getUserDtoByEmail(String email) {
		try {
			return new UserDto(userDao.findByEmail(email));
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to get userdto woith email " + email);
			throw new NotFoundException();
		}
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		try {
			User userToChange = userDao.getById(userId);
			userToChange.setPassword(passwordEncoder.encode(newPassword));
		} catch (NotFoundException ex) {
			throw new NotFoundException();
		}
	}

	@Override
	public void changePassword(String userEmail, String newPassword) {
		try {
			User userToChange = userDao.findByEmail(userEmail);
			userToChange.setPassword(passwordEncoder.encode(newPassword));
		} catch (NotFoundException ex) {
			throw new NotFoundException();
		}
	}

	@Override
	public void changeUserInfo(UserDto userDto) {
		try {
			User userToChange = userDao.getById(userDto.getId());
			userToChange.setFirstName(userDto.getFirstName());
			userToChange.setLastName(userDto.getLastName());
		} catch (NotFoundException ex) {
			throw new NotFoundException();
		}
	}

	@Override
	public List<RoleDto> getUserRoles(Long userId) {
		List<RoleDto> roles = new ArrayList<>();
		userDao.getById(userId).getRoles().forEach((role) -> {
			roles.add(new RoleDto(role));
		});
		return roles;
	}

	@Override
	public List<RoleDto> getUserRoles(String userEmail) {
		List<RoleDto> roles = new ArrayList<>();
		userDao.findByEmail(userEmail).getRoles().forEach((role) -> {
			roles.add(new RoleDto(role));
		});
		return roles;

	}

	@Override
	public void chageRole(String userEmail, List<Long> roles) {
		User userToEdit = userDao.findByEmail(userEmail);
		userToEdit.getRoles().clear();
		for (Long roleId : roles) {
			userToEdit.getRoles().add(roleDao.getById(roleId));
		}
	}

}
