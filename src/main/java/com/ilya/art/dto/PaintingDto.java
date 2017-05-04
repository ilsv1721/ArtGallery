package com.ilya.art.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ilya.art.domain.Painting;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;

public class PaintingDto implements Serializable {

	private static final long serialVersionUID = 5704374056044725084L;

	private DateDto creationDate;
	private String title;
	private String description;
	private List<BasicExhibitionDto> exhibitions = new ArrayList<>();
	private List<GenreDto> genres = new ArrayList<>();
	private String path;
	private long id;
	private UserDto userDto;

	public PaintingDto() {
	}

	
	//null checks.
	public PaintingDto(Painting painting) {
		this.creationDate = DateDtoDateJavaConverter.convert(painting.getCreationdate());
		this.title = painting.getTitle();
		this.description = painting.getDescription();
		this.path = painting.getPath();
		painting.getExhibitions().forEach(exhibition -> {
			exhibitions.add(new BasicExhibitionDto(exhibition));
		});
		
		painting.getGenre().forEach(genre -> {
			genres.add(new GenreDto(genre));
		});
		this.id = painting.getId();
		this.userDto = new UserDto(painting.getAuthor());

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

	public List<BasicExhibitionDto> getExhibitions() {
		return exhibitions;
	}

	public void setExhibitions(List<BasicExhibitionDto> exhibitions) {
		this.exhibitions = exhibitions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<GenreDto> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDto> genres) {
		this.genres = genres;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaintingDto other = (PaintingDto) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

}
