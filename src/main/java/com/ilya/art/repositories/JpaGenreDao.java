package com.ilya.art.repositories;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Genre;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.GenreDao;

@Repository
public class JpaGenreDao extends JpaDao<Genre, Long> implements GenreDao {

	@Override
	public Genre findByGenreString(String genrestr) {
		try {
			Genre genre = entityManager.createNamedQuery("findByGenre", Genre.class)
					.setParameter("genre_search", genrestr).getSingleResult();
			return genre;
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to get genre = " + genrestr);
			throw new NotFoundException();
		}

	}

	@Override
	public List<Genre> getAll() {
		return entityManager.createNamedQuery("findAll", Genre.class).getResultList();
	}

}
