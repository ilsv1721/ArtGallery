package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.ilya.art.domain.Role;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = -6932434383860486329L;

	private long id;
	@Size(min = 3, max = 70)
	private String role;

	public RoleDto(String role) {
		this.role = role;
	}

	public RoleDto(Role role) {
		this.id = role.getId();
		this.role = role.getAuthority();
	}

	public RoleDto() {
	};

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
