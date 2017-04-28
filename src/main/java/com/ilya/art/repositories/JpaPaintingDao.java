package com.ilya.art.repositories;

import org.springframework.stereotype.Repository;
import com.ilya.art.domain.Painting;
import com.ilya.art.repositories.interfaces.PaintingDao;

@Repository
public class JpaPaintingDao extends JpaDao<Painting, Long> implements PaintingDao {

}
