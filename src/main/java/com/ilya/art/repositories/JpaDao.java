package com.ilya.art.repositories;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.Dao;

/**
 * Basic JpaDao implementation.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class JpaDao<E, K> implements Dao<E, K> {

	protected Class entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	protected static Logger logger = LogManager.getLogger(JpaDao.class);

	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public void persist(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public void remove(E entity) {
		entityManager.remove(entity);
	}

	@Override
	public E getById(K id) {
		E entity = (E) entityManager.find(entityClass, id);
		if (entity == null) {
			logger.error("NotFounException :: In JpaDao while trying to find entity with id " + id);
			throw new NotFoundException();
		}
		return entity;
	}

	@Override
	public void merge(E entity) {
		entityManager.merge(entity);
	}

}
