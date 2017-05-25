package com.ilya.art.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

public class UserRolesEditDto {

	@Size(min = 1, max = 40)
	private String userEmail;
	private List<Long> newRoles = new ArrayList<>();

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Long> getNewRoles() {
		return newRoles;
	}

	public void setNewRoles(List<Long> newRoles) {
		this.newRoles = newRoles;
	}

}
