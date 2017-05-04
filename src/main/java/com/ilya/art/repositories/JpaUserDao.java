package com.ilya.art.repositories;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.User;
import com.ilya.art.repositories.interfaces.UserDao;

@Repository
public class JpaUserDao extends JpaDao<User, Long> implements UserDao {

	@Override
	public User findByEmail(String email) throws NoResultException {
		TypedQuery<User> q = entityManager.createNamedQuery("User.findbyemail", User.class).setParameter("parem",
				email);
		return q.getSingleResult();
	}
}
