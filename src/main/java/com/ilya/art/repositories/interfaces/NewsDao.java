package com.ilya.art.repositories.interfaces;

import java.util.List;

import com.ilya.art.domain.News;

public interface NewsDao extends Dao<News, Long> {
	List<News> getDescOrderedNews();

	List<News> getAscOrderedNews();

	News findByTitle(String title);

	News findLastDateNews();

}
