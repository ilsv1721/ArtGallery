package com.ilya.art.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.User;
import com.ilya.art.repositories.interfaces.UserDAO;

@Repository
@Transactional
public class UserDAOjpa implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public void saveUser(User user) {
	}

	@Override
	public User findUserById(Long id) {
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		return null;
	}

}
