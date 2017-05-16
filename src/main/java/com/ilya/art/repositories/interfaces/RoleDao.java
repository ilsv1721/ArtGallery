package com.ilya.art.repositories.interfaces;

import java.util.List;

import com.ilya.art.domain.Role;

public interface RoleDao extends Dao<Role, Long> {

	Role getRole(String role);

	public List<Role> getAll();
}
