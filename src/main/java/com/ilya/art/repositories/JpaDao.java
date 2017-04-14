package com.ilya.art.repositories;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ilya.art.repositories.interfaces.Dao;

/**
 * This class represents basic Jpa DAO functionality with underneath usage of
 * hardcoded reflection API.
 * 
 * @author ilya
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class JpaDao<E, K> implements Dao<E, K> {

	protected Class entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

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
	public E findById(K id) {
		return (E) entityManager.find(entityClass, id);
	}

	@Override
	public void merge(E entity) {
		entityManager.merge(entity);
	}
	


}
