package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;

public class BasicExhibitionDto implements Serializable {
	
	private static final long serialVersionUID = 7650466647313331106L;
	
	private String title;
	DateDto startDate;
	DateDto endDate;

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

	public DateDto getStartDate() {
		return startDate;
	}

	public void setStartDate(DateDto startDate) {
		this.startDate = startDate;
	}

	public DateDto getEndDate() {
		return endDate;
	}

	public void setEndDate(DateDto endDate) {
		this.endDate = endDate;
	}

}
