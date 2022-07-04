package com.debpredator.tiendamusicalweb.session;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.debpredator.tiendamusicalweb.utils.CommonsUtils;

//cerra sesion usuario y redireccionar a login

@ManagedBean
@ViewScoped
public class SessionClosed {
	
	public void cerrarSesion() {
		
		try {
			
			//elimina beans de sesion
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();;
			
			CommonsUtils.redireccionar("/login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "¡UPS!", "Error al intentar salir de sesion, favor intentar mas tarde");
			e.printStackTrace();
		}
		
	}
	
	
}
