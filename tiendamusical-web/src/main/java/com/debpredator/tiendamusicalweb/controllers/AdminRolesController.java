package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Genero;
import com.devpredator.tiendamusicalentities.entities.Nacionalidad;
import com.devpredator.tiendamusicalentities.entities.Rol;
import com.devpredator.tiendamusicalentities.entities.SubGenero;
import com.devpredator.tiendamusicalservices.service.AdminRolesService;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class AdminRolesController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(AdminRolesController.class);//import org.apache.logging.log4j.Logger;
	
	
	@ManagedProperty("#{adminRolesServiceImpl}")
	private AdminRolesService adminRolesServiceImpl;
	
	private List<Rol> roles;
	
	private List<Rol> rolesFiltrados;
	
	private Rol rol;


	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler admin albums ");
 
		this.consultar();
		
		this.limpiarComponentes();
	}

	public void limpiarComponentes() {
		this.rol = new Rol();

	}
	
	public void consultar() {
		this.roles = this.adminRolesServiceImpl.consultarRoles();
		
	}

	
	//objeto selecionado en la fila de la tabla para actualizar
	public void cargarArtista(Rol rolSeleccionado) {
		this.rol = rolSeleccionado;
		
	}
	
	//objeto selecionado en la fila de la tabla para actualizar
	public void cargarRol(Rol rolSeleccionado) {
		this.rol = rolSeleccionado;
		
	}

	//guarda album
	public void guardar() {

		Rol rolGuardado = this.adminRolesServiceImpl.guardarRol(this.rol);
		
		if (rolGuardado.getIdRol() != null) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Rol guardado : " + this.rol.getNombre());
			
			//ocultar modal guadado ok
			PrimeFaces.current().executeScript("PF('dlgPerfiles').hide()");
			
			this.consultar();
			this.limpiarComponentes();
		}
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public AdminRolesService getAdminRolesServiceImpl() {
		return adminRolesServiceImpl;
	}

	public void setAdminRolesServiceImpl(AdminRolesService adminRolesServiceImpl) {
		this.adminRolesServiceImpl = adminRolesServiceImpl;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Rol> getRolesFiltrados() {
		return rolesFiltrados;
	}

	public void setRolesFiltrados(List<Rol> rolesFiltrados) {
		this.rolesFiltrados = rolesFiltrados;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
