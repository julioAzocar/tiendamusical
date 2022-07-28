/**
 * 
 */
package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.LoginService;

/**
 * @author zf701299
 *controlador encargado de flujo de pantalla login.xhtml
 */

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class loginController implements Serializable {
	/**
	 * agregar implements Serializable de,  import java.io.Serializable;
	 * add generate serial version id->hover en loginController eleigir opcion
	 * genera una instancia de sesion por cada usuario
	 */
	private static final long serialVersionUID = -6832572449982879476L;
	
	
	private String usuario; //usuario de persona
	private String password;//contraseña de persona 
	
	//@ManagedProperty @Service
	//propiedad de logica negocio inyectada con jsf y spring
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService loginServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(loginController.class);//import org.apache.logging.log4j.Logger;
	
	
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
		
		try {
			Persona personaConsultada = this.loginServiceImpl.consultarUsuarioLogin(this.usuario, this.password);

			if (personaConsultada != null) {
				
				if (personaConsultada.getRol().getIdRol() == 4 && personaConsultada.getCarrito() != null) {
					  List<CarritoAlbum> carritoAlbumFiltrados = personaConsultada.getCarrito().getCarritosAlbum().stream().filter(ca ->
						ca.getEstatus().equals("PENDIENTE")).collect(Collectors.toList());
					
					  //solo carritos pendientes
					  personaConsultada.getCarrito().setCarritosAlbum(carritoAlbumFiltrados);
					  
					  LOGGER.info("INFO");
				}

				this.sessionBean.setPersona(personaConsultada);// sesion persona logueada

				CommonsUtils.redireccionar("/pages/commons/dashboard.xhtml");
			} else {
				CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "¡UPS!",
						"El usuario y/o contraseña son incorrectos");

			}

		} catch (IOException e) {
			strMsg = e.getMessage();
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "¡ERROR!", strMsg);
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
