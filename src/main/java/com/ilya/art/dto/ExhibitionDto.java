package com.ilya.art.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;

public class ExhibitionDto implements Serializable {

	private static final long serialVersionUID = -4720507796192665364L;

	private UserDto user;
	private long id;
	@Size(min = 1, max = 20)
	private String title;
	private DateDto startDate;
	private DateDto endDate;
	@Size(min = 1)
	private String description;
	private List<PaintingDto> paints = new ArrayList<>();

	public ExhibitionDto() {
	}

	public ExhibitionDto(Exhibition exhib) {
		this.user = new UserDto(exhib.getAnnouncedBy());
		this.id = exhib.getId();
		this.title = exhib.getTitle();
		this.startDate = DateDtoDateJavaConverter.convert(exhib.getStarts());
		this.endDate = DateDtoDateJavaConverter.convert(exhib.getEnds());
		exhib.getPaintings().forEach((pa) -> {
			this.paints.add(new PaintingDto(pa));
		});
		this.description = exhib.getDescription();
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<PaintingDto> getPaints() {
		return paints;
	}

	public void setPaints(List<PaintingDto> paints) {
		this.paints = paints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
