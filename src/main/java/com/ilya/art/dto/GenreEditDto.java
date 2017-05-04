package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class GenreEditDto implements Serializable {

	private static final long serialVersionUID = -9032397873472315753L;

	private String oldValue;
	@Size(min = 2, max = 15)
	private String newValue;

	public GenreEditDto() {

	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}
