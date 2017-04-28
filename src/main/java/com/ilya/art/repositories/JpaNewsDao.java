package com.ilya.art.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.News;
import com.ilya.art.repositories.interfaces.NewsDao;

@Repository
public class JpaNewsDao extends JpaDao<News, Long> implements NewsDao {

	@Override
	public List<News> getDescOrderedNews() {
		return entityManager.createNamedQuery("descByDate", News.class).getResultList();
	}

	@Override
	public List<News> getAscOrderedNews() {
		return entityManager.createNamedQuery("ascByDate", News.class).getResultList();
	}

	@Override
	public News findByTitle(String title) {
		return entityManager.createNamedQuery("findByTitle", News.class).setParameter("searchedTitle", title)
				.getSingleResult();
	}

	@Override
	public News findLastDateNews() {
		return entityManager.createNamedQuery("descByDate", News.class).setMaxResults(1).getSingleResult();
	}

}
