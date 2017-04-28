package com.ilya.art.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "paintings")
public class Painting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "painting_id")
	private long id;

	@Column(name = "filepath")
	private String path;

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@Column(name = "create_date")
	private Date creationdate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "paintings_genres", joinColumns = @JoinColumn(name = "painting_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genre = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "paintings_exhibition", joinColumns = @JoinColumn(name = "painting_id"), inverseJoinColumns = @JoinColumn(name = "exhibition_id"))
	private Set<Genre> exhibitions = new HashSet<>();

	public Painting() {
	}

	public Painting(String path) {
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationdate == null) ? 0 : creationdate.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Painting other = (Painting) obj;
		if (creationdate == null) {
			if (other.creationdate != null)
				return false;
		} else if (!creationdate.equals(other.creationdate))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
