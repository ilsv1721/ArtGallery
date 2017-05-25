package com.ilya.art.repositories;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.exceptions.ExhibitionNotFoundException;
import com.ilya.art.repositories.interfaces.ExhibitionDao;

@Repository
public class JpaExhibitionDao extends JpaDao<Exhibition, Long> implements ExhibitionDao {

	@Override
	public Exhibition findExhibition(String title) {
		try {
			Exhibition exhib = entityManager.createNamedQuery("Exhibition.findByTitle", Exhibition.class)
					.setParameter("searchedTitle", title).getSingleResult();
			return exhib;
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " :: while trying to get exhibition with titile = " + title);
			throw new ExhibitionNotFoundException(title);
		}

	}

	@Override
	public List<Exhibition> getAll() {
		return entityManager.createNamedQuery("Exhibition.findAll", Exhibition.class).getResultList();
	}

}
