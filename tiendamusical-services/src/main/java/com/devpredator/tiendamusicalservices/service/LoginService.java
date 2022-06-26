package com.devpredator.tiendamusicalservices.service;

import com.devpredator.tiendamusicalentities.entities.Persona;

/*
 * interfaz con logica de negocio para inicio de sesion de persona 
 * */
public interface LoginService {

	//servicio consulta usuario login
	Persona consultarUsuarioLogin(String usuario,String password);
	
	
}
