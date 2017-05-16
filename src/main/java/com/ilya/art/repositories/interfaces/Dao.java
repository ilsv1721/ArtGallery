package com.ilya.art.repositories.interfaces;

public interface Dao<E, K> {
	void persist(E entity);

	void remove(E entity);

	E getById(K id);

	void merge(E entity);

}
