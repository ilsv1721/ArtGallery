package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitiosTitlesFormatted;
import com.ilya.art.repositories.interfaces.ExhibitionDao;

@Service
public class ExhibitionService implements com.ilya.art.services.interfaces.ExhibitionService {

	@Autowired
	ExhibitionDao exDao;

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

	// Not really cool. Ask a question
	@Override
	public List<ExhibitiosTitlesFormatted> getTitlesMeta() {
		List<ExhibitiosTitlesFormatted> list = new ArrayList<>();
		findAll().forEach((exhibition) -> {
			list.add(new ExhibitiosTitlesFormatted(exhibition));
		});
		return list;
	}

}
