package com.ilya.art.dto;

import java.io.Serializable;
import java.util.Date;

import com.ilya.art.domain.News;

public class NewsDto implements Serializable {

	private static final long serialVersionUID = -7985663385515669607L;

	private long id;
	private Date publishedDate = new Date();
	private String text;
	private String keyAuthor;
	private String title;

	public NewsDto() {

	}

	public NewsDto(News news) {
		this.id = news.getId();
		this.publishedDate = news.getPublishDate();
		this.text = news.getContent();
		this.keyAuthor = news.getWrittenBy().getEmail();
		this.title = news.getTitle();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKeyAuthor() {
		return keyAuthor;
	}

	public void setKeyAuthor(String keyAuthor) {
		this.keyAuthor = keyAuthor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
