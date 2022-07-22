package com.debpredator.tiendamusicalweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalservices.service.FacturaService;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class MisComprasController {
	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(MisComprasController.class);//import org.apache.logging.log4j.Logger;
	
	@ManagedProperty("#{facturaServiceImpl}")
	private FacturaService facturaServiceImpl;
	
	private List<Factura> facturas;
	private List<CarritoAlbum> carritosAlbum;
	
	public List<CarritoAlbum> getCarritosAlbum() {
		return carritosAlbum;
	}

	public void setCarritosAlbum(List<CarritoAlbum> carritosAlbum) {
		this.carritosAlbum = carritosAlbum;
	}

	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler MisCompras");
		this.consultaFacturasPorPersona();
	}

	public void consultaFacturasPorPersona() {
		LOGGER.info("consultando facturas de " + this.sessionBean.getPersona().getNombre());
		this.facturas = this.facturaServiceImpl.consultarFacturaPerson(this.sessionBean.getPersona());
		
	}
	
	//permite mostrar dettale de compra
	public void mostrarDetalle(List<CarritoAlbum> carritosAlbum) {
		this.carritosAlbum = carritosAlbum;
	}
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public FacturaService getFacturaServiceImpl() {
		return facturaServiceImpl;
	}

	public void setFacturaServiceImpl(FacturaService facturaServiceImpl) {
		this.facturaServiceImpl = facturaServiceImpl;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	
	
}
