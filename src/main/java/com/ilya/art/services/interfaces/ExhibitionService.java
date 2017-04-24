package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.dto.ExhibitionEditionDto;
import com.ilya.art.dto.UrlChosserAssistantMatcher;

public interface ExhibitionService extends BasicService<Exhibition, Long> {

	public Exhibition findExhibition(String title);

	public List<Exhibition> findAll();

	public List<UrlChosserAssistantMatcher> getTitlesMeta();

	public void anounceNewExhibition(ExhibitionAnnounceDto exhibAnounceDTO);

	public void editExhibition(ExhibitionEditionDto exDto);

	public ExhibitionEditionDto getExhibitionEditionDto(String titile);

	public ExhibitionEditionDto getExhibitionEditionDto(Long id);

	public void deleteExhibition(Long id);

}
