package com.ilya.art.services.interfaces;

public interface BasicService<E, K> {

	public void persist(E entity);

	public void remove(E entity);

	public E findById(K key);

	public void merge(E entity);

}
