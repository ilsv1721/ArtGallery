package com.ilya.art.dto.converters;

import com.ilya.art.domain.Painting;
import com.ilya.art.dto.PaintingDto;

public abstract class PaintingDtoPaintingConverter {

	static public Painting convertBasics(PaintingDto paintingDto) {
		Painting painting = new Painting();
		painting.setCreationdate(DateDtoDateJavaConverter.convert(paintingDto.getCreationDate()));
		painting.setDescription(paintingDto.getDescription());
		painting.setPath(paintingDto.getPath());
		painting.setTitle(paintingDto.getTitle());
		return painting;

	}

}
