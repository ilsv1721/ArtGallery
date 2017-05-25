package com.ilya.art.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.config.utils.LocalStorageProps;
import com.ilya.art.domain.Exhibition;
import com.ilya.art.domain.Genre;
import com.ilya.art.domain.Painting;
import com.ilya.art.dto.BasicUrlEnityMapperDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.dto.PaintingMetaDto;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.exceptions.PaintingNotFoundException;
import com.ilya.art.repositories.interfaces.ExhibitionDao;
import com.ilya.art.repositories.interfaces.GenreDao;
import com.ilya.art.repositories.interfaces.PaintingDao;
import com.ilya.art.repositories.interfaces.UserDao;
import com.ilya.art.utils.files.HashCodePathFileAssistant;
import com.ilya.art.utils.files.PathAndFileAssistant;
import com.ilya.art.utils.web.UrlEntityMapper;

@Service
@Transactional
public class PaintingService implements com.ilya.art.services.interfaces.PaintingService {

	static private Logger logger = LogManager.getLogger(PaintingService.class);

	PathAndFileAssistant<MultipartFile> pathAndFileAssistant = new HashCodePathFileAssistant(2);

	@Autowired
	LocalStorageProps localStorageProps;

	@Autowired
	PaintingDao paintingDao;

	@Autowired
	GenreDao genreDao;

	@Autowired
	ExhibitionDao exibitionDao;

	@Autowired
	UserDao userDao;

	@Override
	public void addNewPainting(PaintingDto paintingDto, MultipartFile file) {
		Painting paint = new Painting();
		paint.setAuthor(userDao.findByEmail(paintingDto.getAuthor().getEmail()));
		paint.setCreationdate(DateDtoDateJavaConverter.convert(paintingDto.getCreationDate()));
		paint.setDescription(paintingDto.getDescription());
		paint.setTitle(paintingDto.getTitle());

		Set<Exhibition> relatedExhibs = new HashSet<>();
		paintingDto.getExhibitionsId().forEach((exId) -> {
			Exhibition relExib = exibitionDao.getById(exId);
			relatedExhibs.add(relExib);
			relExib.getPaintings().add(paint);
		});
		paint.setExhibitions(relatedExhibs);

		Set<Genre> relatedGenres = new HashSet<>();
		paintingDto.getGenresId().forEach((genreId) -> {
			Genre relGenre = genreDao.getById(genreId);
			relatedGenres.add(relGenre);
			relGenre.getPaintingsWithThisGenre().add(paint);
		});
		paint.setGenre(relatedGenres);

		String prefix = localStorageProps.getLocalStoragePathExhibs();

		try {
			com.ilya.art.utils.files.Path path = pathAndFileAssistant.getPath(file);
			if (validateUnique(path.getPathToFile() + path.getFilename())) {
				try {
					pathAndFileAssistant.saveToFile(prefix, path, file);
					paint.setPath(path.getPathToFile() + path.getFilename());
					paintingDao.persist(paint);
				} catch (IOException ex) {
					logger.error(ex.getClass().getName() + " while trying to add new painting with path in file system "
							+ prefix + path.getPathToFile() + path.getFilename());
					pathAndFileAssistant
							.deleteFile(Paths.get(prefix + path.getPathToFile() + File.separator + path.getFilename()));
				}
			}
		} catch (IOException ex) {
			logger.error(ex.getClass().getName()
					+ " while trying to rollback/calculate path for new painting with path in file system " + prefix);
		}

	}

	@Override
	public PaintingDto getRandomPainting() {
		Painting paint = paintingDao.getRandomPainting();
		return new PaintingDto(paint);
	}

	@Override
	public boolean validateUnique(String path) {
		try {
			paintingDao.findByPath(path);
			return false;
		} catch (NotFoundException ex) {
			return true;
		}
	}

	@Override
	public void editDescContent(PaintingMetaDto paintingDto) {
		try {
			Painting paint = paintingDao.getById(paintingDto.getId());
			paint.setTitle(paintingDto.getTitle());
			paint.setCreationdate(DateDtoDateJavaConverter.convert(paintingDto.getCreationDate()));
			paint.setDescription(paintingDto.getDescription());
			Set<Exhibition> relatedExhibs = new HashSet<>();
			for (Long exhId : paintingDto.getExhibitionsId()) {
				Exhibition relExib = exibitionDao.getById(exhId);
				relatedExhibs.add(relExib);
				relExib.getPaintings().add(paint);
			}
			paint.setExhibitions(relatedExhibs);

			Set<Genre> relatedGenres = new HashSet<>();
			for (Long genId : paintingDto.getGenresId()) {
				Genre relGenre = genreDao.getById(genId);
				relatedGenres.add(relGenre);
				relGenre.getPaintingsWithThisGenre().add(paint);
			}
			paint.setGenre(relatedGenres);

		} catch (NotFoundException ex) {
			throw new PaintingNotFoundException();
		}
	}

	@Override
	public void deletePainting(Long paintId, Long exhibitionId) {
		try {
			Painting paint = paintingDao.getById(paintId);
			paint.getGenre().forEach((genre) -> {
				genre.getPaintingsWithThisGenre().remove(paint);
			});

			Exhibition exhibDeleteFrom = exibitionDao.getById(exhibitionId);
			exhibDeleteFrom.getPaintings().remove(paint);
			paint.getExhibitions().remove(exhibDeleteFrom);

			if (paint.getExhibitions().isEmpty()) {
				try {
					pathAndFileAssistant
							.deleteFile(Paths.get(localStorageProps.getLocalStoragePathExhibs() + paint.getPath()));
					paintingDao.remove(paint);
				} catch (IOException ex) {
					logger.error(ex.getClass().getName() + " while trying to delete painting by id = " + paintId);
				}
			}

		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " while trying to delete painting by id = " + paintId);
			throw new PaintingNotFoundException();
		}

	}

	@Override
	public boolean isExist(Long id) {
		try {
			paintingDao.getById(id);
			return true;
		} catch (NotFoundException ex) {
			return false;
		}
	}

	@Override
	public boolean isExist(String path) {
		try {
			paintingDao.findByPath(path);
			return true;
		} catch (NotFoundException ex) {
			return false;
		}
	}

	@Override
	public PaintingDto getById(Long id) {
		try {
			return new PaintingDto(paintingDao.getById(id));
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " while trying to find painting by id = " + id);
			throw new PaintingNotFoundException();
		}
	}

	@Override
	public List<UrlEntityMapper> findPaintingByTitleSubstring(String substring) {
		List<UrlEntityMapper> list = new ArrayList<>();
		paintingDao.getAllPaintings().forEach((paint) -> {
			if (paint.getTitle().toLowerCase().contains(substring.toLowerCase())) {
				list.add(new BasicUrlEnityMapperDto(paint.getTitle(), Long.toString(paint.getId())));
			}
		});
		return list;
	}

}
