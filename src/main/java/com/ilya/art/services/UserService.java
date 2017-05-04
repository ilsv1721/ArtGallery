package com.ilya.art.services;

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

	public void persist(User entity) {
		userDao.persist(entity);
	}

	public void remove(User entity) {
		userDao.remove(entity);
	}

	public User findById(Long id) {
		return userDao.findById(id);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public void merge(User entity) {
		userDao.merge(entity);
	}

	@Override
	public void registerNewUser(UserDetailsDto userDetailsDto) {
		User user = new User(userDetailsDto.getFirstName(), userDetailsDto.getLastName(), userDetailsDto.getEmail(),
				passwordEncoder.encode(userDetailsDto.getPassword()));
		user.getRoles().add(roleDao.getRole("ROLE_USER"));
		user.setActiveStatus(true);
		userDao.persist(user);
	}
	
	
}
