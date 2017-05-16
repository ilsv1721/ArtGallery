package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;

public interface GenreService {
	void addNewGenre(GenreDto genreDto);

	void deleteGenre(GenreDto genreDto);

	void editGenre(GenreEditDto genreEditDto);

	boolean validateExist(GenreDto genreDto);

	List<GenreDto> getAllDto();

	List<GenreDto> getGenreOfPainting(long paintingId);

}
