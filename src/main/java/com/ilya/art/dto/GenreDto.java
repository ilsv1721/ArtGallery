package com.ilya.art.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.ilya.art.domain.Genre;

public class GenreDto implements Serializable {

	private static final long serialVersionUID = 2970291294671257859L;

	private long id = -1;
	@Size(min = 2, max = 15)
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

}
