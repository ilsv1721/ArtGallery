package com.ilya.art.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilya.art.domain.Role;
import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.RoleEditDto;
import com.ilya.art.repositories.interfaces.RoleDao;

@Service
@Transactional
public class RoleService implements com.ilya.art.services.interfaces.RoleService {

	private static Logger logger = LogManager.getLogger(RoleService.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public void addNewRole(RoleDto roleDto) {
		try {
			roleDao.getRole(roleDto.getRole());
			throw new EntityExistsException();
		} catch (NoResultException ex) {
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
		} catch (NoResultException ex) {
			logger.error(ex.getClass().getName() + " while trying to delte role with id =" + roleDto.getId());
			throw new EntityNotFoundException();

		}

	}

	@Override
	public void editGenre(RoleEditDto editRoleDto) {
		try {
			Role editRole = roleDao.getRole(editRoleDto.getRole());
			editRole.setAuthority(editRoleDto.getNewValue());
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public boolean validateExist(RoleDto genreDto) {
		try {
			roleDao.getRole(genreDto.getRole());
			return true;
		} catch (NoResultException ex) {
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
