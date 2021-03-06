package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.ilya.art.domain.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -4408340045040147956L;

	private long id;
	@Size(min = 1, max = 40)
	private String firstName;
	@Size(min = 1, max = 40)
	private String lastName;
	@Size(min = 1, max = 40)
	private String email;

	public UserDto() {
	}

	public UserDto(String firstName, String lastName, String email, int id) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDto(User author) {
		this.id = author.getId();
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
