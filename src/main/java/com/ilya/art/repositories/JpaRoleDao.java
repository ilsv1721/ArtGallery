package com.ilya.art.repositories;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Role;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.RoleDao;

@Repository
public class JpaRoleDao extends JpaDao<Role, Long> implements RoleDao {

	@Override
	public Role getRole(String rolestr) {
		try {
			Role role = entityManager.createNamedQuery("Role.selectByRole", Role.class).setParameter("in_role", rolestr)
					.getSingleResult();
			return role;
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to get role = " + rolestr);
			throw new NotFoundException();
		}

	}

	@Override
	public List<Role> getAll() {
		return entityManager.createNamedQuery("Role.findAll", Role.class).getResultList();
	}

}
