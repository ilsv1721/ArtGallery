package com.ilya.art.repositories;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Painting;
import com.ilya.art.domain.User;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.PaintingDao;

@Repository
public class JpaPaintingDao extends JpaDao<Painting, Long> implements PaintingDao {

	@Override
	public long getNumberOfPaintings() {
		return entityManager.createNamedQuery("getCount", Long.class).getSingleResult();
	}

	@Override
	public Painting getRandomPainting() {
		int count = (int) getNumberOfPaintings();
		int randNumber = ThreadLocalRandom.current().nextInt(count);
		return entityManager.createNamedQuery("selectAll", Painting.class).setFirstResult(randNumber).setMaxResults(1)
				.getSingleResult();

	}

	@Override
	public Painting findByPath(String path) {
		TypedQuery<Painting> query = entityManager.createQuery("from Painting p where p.path = :path_search",
				Painting.class);
		query.setParameter("path_search", path);
		try {
			Painting painting = query.getSingleResult();
			return painting;
		} catch (NoResultException ex) {
			
			logger.error(ex.getClass().getName() + " :: while trying to get painting with path = " + path);
			throw new NotFoundException();
		}

	}

	@Override
	public Painting findByAuthor(User user) {
		TypedQuery<Painting> query = entityManager.createQuery("from Painting p where p.author = :author_search",
				Painting.class);
		query.setParameter("author_search", user);
		try {
			Painting painting = query.getSingleResult();
			return painting;
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to get painting with author= " + user);
			throw new NotFoundException();
		}
	}

	@Override
	public List<Painting> getAllPaintings() {
		TypedQuery<Painting> query = entityManager.createQuery("from Painting p", Painting.class);
		return query.getResultList();
	}

}
