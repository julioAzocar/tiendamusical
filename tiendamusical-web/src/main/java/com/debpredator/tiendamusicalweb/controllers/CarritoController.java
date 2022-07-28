package com.debpredator.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalservices.service.CarritoService;
import com.devpredator.tiendamusicalservices.service.HomeService;

//clase controla flujo pantalla carrito de compras 

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class CarritoController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	

	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(CarritoController.class);//import org.apache.logging.log4j.Logger;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	@PostConstruct
	public void init() {
		this.sessionBean.setPaso(0);
		System.out.println("iniciando controler carrito");
		this.CalcularTotal();
		
	}
	

	public void CalcularTotal() {
		
		float total = carritoServiceImpl.calcularTotal(this.sessionBean.getPersona().getCarrito());
		
		this.sessionBean.setTotalCompra(total);
		
		LOGGER.info("CarritoController - Calculando Total:" + total);
	}

	public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
		//se realiza llama a servico eliminar albumCarrito 
		
		LOGGER.info("Eliminando album de carrito de compras");
		
		this.carritoServiceImpl.eliminarAlbumCarrito(carritoAlbum);
		
		if (sessionBean.getPersona().getCarrito() != null) {
			this.sessionBean.getPersona().getCarrito().getCarritosAlbum().remove(carritoAlbum);
		}
		
		this.CalcularTotal();
		
	}
	
	//en carrito al actualizar cantidad recalcula totales
	public void actualizarCantidadCarrito(CarritoAlbum carritoAlbum) {
		
		float total = this.carritoServiceImpl.actualizarAlbumCantidad(carritoAlbum, this.sessionBean.getPersona().getCarrito());
		
		this.sessionBean.setTotalCompra(total);
	}
	
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

	
}
