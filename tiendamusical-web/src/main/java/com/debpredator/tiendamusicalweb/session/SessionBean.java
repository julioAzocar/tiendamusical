package com.debpredator.tiendamusicalweb.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.devpredator.tiendamusicalentities.entities.Persona;

/*
 * mantendra informacion en sesion de usuario
 * se crea una vez por sesion
 * 
 * */

@ManagedBean
@SessionScoped
public class SessionBean {

	//objeto persona que se mantendra en sesion
	private Persona persona;
	

	@PostConstruct
	public void init() {
		System.out.println("Clase sesion creada");
		
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	public Persona getPersona() {
		return persona;
	}



}
