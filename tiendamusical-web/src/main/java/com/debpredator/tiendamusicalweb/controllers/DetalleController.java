package com.debpredator.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalservices.service.CarritoService;

//controla flujo de pantalla detalle.xhtml


@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class DetalleController {

	//cantidad de compra 
	private int cantidadAlbumSeleccionada;
	
	//objeto contiene logica de negocio carrito
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}


	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}


	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}


	public int getCantidadAlbumSeleccionada() {
		return cantidadAlbumSeleccionada;
	}


	public void setCantidadAlbumSeleccionada(int cantidadAlbumSeleccionada) {
		this.cantidadAlbumSeleccionada = cantidadAlbumSeleccionada;
	}


	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(DetalleController.class);//import org.apache.logging.log4j.Logger;
	
	@PostConstruct
	public void init() {
		System.out.println("controler detalle iniciado");
		
		this.cantidadAlbumSeleccionada=1;
		
	}
	
	
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO){
		LOGGER.info("Agregando album a carrito, Cantidad seleccionada : " + this.cantidadAlbumSeleccionada);
		
		CarritoAlbum carritosAlbumAgregado = this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDTO, sessionBean.getPersona().getCarrito(), cantidadAlbumSeleccionada);
		
		this.sessionBean.getPersona().getCarrito().getCarritosAlbum().add(carritosAlbumAgregado);
		
	}
}
