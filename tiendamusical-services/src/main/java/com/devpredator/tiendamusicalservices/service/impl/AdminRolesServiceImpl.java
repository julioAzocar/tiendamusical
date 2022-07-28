package com.devpredator.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.ArtistaDAO;
import com.devpredator.tiendamusicaldata.dao.RolDAO;
import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Rol;
import com.devpredator.tiendamusicalservices.service.AdminRolesService;

@Service
public class AdminRolesServiceImpl implements AdminRolesService {

	@Autowired
	private RolDAO rolDAO;
	
	@Override
	public List<Rol> consultarRoles() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("nombre"));
		Page<Rol> page = this.rolDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Rol guardarRol(Rol rol) {
		if (rol.getIdRol() != null) {
			rol.setFechaModificacion(LocalDateTime.now());
		}else {
			rol.setFechaCreacion(LocalDateTime.now());
			rol.setEstatus(true);
		}

		
		return this.rolDAO.save(rol);
	}

}
