package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.domain.News;
import com.ilya.art.domain.Painting;
import com.ilya.art.domain.User;

public class ArtMetaDto implements Serializable {
	
	private static final long serialVersionUID = -6240038759559685383L;
	
	private User artist;
	private Exhibition exhibition;
	private News news;
	private Painting painting;

	public ArtMetaDto(User artist, Exhibition exhibition, News news, Painting painting) {
		super();
		this.artist = artist;
		this.exhibition = exhibition;
		this.news = news;
		this.painting = painting;
	}

	public User getArtist() {
		return artist;
	}

	public void setArtist(User artist) {
		this.artist = artist;
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Painting getPainting() {
		return painting;
	}

	public void setPainting(Painting painting) {
		this.painting = painting;
	}

}
