package com.ilya.art.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Role;
import com.ilya.art.repositories.interfaces.RoleDao;

@Repository
public class JpaRoleDao extends JpaDao<Role, Long> implements RoleDao {

	@Override
	public Role getRole(String role) {
		return entityManager.createNamedQuery("Role.selectByRole", Role.class).setParameter("in_role", role)
				.getSingleResult();
	}

	@Override
	public List<Role> getAll() {
		return entityManager.createNamedQuery("Role.findAll", Role.class).getResultList();
	}

}
