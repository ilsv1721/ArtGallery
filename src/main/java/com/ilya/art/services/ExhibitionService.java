package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.dto.ExhibitiosTitlesFormattedDto;
import com.ilya.art.repositories.interfaces.ExhibitionDao;
import com.ilya.art.repositories.interfaces.UserDAO;

@Service
@Transactional
public class ExhibitionService implements com.ilya.art.services.interfaces.ExhibitionService {

	@Autowired
	ExhibitionDao exDao;

	@Autowired
	UserDAO userDao;

	public void persist(Exhibition entity) {
		exDao.persist(entity);
	}

	public void remove(Exhibition entity) {
		exDao.remove(entity);
	}

	public Exhibition findById(Long id) {
		return exDao.findById(id);
	}

	public void merge(Exhibition entity) {
		exDao.merge(entity);
	}

	public Exhibition findExhibition(String title) {
		return exDao.findExhibition(title);
	}

	public List<Exhibition> findAll() {
		return exDao.findAll();
	}

	// Use criteria API
	@Override
	public List<ExhibitiosTitlesFormattedDto> getTitlesMeta() {
		List<ExhibitiosTitlesFormattedDto> list = new ArrayList<>();
		findAll().forEach((exhibition) -> {
			list.add(new ExhibitiosTitlesFormattedDto(exhibition));
		});
		return list;
	}

	@Override
	public void anounceNewExhibition(ExhibitionAnnounceDto exhibAnounceDTO) {
		Exhibition exib = new Exhibition();
		exib.setAnnouncedBy(userDao.findByEmail(exhibAnounceDTO.getEmailAnouncer()));
		exib.setDescription(exhibAnounceDTO.getDescription());
		exib.setStarts(exhibAnounceDTO.getStarts());
		exib.setEnds(exhibAnounceDTO.getEnds());
		exib.setTitle(exhibAnounceDTO.getTitle());
		exhibAnounceDTO = null;
		exDao.persist(exib);

	}

}
