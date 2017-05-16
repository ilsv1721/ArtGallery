package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class GenreEditDto extends GenreDto implements Serializable {

	private static final long serialVersionUID = -9032397873472315753L;

	@Size(min = 2, max = 15)
	private String newValue;

	public GenreEditDto() {

	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}
