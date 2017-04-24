package com.ilya.art.dto;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;

public class BasicExhibitionDto {
	private String title;
	DtoDate startDate;
	DtoDate endDate;

	public BasicExhibitionDto() {
	}

	public BasicExhibitionDto(Exhibition exhibition) {
		this.title = exhibition.getTitle();
		this.startDate = DateDtoDateJavaConverter.convert(exhibition.getStarts());
		this.endDate = DateDtoDateJavaConverter.convert(exhibition.getEnds());

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DtoDate getStartDate() {
		return startDate;
	}

	public void setStartDate(DtoDate startDate) {
		this.startDate = startDate;
	}

	public DtoDate getEndDate() {
		return endDate;
	}

	public void setEndDate(DtoDate endDate) {
		this.endDate = endDate;
	}

}
