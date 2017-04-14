package com.ilya.art.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.repositories.interfaces.ExhibitionDao;

@Repository
public class JpaExhibitionDao extends JpaDao<Exhibition, Long> implements ExhibitionDao {

	@Override
	public Exhibition findExhibition(String title) {
		return entityManager.createNamedQuery("Exhibition.findByTitle", Exhibition.class)
				.setParameter("searchedTitle", title).getSingleResult();
	}

	@Override
	public List<Exhibition> findAll() {
		return entityManager.createNamedQuery("Exhibition.findAll", Exhibition.class).getResultList();
	}

}
