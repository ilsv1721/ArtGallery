package com.ilya.art.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.dto.PaintingDto;
import com.ilya.art.dto.PaintingMetaDto;
import com.ilya.art.utils.web.UrlEntityMapper;

public interface PaintingService {

	PaintingDto getRandomPainting();

	void deletePainting(Long paintId, Long exhibitionId);

	boolean validateUnique(String path);

	boolean isExist(Long id);

	boolean isExist(String path);

	void editDescContent(PaintingMetaDto paintingDto);

	PaintingDto getById(Long id);

	void addNewPainting(PaintingDto paintingDto, MultipartFile file);

	public List<UrlEntityMapper> findPaintingByTitleSubstring(String substring);

}
