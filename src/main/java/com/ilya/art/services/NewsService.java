package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.News;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.dto.BasicUrlEnityMapperDto;
import com.ilya.art.dto.converters.NewsDtoToNewsConverter;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.NewsDao;
import com.ilya.art.repositories.interfaces.UserDao;
import com.ilya.art.utils.web.UrlEntityMapper;

@Service
@Transactional
public class NewsService implements com.ilya.art.services.interfaces.NewsService {

	@Autowired
	NewsDao newDao;

	@Autowired
	UserDao userDao;

	private static Logger logger = LogManager.getLogger(NewsService.class);

	@Override
	public List<NewsDto> getDescOrderedNews() {
		List<NewsDto> list = new ArrayList<>();
		newDao.getDescOrderedNews().forEach((nes) -> {
			list.add(new NewsDto(nes));
		});
		return list;
	}

	@Override
	public List<NewsDto> getAscOrderedNews() {
		List<NewsDto> list = new ArrayList<>();
		newDao.getAscOrderedNews().forEach((nes) -> {
			list.add(new NewsDto(nes));
		});
		return list;
	}

	@Override
	public News findByTitle(String title) {
		return newDao.findByTitle(title);
	}

	@Override
	public News findLastDateNews() {
		return newDao.findLastDateNews();
	}

	@Override
	public void deleteNewsById(long id) {
		try {
			newDao.remove(newDao.getById(id));
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " while trying delete news by id = " + id);
		}
	}

	@Override
	public void editNews(NewsDto newsDto) {
		try {
			News newsToEdit = newDao.getById(newsDto.getId());
			newsToEdit.setContent(newsDto.getText());
			newsToEdit.setTitle(newsToEdit.getTitle());
			newsToEdit.setWrittenBy(userDao.findByEmail(newsDto.getAuthor().getEmail()));
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " while trying edit news wirh id=" + newsDto.getId());
			throw new NotFoundException();
		}
	}

	@Override
	public void persistNews(NewsDto newsDto) {
		News news = NewsDtoToNewsConverter.convert(newsDto, userDao.findByEmail(newsDto.getAuthor().getEmail()));
		newDao.persist(news);
	}

	@Override
	public NewsDto getNewsAsNewsDtoById(long id) {
		return new NewsDto(newDao.getById(id));
	}

	@Override
	public List<UrlEntityMapper> getUrlEntityFieldAssistantMatchers() {
		List<UrlEntityMapper> list = new ArrayList<>();
		getDescOrderedNews().forEach((news) -> {
			list.add(new BasicUrlEnityMapperDto(news.getTitle(), Long.toString(news.getId())));
		});
		return list;
	}

}
