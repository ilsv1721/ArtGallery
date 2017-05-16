package com.ilya.art.repositories.interfaces;

import java.util.List;

import com.ilya.art.domain.Genre;

public interface GenreDao extends Dao<Genre, Long> {

	public Genre findByGenreString(String genre);

	public List<Genre> getAll();

}
