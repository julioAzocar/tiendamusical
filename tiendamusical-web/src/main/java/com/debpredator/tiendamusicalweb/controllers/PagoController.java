package com.debpredator.tiendamusicalweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.FacturaService;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

//controlador genera factura y reporte con web service
//controla flujo compra de cliente 

@ManagedBean   //javax.faces.bean.ManagedBean;
@ViewScoped    //import javax.faces.bean.ViewScoped;
public class PagoController {

	
	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(PagoController.class);//import org.apache.logging.log4j.Logger;
	
	@ManagedProperty("#{facturaServiceImpl}")
	private FacturaService facturaServiceImpl;

	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler Pago");

	}
	
	//permite guardar factura y generar orden de compra
	public void guardarFactura() {
		LOGGER.info("iniciando guardar factura");
		
		//respuesta paypal orden de compra
		HttpResponse<Order> order= this.sessionBean.getOrder();
		Persona persona = this.sessionBean.getPersona();
		
		//ejecuta funcion que guarda factura y orden del cliente
		Factura factura = new Factura();
		Factura facturaGenerada = this.facturaServiceImpl.guardarFactura(factura, order.result(), persona);
		
		if (facturaGenerada != null) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Se ha generado la factura exitosamente");
		}else {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "OK!", "No se genero la factura");
		}
		
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

}
