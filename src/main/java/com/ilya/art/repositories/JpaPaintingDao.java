package com.ilya.art.repositories;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Painting;
import com.ilya.art.repositories.interfaces.PaintingDao;

@Repository
public class JpaPaintingDao extends JpaDao<Painting, Long> implements PaintingDao {

	@Override
	public long getNumberOfPaintings() {
		return entityManager.createNamedQuery("getCount", Long.class).getSingleResult();
	}

	// This is quite shiity, but count returns long in jpa/hibernate but setting
	// first result has int api.
	@Override
	public Painting getRandomPainting() {
		int count = (int) getNumberOfPaintings();
		int randNumber = ThreadLocalRandom.current().nextInt(count);
		return entityManager.createNamedQuery("selectAll", Painting.class).setFirstResult(randNumber).setMaxResults(1)
				.getSingleResult();

	}

}
