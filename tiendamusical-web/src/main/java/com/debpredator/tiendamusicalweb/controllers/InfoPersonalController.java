package com.debpredator.tiendamusicalweb.controllers;

import java.time.LocalDateTime;

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
import com.devpredator.tiendamusicalservices.service.CarritoService;
import com.devpredator.tiendamusicalservices.service.PersonaService;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class InfoPersonalController {
	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(InfoPersonalController.class);//import org.apache.logging.log4j.Logger;
	
	@ManagedProperty("#{personaServiceImpl}")
	private PersonaService personaServiceImpl;
	                            
	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler Informacion Personal");
	
	}

	public void actualizarPersona() {
		Persona personaActualizada = null;
		
		try {
			this.sessionBean.getPersona().setFechaModificacion(LocalDateTime.now());
			personaActualizada = this.personaServiceImpl.actualizarPersona(this.sessionBean.getPersona());
			
			if (personaActualizada != null) {
				this.sessionBean.setPersona(personaActualizada);
				CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Informacion actualizada, reicinie sesion para aplicar los cambios");
			}else {
				CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!!", "Informacion no actualizada, intente mas tarde");
			}
			
		} catch (Exception e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!!", "Error de sistema, intente mas tarde");
			LOGGER.error(e.getMessage());
			
		}
		
		

	}
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public PersonaService getPersonaServiceImpl() {
		return personaServiceImpl;
	}

	public void setPersonaServiceImpl(PersonaService personaServiceImpl) {
		this.personaServiceImpl = personaServiceImpl;
	}


	
	
}
