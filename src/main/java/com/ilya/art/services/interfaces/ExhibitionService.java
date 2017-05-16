package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.ExhibitionDto;
import com.ilya.art.dto.ExhibitionTitileAndIdDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.utils.web.EntityMetaURLMappable;
import com.ilya.art.utils.web.UrlEntityMapper;

public interface ExhibitionService extends EntityMetaURLMappable {

	public Exhibition findExhibition(String title);

	public ExhibitionDto findExhibition(Long id);

	public List<Exhibition> getAllExhibition();

	public List<ExhibitionTitileAndIdDto> getPastExhibitionTitileAndIdDto();

	public List<ExhibitionTitileAndIdDto> getAllExhibitionTitileAndIdDto();

	public List<ExhibitionDto> findAllBasicDto();

	public void anounceNewExhibition(ExhibitionDto exhibAnounceDTO);

	public void editExhibition(ExhibitionDto exDto);

	public ExhibitionDto getExhibitionEditionDto(String titile);

	public ExhibitionDto getExhibitionEditionDto(Long id);

	public void deleteExhibition(Long id);

	public List<PaintingDto> getPaintingsOfExhibition(long id);

	public List<UrlEntityMapper> getCurrentExhibitions();

	public List<UrlEntityMapper> getPastExhibitions();

	public List<UrlEntityMapper> getFutureExhibitions();

	public List<UrlEntityMapper> findExhibitionByTitleSubstring(String substring);
}
