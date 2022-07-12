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
import com.devpredator.tiendamusicalservices.service.CarritoService;

//controlador que maneja flujo de cambio de pasos en proceso de compra 

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class PasosController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;

	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(PasosController.class);//import org.apache.logging.log4j.Logger;
	
	@PostConstruct
	public void init() {
		
		LOGGER.info("iniciando controler pasos");

	}
	//metodo permite redireccionar al siguiente paso de compra
	public void cambiarPasos(String url, int paso) {
		try {
			
			this.sessionBean.setPaso(paso);
			
			CommonsUtils.redireccionar(url);
		} catch (IOException e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡UPS!",
					"Hubo un problema al trata de ingresar al siguiente paso de la compra");
			LOGGER.error("Ver Detalle : " +  e.getMessage());
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
