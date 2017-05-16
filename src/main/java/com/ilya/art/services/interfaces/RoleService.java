package com.ilya.art.services.interfaces;

import java.util.List;

import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.RoleEditDto;

public interface RoleService {
	void addNewRole(RoleDto genreDto);

	void deleteGenre(RoleDto genreDto);

	void editGenre(RoleEditDto genreEditDto);

	/**
	 * 
	 * @param genre
	 * @return true - if exist; false - duplicate
	 */
	boolean validateExist(RoleDto genreDto);

	List<RoleDto> getAllDto();

}
