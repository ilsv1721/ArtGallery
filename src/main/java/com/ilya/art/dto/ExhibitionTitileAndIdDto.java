package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.domain.Exhibition;

public class ExhibitionTitileAndIdDto implements Serializable {

	private static final long serialVersionUID = 6926994582468554711L;

	private long id;
	private String title;

	public ExhibitionTitileAndIdDto() {
	}

	public ExhibitionTitileAndIdDto(Exhibition ex) {
		this.id = ex.getId();
		this.title = ex.getTitle();
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

}
