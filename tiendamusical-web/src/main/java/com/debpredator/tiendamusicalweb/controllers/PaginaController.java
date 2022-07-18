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

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class PaginaController {

	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(PaginaController.class);//import org.apache.logging.log4j.Logger;
	
	@PostConstruct
	public void init() {
		//System.out.println("Home inicializado");
		
		LOGGER.info("Pagina Controller");

	}
	
	public void irPagina(String pagina) {
		
		try {
			CommonsUtils.redireccionar(pagina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡ERROR!",e.getMessage());
			LOGGER.error(" : " +  e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
