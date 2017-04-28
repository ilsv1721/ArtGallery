package com.ilya.art.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.domain.Painting;

public class ExhibitionEditionDto extends ExhibitionAnnounceDto implements Serializable {

	private static final long serialVersionUID = 2220150365036928246L;

	private long id;
	private List<String> relativePathToMedia = new ArrayList<>();
	private List<String> pathToDelete = new ArrayList<>();

	public ExhibitionEditionDto() {
	}

	public ExhibitionEditionDto(Exhibition exhibition) {
		super(exhibition);
		this.id = exhibition.getId();
		for (Painting exIm : exhibition.getPaintings()) {
			relativePathToMedia.add(exIm.getPath());
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getRelativePathToMedia() {
		return relativePathToMedia;
	}

	public void setRelativePathToMedia(List<String> relativePathToMedia) {
		this.relativePathToMedia = relativePathToMedia;
	}

	public List<String> getPathToDelete() {
		return pathToDelete;
	}

	public void setPathToDelete(List<String> pathToDelete) {
		this.pathToDelete = pathToDelete;
	}

}
