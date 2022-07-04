package com.debpredator.tiendamusicalweb.utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

//compartir funciones globales o comunes entre clase del proyecto


public class CommonsUtils {

	/*
	permite mostrar mensaje a usuario
	@param severity {@link severiry} tipo de mensaje
	@param sumary  {@link String } titulo
	@param detail {@link String} mensaje
	*/
	
	public static void mostrarMensaje(Severity severity, String sumary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(severity, sumary, detail));
		
	}
	
	
	/**
	 * permite redireccionar entre pantallas de aplicativo
	 * con throws pasa el error para manipularlo afuera
	 * */
	
	public static void redireccionar(String url) throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String contextPath = ec.getRequestContextPath();
		ec.redirect(contextPath + url);
		
	}
	
	
}
