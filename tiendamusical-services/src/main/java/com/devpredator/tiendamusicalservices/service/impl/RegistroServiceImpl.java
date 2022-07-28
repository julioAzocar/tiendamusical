package com.devpredator.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.PersonaDAO;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalentities.entities.Rol;
import com.devpredator.tiendamusicalservices.service.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private PersonaDAO personaDAO;
	
	@Override
	public Persona registrarPersona(Persona persona) {
		
		persona.setFechaCreacion(LocalDateTime.now());
		persona.setEstatus(true);
		//rol por defecto para cliente es 4 y admin es 3
		persona.setRol(new Rol());
		persona.getRol().setIdRol(4L);
		
		return personaDAO.save(persona);
		
		
	}

	
	
}
