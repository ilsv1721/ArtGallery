package com.ilya.art.dto.converters;

import com.ilya.art.domain.News;
import com.ilya.art.domain.User;
import com.ilya.art.dto.NewsDto;

public abstract class NewsDtoToNews {

	static public News convert(NewsDto newsDto, User user) {
		News news = new News();
		news.setContent(newsDto.getText());
		news.setId(newsDto.getId());
		news.setPublishDate(newsDto.getPublishedDate());
		news.setTitle(newsDto.getTitle());
		news.setWrittenBy(user);
		return news;

	}

}
