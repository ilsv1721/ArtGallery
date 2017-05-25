package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitionDto;
import com.ilya.art.dto.ExhibitionTitileAndIdDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.utils.web.EntityMetaURLMappable;
import com.ilya.art.utils.web.UrlEntityMapper;

public interface ExhibitionService extends EntityMetaURLMappable {

	Exhibition getById(Long id);

	Exhibition getByTitle(String title);

	ExhibitionDto findExhibition(String title);

	ExhibitionDto findExhibition(Long id);

	List<Exhibition> getAllExhibition();

	List<ExhibitionTitileAndIdDto> getPastExhibitionTitileAndIdDto();

	List<ExhibitionTitileAndIdDto> getAllExhibitionTitileAndIdDto();

	List<ExhibitionDto> findAllBasicDto();

	void anounceNewExhibition(ExhibitionDto exhibAnounceDTO);

	void editExhibition(ExhibitionDto exDto);

	void deleteExhibition(Long id);

	List<PaintingDto> getPaintingsOfExhibition(long id);

	List<UrlEntityMapper> getCurrentExhibitions();

	List<UrlEntityMapper> getPastExhibitions();

	List<UrlEntityMapper> getFutureExhibitions();

	List<UrlEntityMapper> findExhibitionByTitleSubstring(String substring);

}
