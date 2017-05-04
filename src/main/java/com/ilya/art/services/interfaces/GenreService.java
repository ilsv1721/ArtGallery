package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;

public interface GenreService {
	void addNewGenre(String genre);
	void deleteGenre(long genreId);
	void deleteGenre(String genre);
	void editGenre(GenreEditDto genreEditDto);

	/**
	 * 
	 * @param genre
	 * @return true - if exist; false - duplicate
	 */
	boolean validateExist(GenreDto genreDto);
	boolean validateExistByGenreString(String genreString);
	List<GenreDto> getAllGenresDto();
	

}
