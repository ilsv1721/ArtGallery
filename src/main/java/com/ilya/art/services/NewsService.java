package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.News;
import com.ilya.art.dto.BasicUrlEnityMapperDto;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.dto.converters.NewsDtoToNewsConverter;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.NewsDao;
import com.ilya.art.repositories.interfaces.UserDao;
import com.ilya.art.utils.web.UrlEntityMapper;

@Service
@Transactional
public class NewsService implements com.ilya.art.services.interfaces.NewsService {

	@Autowired
	NewsDao newsDao;

	@Autowired
	UserDao userDao;

	private static Logger logger = LogManager.getLogger(NewsService.class);

	@Override
	public News getById(Long id) {
		try {
			News news = newsDao.getById(id);
			return news;
		} catch (NotFoundException ex) {
			logger.error("NotFoundException :: while trying to find news with id" + id);
			throw new NotFoundException();
		}
	}

	@Override
	public News getNewsByTitle(String title) {
		try {
			News news = newsDao.findByTitle(title);
			return news;
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to find news with " + title);
			throw new NotFoundException();
		}

	}

	@Override
	public List<NewsDto> getDescOrderedNews() {
		List<NewsDto> list = new ArrayList<>();
		newsDao.getDescOrderedNews().forEach((nes) -> {
			list.add(new NewsDto(nes));
		});
		return list;
	}

	@Override
	public List<NewsDto> getAscOrderedNews() {
		List<NewsDto> list = new ArrayList<>();
		newsDao.getAscOrderedNews().forEach((nes) -> {
			list.add(new NewsDto(nes));
		});
		return list;
	}

	@Override
	public void deleteNewsById(long id) {
		try {
			newsDao.remove(getById(id));
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " while trying delete news by id = " + id);
		}
	}

	@Override
	public void editNews(NewsDto newsDto) {
		try {
			News newsToEdit = getById(newsDto.getId());
			newsToEdit.setContent(newsDto.getText());
			newsToEdit.setTitle(newsToEdit.getTitle());
			newsToEdit.setWrittenBy(userDao.findByEmail(newsDto.getAuthor().getEmail()));
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " while trying edit news wirh id=" + newsDto.getId());
			throw new NotFoundException();
		}
	}

	@Override
	public void persistNews(NewsDto newsDto) {
		News news = NewsDtoToNewsConverter.convert(newsDto, userDao.findByEmail(newsDto.getAuthor().getEmail()));
		newsDao.persist(news);
	}

	@Override
	public NewsDto getNewsAsNewsDtoById(long id) {
		return new NewsDto(getById(id));
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
