package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.News;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.dto.UrlChosserAssistantMatcher;

public interface NewsService extends BasicService<News, Long> {
	List<News> getDescOrderedNews();

	List<News> getAscOrderedNews();

	News findByTitle(String title);

	News findLastDateNews();

	void deleteNewsById(long id);

	void editNews(NewsDto newsDto);

	void persistNews(NewsDto newsDto);

	NewsDto getNewsAsNewsDtoById(long id);

	List<UrlChosserAssistantMatcher> getNewsURLd();

}
