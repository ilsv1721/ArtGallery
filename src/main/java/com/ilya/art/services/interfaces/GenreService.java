package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.Genre;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;
import com.ilya.art.dto.PaintingDto;

public interface GenreService {

	public Genre getByid(Long id);

	public Genre getByGenreString(String genrestring);

	void addNewGenre(GenreDto genreDto);

	void deleteGenre(GenreDto genreDto);

	void editGenre(GenreEditDto genreEditDto);

	boolean validateExist(GenreDto genreDto);

	List<GenreDto> getAllDto();

	List<GenreDto> getGenreOfPainting(long paintingId);

	List<PaintingDto> getAllPaintingsWithThisGenre(long genreId);

}
