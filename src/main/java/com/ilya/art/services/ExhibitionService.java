package com.ilya.art.services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.config.utils.LocalStorageProps;
import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.BasicUrlEnityMapperDto;
import com.ilya.art.dto.ExhibitionDto;
import com.ilya.art.dto.ExhibitionTitileAndIdDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;
import com.ilya.art.exceptions.ExhibitionNotFoundException;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.ExhibitionDao;
import com.ilya.art.repositories.interfaces.UserDao;
import com.ilya.art.utils.files.HashCodePathFileAssistant;
import com.ilya.art.utils.files.PathAndFileAssistant;
import com.ilya.art.utils.web.UrlEntityMapper;

@Service
@Transactional
public class ExhibitionService implements com.ilya.art.services.interfaces.ExhibitionService {

	private static final Logger logger = LogManager.getLogger(ExhibitionService.class);

	PathAndFileAssistant<MultipartFile> pathAndFileAssistant = new HashCodePathFileAssistant(2);

	@Autowired
	LocalStorageProps localStorageProps;

	@Autowired
	ExhibitionDao exDao;

	@Autowired
	UserDao userDao;

	@Override
	public Exhibition getById(Long id) {
		try {
			Exhibition ex = exDao.getById(id);
			ex.getPaintings().iterator();
			return ex;
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + ":: Ocured while trying to find exhibition with id " + id);
			throw ex;
		}

	}

	@Override
	public Exhibition getByTitle(String title) {
		try {
			Exhibition ex = exDao.findExhibition(title);
			ex.getPaintings().iterator();
			return ex;
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + ":: Ocured while trying to find exhibition with title" + title);
			throw new ExhibitionNotFoundException(title);
		}
	}

	@Override
	public ExhibitionDto findExhibition(Long id) {
		return new ExhibitionDto(getById(id));
	}

	@Override
	public ExhibitionDto findExhibition(String title) {
		return new ExhibitionDto(getByTitle(title));

	}

	@Override
	public List<Exhibition> getAllExhibition() {
		return exDao.getAll();
	}

	@Override
	public List<UrlEntityMapper> getUrlEntityFieldAssistantMatchers() {
		List<UrlEntityMapper> list = new ArrayList<>();
		getAllExhibition().forEach((exhibition) -> {
			list.add(new BasicUrlEnityMapperDto(exhibition.getTitle(), Long.toString(exhibition.getId())));
		});
		return list;
	}

	@Override
	public void anounceNewExhibition(ExhibitionDto exhibAnounceDTO) {
		Exhibition exib = new Exhibition();
		exib.setDescription(exhibAnounceDTO.getDescription());
		exib.setTitle(exhibAnounceDTO.getTitle());
		exib.setStarts(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getStartDate()));
		exib.setEnds(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getEndDate()));
		exib.setAnnouncedBy(userDao.findByEmail(exhibAnounceDTO.getUser().getEmail()));
		exDao.persist(exib);

	}

	@Override
	public void editExhibition(ExhibitionDto exhibAnounceDTO) {
		Exhibition exib = getById(exhibAnounceDTO.getId());
		exib.setDescription(exhibAnounceDTO.getDescription());
		exib.setTitle(exhibAnounceDTO.getTitle());
		exib.setStarts(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getStartDate()));
		exib.setEnds(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getEndDate()));
		exib.setAnnouncedBy(userDao.findByEmail(exhibAnounceDTO.getUser().getEmail()));
	}

	@Override
	public void deleteExhibition(Long id) {
		Exhibition exhib = getById(id);
		exhib.getPaintings().forEach((paint) -> {
			if (paint.getExhibitions().size() == 1) {
				try {
					pathAndFileAssistant
							.deleteFile(Paths.get(localStorageProps.getLocalStoragePathExhibs() + paint.getPath()));
				} catch (IOException ex) {
					logger.error(ex.getClass().getName() + " while trying to delete painting by id = " + id);
				}
			}
		});
		exDao.remove(exhib);
	}

	@Override
	public List<ExhibitionDto> findAllBasicDto() {
		List<ExhibitionDto> list = new ArrayList<>();
		exDao.getAll().forEach((exhibition) -> {
			list.add(new ExhibitionDto(exhibition));
		});
		return list;
	}

	@Override
	public List<ExhibitionTitileAndIdDto> getPastExhibitionTitileAndIdDto() {
		Date currentDate = new Date();
		List<ExhibitionTitileAndIdDto> list = new ArrayList<>();
		getAllExhibition().forEach((ex) -> {
			if (currentDate.after(ex.getEnds())) {
				list.add(new ExhibitionTitileAndIdDto(ex));
			}
		});
		return list;
	}

	@Override
	public List<PaintingDto> getPaintingsOfExhibition(long id) {
		List<PaintingDto> list = new ArrayList<>();
		exDao.getById(id).getPaintings().forEach((painting) -> {
			list.add(new PaintingDto(painting));
		});
		return list;
	}

	@Override
	public List<UrlEntityMapper> getCurrentExhibitions() {
		List<UrlEntityMapper> list = new ArrayList<>();
		Date currentDate = new Date();
		getAllExhibition().forEach((ex) -> {
			if (currentDate.after(ex.getStarts()) && currentDate.before(ex.getEnds())) {
				list.add(new BasicUrlEnityMapperDto(ex.getTitle() + " by " + ex.getAnnouncedBy().getFirstName() + " "
						+ ex.getAnnouncedBy().getLastName(), Long.toString(ex.getId())));
			}
		});
		return list;
	}

	@Override
	public List<UrlEntityMapper> getPastExhibitions() {
		List<UrlEntityMapper> list = new ArrayList<>();
		Date currentDate = new Date();
		getAllExhibition().forEach((ex) -> {
			if (currentDate.after(ex.getEnds())) {
				list.add(new BasicUrlEnityMapperDto(ex.getTitle() + " by " + ex.getAnnouncedBy().getFirstName() + " "
						+ ex.getAnnouncedBy().getLastName(), Long.toString(ex.getId())));
			}
		});
		return list;
	}

	@Override
	public List<UrlEntityMapper> getFutureExhibitions() {
		List<UrlEntityMapper> list = new ArrayList<>();
		Date currentDate = new Date();
		getAllExhibition().forEach((ex) -> {
			if (currentDate.before(ex.getStarts())) {
				list.add(new BasicUrlEnityMapperDto(ex.getTitle() + " by " + ex.getAnnouncedBy().getFirstName() + " "
						+ ex.getAnnouncedBy().getLastName(), Long.toString(ex.getId())));
			}
		});
		return list;
	}

	@Override
	public List<UrlEntityMapper> findExhibitionByTitleSubstring(String substring) {
		List<UrlEntityMapper> list = new ArrayList<>();
		getAllExhibition().forEach((ex) -> {
			if (ex.getTitle().toLowerCase().contains(substring.toLowerCase())) {
				list.add(new BasicUrlEnityMapperDto(ex.getTitle(), Long.toString(ex.getId())));
			}
		});
		return list;
	}

	@Override
	public List<ExhibitionTitileAndIdDto> getAllExhibitionTitileAndIdDto() {
		List<ExhibitionTitileAndIdDto> list = new ArrayList<>();
		getAllExhibition().forEach((ex) -> {
			list.add(new ExhibitionTitileAndIdDto(ex));
		});
		return list;
	}

}
