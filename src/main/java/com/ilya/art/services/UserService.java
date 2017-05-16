package com.ilya.art.services;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.User;
import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.repositories.interfaces.RoleDao;
import com.ilya.art.repositories.interfaces.UserDao;

@Service
@Transactional
public class UserService implements com.ilya.art.services.interfaces.UserService {

	static public String DEFFAULT_ROLE = "ROLE_USER";

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
		} catch (NoResultException ex) {
			return true;
		}
	}

	@Override
	public boolean validateExistByEmail(String emailString) {
		try {
			userDao.findByEmail(emailString);
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

}
