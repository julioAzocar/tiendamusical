package com.devpredator.tiendamusicalservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.PersonaDAO;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.LoginService;

/*
 * implementa logina de negocio pantalla login
 * */

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PersonaDAO personaDAOImpl;
	
	@Override
	public Persona consultarUsuarioLogin(String usuario, String password) {
		// TODO Auto-generated method stub
		return this.personaDAOImpl.findByUsuarioAndPassword(usuario, password);
	}

	
	
	
	
}
