package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.entities.Rol;

public interface AdminRolesService {

	//consulta artistas
	List<Rol> consultarRoles();
	
	Rol guardarRol(Rol rol);
}
