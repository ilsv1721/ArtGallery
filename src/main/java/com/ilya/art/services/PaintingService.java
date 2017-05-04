package com.ilya.art.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilya.art.domain.Painting;
import com.ilya.art.dto.BasicExhibitionDto;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.dto.converters.PaintingDtoPaintingConverter;
import com.ilya.art.repositories.interfaces.ExhibitionDao;
import com.ilya.art.repositories.interfaces.GenreDao;
import com.ilya.art.repositories.interfaces.PaintingDao;
import com.ilya.art.repositories.interfaces.UserDao;

@Service
public class PaintingService implements com.ilya.art.services.interfaces.PaintingService {

	@Autowired
	PaintingDao paintingDao;

	@Autowired
	GenreDao genreDao;

	@Autowired
	ExhibitionDao exibitionDao;

	@Autowired
	UserDao userDao;

	@Override
	public void addNewPainting(PaintingDto paintingDto) {
		Painting paint = PaintingDtoPaintingConverter.convertBasics(paintingDto);
		for (BasicExhibitionDto exhDto : paintingDto.getExhibitions()) {
			paint.getExhibitions().add(exibitionDao.findExhibition(exhDto.getTitle()));
		}
		for (GenreDto genDto : paintingDto.getGenres()) {
			paint.getGenre().add(genreDao.findByGenreString(genDto.getGenre()));
		}
		paint.setAuthor(userDao.findByEmail(paintingDto.getUserDto().getEmail()));

	}

	@Override
	public PaintingDto getRandomPainting() {
		Painting paint = paintingDao.getRandomPainting();
		return new PaintingDto(paint);
	}

}
