/**
 * 
 */
package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Service;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.LoginService;

/**
 * @author zf701299
 *controlador encargado de flujo de pantalla login.xhtml
 */

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.view.ViewScoped;
public class loginController {
	private String usuario; //usuario de persona
	private String password;//contraseña de persona 
	
	//@ManagedProperty @Service
	//propiedad de logica negocio inyectada con jsf y spring
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService loginServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	

	
	public SessionBean getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}


	public LoginService getLoginServiceImpl() {
		return loginServiceImpl;
	}


	public void setLoginServiceImpl(LoginService loginServiceImpl) {
		this.loginServiceImpl = loginServiceImpl;
	}


	@PostConstruct
	public void init() {
		System.out.println("controler login iniciado");
	}
	
	
	//permite ingresar a pantalla principal
	public void entrar() {
		
		String strMsg = "";
		
			Persona personaConsultada = this.loginServiceImpl.consultarUsuarioLogin(this.usuario, this.password);
			
			if (personaConsultada != null) {
				
				try {
					
					this.sessionBean.setPersona(personaConsultada);//sesion persona logueada
				
					CommonsUtils.redireccionar("/pages/commons/dashboard.xhtml");
				} catch (IOException e) {
					strMsg = e.getMessage();
					CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡ERROR!",strMsg);
				}
			}else {
				CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡UPS!","El usuario y/o contraseña son incorrectos");
			
			}
			


	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
