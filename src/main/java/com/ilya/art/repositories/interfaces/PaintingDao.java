package com.ilya.art.repositories.interfaces;

import java.util.List;

import com.ilya.art.domain.Painting;
import com.ilya.art.domain.User;

public interface PaintingDao extends Dao<Painting, Long> {

	long getNumberOfPaintings();

	Painting getRandomPainting();

	Painting findByPath(String Path);

	Painting findByAuthor(User uesr);
	
	List<Painting> getAllPaintings();

}
