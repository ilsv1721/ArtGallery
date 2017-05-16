package com.ilya.art.dto;

import javax.validation.constraints.Size;

public class RoleEditDto extends RoleDto {

	private static final long serialVersionUID = -3977033896245441477L;

	@Size(min = 3, max = 70)
	private String newValue;

	public RoleEditDto() {

	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}
