package com.ilya.art.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.domain.Exhibition;

public class ExhibitionAnnounceDto extends BasicExhibitionDto implements Serializable {

	private static final long serialVersionUID = -4541060973225624238L;

	private String emailAnouncer;
	private String description;
	private List<MultipartFile> exhiMedia = new ArrayList<>();

	public ExhibitionAnnounceDto() {

	}

	public ExhibitionAnnounceDto(Exhibition exhibition) {
		super(exhibition);
		emailAnouncer = exhibition.getAnnouncedBy().getEmail();
		description = exhibition.getDescription();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MultipartFile> getExhiMedia() {
		return exhiMedia;
	}

	public void setExhiMedia(List<MultipartFile> exhiMedia) {
		this.exhiMedia = exhiMedia;
	}

	public String getEmailAnouncer() {
		return emailAnouncer;
	}

	public void setEmailAnouncer(String emailAnouncer) {
		this.emailAnouncer = emailAnouncer;
	}

}
