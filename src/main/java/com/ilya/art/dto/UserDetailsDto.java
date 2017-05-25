package com.ilya.art.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsDto implements UserDetails {

	private static final long serialVersionUID = -5926439103551082853L;

	@Size(min = 1, max = 20, message = "{firstname.size}")
	private String firstName;
	@Size(min = 1, max = 20, message = "{lastname.size}")
	private String lastName;
	@Size(min = 1, max = 20, message = "{password.size}")
	private String password;
	@Size(min = 1, max = 20, message = "{email.size}")
	private String email;
	private Set<? extends GrantedAuthority> authorities = new HashSet<>();
	private boolean status;

	public UserDetailsDto(String email, String password, boolean status, Set<? extends GrantedAuthority> auths) {
		this.email = email;
		this.password = password;
		this.authorities = auths;
		this.status = status;

	}

	public UserDetailsDto() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status;
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

	public void setPassword(String password) {
		this.password = password;
	}

}
