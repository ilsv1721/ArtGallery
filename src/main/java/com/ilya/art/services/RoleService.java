package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.Role;
import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.RoleEditDto;
import com.ilya.art.exceptions.NotFoundException;
import com.ilya.art.repositories.interfaces.RoleDao;

@Service
@Transactional
public class RoleService implements com.ilya.art.services.interfaces.RoleService {

	private static Logger logger = LogManager.getLogger(RoleService.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public void addNewRole(RoleDto roleDto) {
		if (validateExist(roleDto)) {
			logger.error("Dupplicate role " + roleDto.getRole());
			throw new EntityExistsException();
		} else {
			roleDao.persist(new Role(roleDto.getRole()));
		}
	}

	@Override
	public void deleteGenre(RoleDto roleDto) {
		try {
			Role roleToDelete = roleDao.getRole(roleDto.getRole());
			roleToDelete.getUsersWithThisRole().forEach((user) -> {
				user.getRoles().remove(roleToDelete);
			});
			roleDao.remove(roleToDelete);
		} catch (NotFoundException ex) {
			logger.error(ex.getClass().getName() + " while trying to delte role with id =" + roleDto.getId());
			throw new NotFoundException();

		}

	}

	@Override
	public void editGenre(RoleEditDto editRoleDto) {
		try {
			Role editRole = roleDao.getRole(editRoleDto.getRole());
			editRole.setAuthority(editRoleDto.getNewValue());
		} catch (NotFoundException ex) {
			throw new NotFoundException();
		}
	}

	@Override
	public boolean validateExist(RoleDto roleDto) {
		try {
			roleDao.getRole(roleDto.getRole());
			return true;
		} catch (NotFoundException ex) {
			return false;
		}
	}

	@Override
	public List<RoleDto> getAllDto() {
		List<RoleDto> rolesDto = new ArrayList<>();
		roleDao.getAll().forEach((role) -> {
			rolesDto.add(new RoleDto(role));
		});
		return rolesDto;

	}

}
