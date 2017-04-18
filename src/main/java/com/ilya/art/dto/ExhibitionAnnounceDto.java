package com.ilya.art.dto;

import java.util.Date;

public class ExhibitionAnnounceDto {

	private String title;
	private String description;
	private Date starts = new Date();
	private Date ends = new Date();
	private String emailAnouncer;

	public ExhibitionAnnounceDto() {

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

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public String getEmailAnouncer() {
		return emailAnouncer;
	}

	public void setEmailAnouncer(String emailAnouncer) {
		this.emailAnouncer = emailAnouncer;
	}

}
