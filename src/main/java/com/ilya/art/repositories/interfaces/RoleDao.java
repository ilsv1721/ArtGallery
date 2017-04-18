package com.ilya.art.repositories.interfaces;

import com.ilya.art.domain.Role;

public interface RoleDao extends Dao<Role, Long> {

	Role getRole(String role);
}
