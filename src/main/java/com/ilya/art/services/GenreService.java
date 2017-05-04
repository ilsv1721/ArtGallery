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
import com.ilya.art.repositories.interfaces.GenreDao;

@Service
@Transactional
public class GenreService implements com.ilya.art.services.interfaces.GenreService {

	@Autowired
	GenreDao genreDao;

	@Override
	public void addNewGenre(String genre) {
		try {
			genreDao.findByGenreString(genre);
			throw new EntityExistsException();
		} catch (NoResultException ex) {
			genreDao.persist(new Genre(genre));
		}

	}

	@Override
	public void deleteGenre(long genreId) {

	}

	@Override
	public void editGenre(GenreEditDto genreDto) {
		try {
			Genre genreToChange = genreDao.findByGenreString(genreDto.getOldValue());
			genreToChange.setGenre(genreDto.getNewValue());
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}

	}

	@Override
	public boolean validateExist(GenreDto genreDto) {
		try {
			if (genreDto.getId() > 0) {
				genreDao.findById(genreDto.getId());
			} else if (!genreDto.getGenre().isEmpty()) {
				genreDao.findByGenreString(genreDto.getGenre());
			}
		} catch (NoResultException ex) {
			return false;
		}
		return true;

	}

	@Override
	public List<GenreDto> getAllGenresDto() {
		List<GenreDto> genresDto = new ArrayList<>();
		genreDao.getAllGenres().forEach((genre) -> {
			genresDto.add(new GenreDto(genre));
		});
		return genresDto;
	}

	@Override
	public boolean validateExistByGenreString(String genreString) {
		Genre genreObj = genreDao.findByGenreString(genreString);
		if (genreObj != null) {
			return true;
		} else
			return false;
	}

	@Override
	public void deleteGenre(String genre) {
		try {
			Genre genreToDelete = genreDao.findByGenreString(genre);
			genreToDelete.getPaintingsWithThisGenre().forEach((paint) -> {
				paint.getGenre().remove(genreToDelete);
			});
			genreDao.remove(genreToDelete);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();

		}

	}

}
