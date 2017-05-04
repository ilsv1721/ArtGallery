package com.ilya.art.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = -6932434383860486329L;
	private String role;

	public RoleDto(String role) {
		this.role = role;
	}

	public RoleDto() {
	};

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
