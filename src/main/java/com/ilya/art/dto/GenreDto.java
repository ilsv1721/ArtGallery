package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.ilya.art.domain.Genre;

public class GenreDto implements Serializable {

	private static final long serialVersionUID = 2970291294671257859L;

	private long id;
	@Size(min = 2, max = 70)
	private String genre;

	public GenreDto(String genre) {
		this.genre = genre;
	}

	public GenreDto() {

	}

	public GenreDto(Genre genre2) {
		this.genre = genre2.getGenre();
		this.id = genre2.getId();
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		GenreDto other = (GenreDto) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		return true;
	}

}
