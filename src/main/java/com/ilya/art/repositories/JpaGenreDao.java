package com.ilya.art.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Genre;
import com.ilya.art.repositories.interfaces.GenreDao;

@Repository
public class JpaGenreDao extends JpaDao<Genre, Long> implements GenreDao {

	@Override
	public Genre findByGenreString(String genre) {
		return entityManager.createNamedQuery("findByGenre", Genre.class).setParameter("genre_search", genre)
				.getSingleResult();

	}

	@Override
	public List<Genre> getAllGenres() {
		return entityManager.createNamedQuery("findAll", Genre.class).getResultList();
	}

}
