package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.Genre;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.GenreDao;
import com.ilya.art.repositories.interfaces.PaintingDao;

@Service
@Transactional
public class GenreService implements com.ilya.art.services.interfaces.GenreService {

	@Autowired
	GenreDao genreDao;

	@Autowired
	PaintingDao paintDao;

	@Override
	public void addNewGenre(GenreDto genre) {
		try {
			genreDao.findByGenreString(genre.getGenre());
			throw new EntityExistsException();
		} catch (NoResultException ex) {
			Genre genre1 = new Genre(genre.getGenre());
			genreDao.persist(genre1);
		}

	}

	@Override
	public void editGenre(GenreEditDto genreDto) {
		try {
			Genre genreToChange = genreDao.findByGenreString(genreDto.getGenre());
			genreToChange.setGenre(genreDto.getNewValue());
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}

	}

	@Override
	public List<GenreDto> getAllDto() {
		List<GenreDto> genresDto = new ArrayList<>();
		genreDao.getAll().forEach((genre) -> {
			genresDto.add(new GenreDto(genre));
		});
		return genresDto;
	}

	@Override
	public boolean validateExist(GenreDto genreDto) {
		try {
			genreDao.findByGenreString(genreDto.getGenre());
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

	@Override
	public void deleteGenre(GenreDto genre) {
		try {
			Genre genreToDelete = genreDao.findByGenreString(genre.getGenre());
			genreToDelete.getPaintingsWithThisGenre().forEach((paint) -> {
				paint.getGenre().remove(genreToDelete);
			});
			genreDao.remove(genreToDelete);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();

		}

	}

	@Override
	public List<GenreDto> getGenreOfPainting(long paintingId) {
		List<GenreDto> list = new ArrayList<>();
		try {
			paintDao.getById(paintingId).getGenre().forEach((genre) -> {
				list.add(new GenreDto(genre));
			});
			return list;
		} catch (NoResultException ex) {
			throw new NotFoundException();
		}
	}

}
