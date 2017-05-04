package com.ilya.art.dto;

import com.ilya.art.domain.User;

public class UserDto {
	private String firstName;
	private String lastName;
	private String email;

	public UserDto() {
	}

	public UserDto(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDto(User author) {
		this.firstName = author.getFirstName();
		this.lastName = author.getLastName();
		this.email = author.getEmail();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
