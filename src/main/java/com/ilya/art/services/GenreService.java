package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.Genre;
import com.ilya.art.domain.Painting;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;
import com.ilya.art.dto.PaintingDto;
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

	private static Logger logger = LogManager.getLogger(GenreService.class);

	@Override
	public Genre getByid(Long id) {
		try {
			Genre genre = genreDao.getById(id);
			return genre;
		} catch (NotFoundException ex) {
			logger.error("NotFoundException :: while trying to fing genre with id" + id);
			throw new NotFoundException();
		}

	}

	@Override
	public Genre getByGenreString(String genrestring) {
		try {
			Genre genre = genreDao.findByGenreString(genrestring);
			return genre;
		} catch (NotFoundException ex) {
			logger.error("NotFoundException :: while trying to fing genre " + genrestring);
			throw new NotFoundException();
		}

	}

	@Override
	public void addNewGenre(GenreDto genre) {
		if (validateExist(genre)) {
			logger.error("Dupplicate role " + genre.getGenre());
			throw new EntityExistsException();
		} else {
			genreDao.persist(new Genre(genre.getGenre()));
		}
	}

	@Override
	public void editGenre(GenreEditDto genreDto) {
		Genre genreToChange = getByGenreString(genreDto.getGenre());
		genreToChange.setGenre(genreDto.getNewValue());
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
		} catch (NotFoundException ex) {
			return false;
		}
	}

	@Override
	public void deleteGenre(GenreDto genre) {
		Genre genreToDelete = getByGenreString(genre.getGenre());
		genreToDelete.getPaintingsWithThisGenre().forEach((paint) -> {
			paint.getGenre().remove(genreToDelete);
		});
		genreDao.remove(genreToDelete);

	}

	@Override
	public List<GenreDto> getGenreOfPainting(long paintingId) {
		List<GenreDto> list = new ArrayList<>();
		try {
			Painting paint = paintDao.getById(paintingId);
			paint.getGenre().forEach((genre) -> {
				list.add(new GenreDto(genre));
			});
			return list;
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + "  :: while trying to get genres of painting winth painting id "
					+ paintingId);
			throw new NotFoundException();
		}

	}

	@Override
	public List<PaintingDto> getAllPaintingsWithThisGenre(long genreId) {
		List<PaintingDto> list = new ArrayList<>();
		genreDao.getById(genreId).getPaintingsWithThisGenre().forEach((painting) -> {
			list.add(new PaintingDto(painting));
		});
		return list;
	}
}
