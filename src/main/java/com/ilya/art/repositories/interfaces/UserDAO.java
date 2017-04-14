package com.ilya.art.repositories.interfaces;

import com.ilya.art.domain.User;

public interface UserDAO {
	public void addUser(User user);
	public void saveUser(User user);
	public User findUserById(Long id);
	public User findUserByEmail(String email);
	
}
