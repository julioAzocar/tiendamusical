package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;

//controlador encargado de flujo y acciones de barra de navegacion

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class NavBarController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(NavBarController.class);//import org.apache.logging.log4j.Logger;
	
	@PostConstruct
	public void init() {
		System.out.println("iniciando controler navbar");
	}

	public void redireccionar() {
		
		try {
			CommonsUtils.redireccionar("/pages/cliente/carrito.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Error en direccionar a su Carrito");
			e.printStackTrace();
		}
		
	}
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	
}
