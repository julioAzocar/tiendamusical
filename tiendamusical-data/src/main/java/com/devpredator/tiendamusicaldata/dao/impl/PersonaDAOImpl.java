package com.devpredator.tiendamusicaldata.dao.impl;

import com.devpredator.tiendamusicaldata.common.CommonDAO;
import com.devpredator.tiendamusicaldata.dao.PersonaDAO;
import com.devpredator.tiendamusicalentities.entities.Persona;

/*
 * clase implementa crud generico y funciones de interfaz de personaDAO
 * 
 * */
public class PersonaDAOImpl extends CommonDAO<Persona,PersonaDAO>{

	/*
	 * permite consultar persona por usuario y password
	 * */
	
	public Persona findUsuarioAndPassword(String usuario,String password) {
		return this.repository.findByUsuarioAndPassword(usuario, password);
		
	}
	
}
