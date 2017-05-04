package com.ilya.art.services.interfaces;

import com.ilya.art.dto.PaintingDto;

public interface PaintingService {
	void addNewPainting(PaintingDto paintingDto);

	PaintingDto getRandomPainting();
}
