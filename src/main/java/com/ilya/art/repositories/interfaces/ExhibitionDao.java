package com.ilya.art.repositories.interfaces;

import java.util.List;

import com.ilya.art.domain.Exhibition;

public interface ExhibitionDao extends Dao<Exhibition, Long>{
	Exhibition findExhibition(String title);
	List<Exhibition> getAll();
		
}
