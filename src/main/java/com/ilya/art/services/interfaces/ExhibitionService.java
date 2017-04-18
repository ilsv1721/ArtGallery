package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.dto.ExhibitiosTitlesFormattedDto;

public interface ExhibitionService extends BasicService<Exhibition, Long> {

	public Exhibition findExhibition(String title);

	public List<Exhibition> findAll();

	public List<ExhibitiosTitlesFormattedDto> getTitlesMeta();

	public void anounceNewExhibition(ExhibitionAnnounceDto exhibAnounceDTO);

}
