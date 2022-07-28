package com.debpredator.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.impl.RegistroServiceImpl;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class RegistroController {

	//@ManagedProperty @Service
	//propiedad de logica negocio inyectada con jsf y spring
	@ManagedProperty("#{registroServiceImpl}")
	private RegistroServiceImpl registroServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(RegistroController.class);//import org.apache.logging.log4j.Logger;
	
	private Persona persona;
	
	@PostConstruct
	public void init() {
		LOGGER.info("controler registro iniciado");
		limpiarComponentes();
	}
	
	public void limpiarComponentes() {
		this.persona = new Persona();
	}
	
	
	public void guardar() {
		
		Persona personaRegistrada = this.registroServiceImpl.registrarPersona(persona);
		
		LOGGER.info("persona Registrada " + personaRegistrada.getNombre() + " " + personaRegistrada.getEmail());
	
		if (personaRegistrada.getIdPersona() != null) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Te has registrado correctamente");
			this.limpiarComponentes();
		}else {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un problema en su registro, intentelo mas tarde");
			
		}
		
	}
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}


	public RegistroServiceImpl getRegistroServiceImpl() {
		return registroServiceImpl;
	}


	public void setRegistroServiceImpl(RegistroServiceImpl registroServiceImpl) {
		this.registroServiceImpl = registroServiceImpl;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
