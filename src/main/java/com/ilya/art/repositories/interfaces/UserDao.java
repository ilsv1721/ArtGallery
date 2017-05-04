package com.ilya.art.repositories.interfaces;

import com.ilya.art.domain.User;

public interface UserDao extends Dao<User, Long> {

	User findByEmail(String email);

}
