package com.ilya.art.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.News;
import com.ilya.art.repositories.interfaces.NewsDao;

@Service
@Transactional
public class NewsService implements com.ilya.art.services.interfaces.NewsService {
	
	@Autowired
	NewsDao newDao;

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
	
	
	
}
