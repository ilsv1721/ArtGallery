package com.ilya.art.repositories.interfaces;

import com.ilya.art.domain.User;

public interface UserDAO extends Dao<User, Long> {

	User findByEmail(String email);

}
