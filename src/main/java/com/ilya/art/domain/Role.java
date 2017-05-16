package com.ilya.art.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NamedQueries({ @NamedQuery(name = "Role.selectByRole", query = "from Role rl where rl.authority=:in_role"),
		@NamedQuery(name = "Role.findAll", query = "from Role role") })
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = -7064789554630714486L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int id;

	@Column(name = "role", nullable = false, unique = true)
	String authority;

	@ManyToMany(mappedBy = "roles")
	Set<User> usersWithThisRole = new HashSet<>();

	public Role() {
	}

	public Role(String role) {
		this.authority = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String role) {
		this.authority = role;
	}

	public Set<User> getUsersWithThisRole() {
		return usersWithThisRole;
	}

	public void setUsersWithThisRole(Set<User> usersWithThisRole) {
		this.usersWithThisRole = usersWithThisRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [authority=" + authority + "]";
	}

}
