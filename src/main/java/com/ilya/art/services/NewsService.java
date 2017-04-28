package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.News;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.dto.converters.NewsDtoToNews;
import com.ilya.art.repositories.interfaces.NewsDao;
import com.ilya.art.repositories.interfaces.UserDAO;
import com.ilya.art.utils.web.UrlEntityFieldAssistantMatcher;

@Service
@Transactional
public class NewsService implements com.ilya.art.services.interfaces.NewsService {

	@Autowired
	NewsDao newDao;

	@Autowired
	UserDAO userDao;

	public void persist(News entity) {
		newDao.persist(entity);
	}

	public void remove(News entity) {
		newDao.remove(entity);
	}

	public News findById(Long id) {
		return newDao.findById(id);
	}

	public void merge(News entity) {
		newDao.merge(entity);
	}

	public List<News> getDescOrderedNews() {
		return newDao.getDescOrderedNews();
	}

	public List<News> getAscOrderedNews() {
		return newDao.getAscOrderedNews();
	}

	public News findByTitle(String title) {
		return newDao.findByTitle(title);
	}

	public News findLastDateNews() {
		return newDao.findLastDateNews();
	}

	@Override
	public void deleteNewsById(long id) {
		newDao.remove(newDao.findById(id));
	}

	@Override
	public void editNews(NewsDto newsDto) {
		News news = NewsDtoToNews.convert(newsDto, userDao.findByEmail(newsDto.getKeyAuthor()));
		newDao.merge(news);

	}

	@Override
	public void persistNews(NewsDto newsDto) {
		News news = NewsDtoToNews.convert(newsDto, userDao.findByEmail(newsDto.getKeyAuthor()));
		newDao.persist(news);
	}

	@Override
	public NewsDto getNewsAsNewsDtoById(long id) {
		return new NewsDto(findById(id));
	}

	@Override
	public List<UrlEntityFieldAssistantMatcher> getUrlEntityFieldAssistantMatchers() {
		List<UrlEntityFieldAssistantMatcher> list = new ArrayList<>();
		getDescOrderedNews().forEach((news) -> {
			list.add(new UrlEntityFieldAssistantMatcher(news.getTitle(), Long.toString(news.getId())));
		});
		return list;
	}

}
