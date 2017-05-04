package com.ilya.art.repositories.interfaces;

import com.ilya.art.domain.Painting;

public interface PaintingDao extends Dao<Painting, Long> {

	long getNumberOfPaintings();

	Painting getRandomPainting();

}
