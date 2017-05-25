package com.ilya.art.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.ilya.art.domain.Painting;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;

public class PaintingMetaDto implements Serializable {

	private static final long serialVersionUID = 2395642220623040895L;

	private long id;
	private DateDto creationDate;
	@Size(min = 1, max = 20)
	private String title;
	@Size(min = 1, max = 20)
	private String description;
	private List<Long> exhibitionsId = new ArrayList<>();
	private List<Long> genresId = new ArrayList<>();
	private UserDto author;

	public PaintingMetaDto(Painting painting) {
		this.creationDate = DateDtoDateJavaConverter.convert(painting.getCreationdate());
		this.title = painting.getTitle();
		this.description = painting.getDescription();
		this.id = painting.getId();
		painting.getExhibitions().forEach(exhibition -> {
			exhibitionsId.add(exhibition.getId());
		});

		painting.getGenre().forEach(genre -> {
			genresId.add(genre.getId());
		});
		this.author = new UserDto(painting.getAuthor());

	}

	public PaintingMetaDto() {
	}

	public DateDto getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateDto creationDate) {
		this.creationDate = creationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Long> getExhibitionsId() {
		return exhibitionsId;
	}

	public void setExhibitionsId(List<Long> exhibitionsId) {
		this.exhibitionsId = exhibitionsId;
	}

	public List<Long> getGenresId() {
		return genresId;
	}

	public void setGenresId(List<Long> genresId) {
		this.genresId = genresId;
	}

	public UserDto getAuthor() {
		return author;
	}

	public void setAuthor(UserDto author) {
		this.author = author;
	}

}
