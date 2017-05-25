package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.domain.News;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.utils.web.EntityMetaURLMappable;

public interface NewsService extends EntityMetaURLMappable {
	List<NewsDto> getDescOrderedNews();

	List<NewsDto> getAscOrderedNews();

	void deleteNewsById(long id);

	void editNews(NewsDto newsDto);

	void persistNews(NewsDto newsDto);

	NewsDto getNewsAsNewsDtoById(long id);

	News getById(Long id);

	News getNewsByTitle(String title);

}
