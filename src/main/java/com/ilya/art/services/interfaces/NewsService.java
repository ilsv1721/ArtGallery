package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.News;

public interface NewsService extends BasicService<News, Long> {
	List<News> getDescOrderedNews();

	List<News> getAscOrderedNews();

	News findByTitle(String title);

	News findLastDateNews();

}
