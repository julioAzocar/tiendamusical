package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.client.ReportesServiceClient;
import com.devpredator.tiendamusicalservices.service.CarritoService;
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

	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	@ManagedProperty("#{reportesServiceClient}")
	private ReportesServiceClient reportesServiceClient;
	
	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}

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
		
		
		//si se genra factura en bd se actualiza carrito y se asigna factuta generada
		if (facturaGenerada != null) {
			
			boolean productosCarritoActualizados = 
					this.carritoServiceImpl.actualizarCarritoAlbum(this.sessionBean.getPersona().getCarrito().getCarritosAlbum(), facturaGenerada);
			
			if (productosCarritoActualizados) {
				
				//genera reporte con jasper en dropbox
				String cliente = persona.getNombre() + persona.getPrimerApellido() + persona.getSegundoApellido();
				Response response = reportesServiceClient.generarReporte(order.result().id(), persona.getEmail(), cliente);
				
				LOGGER.info("Response: " + response.getStatus());
				
				this.sessionBean.getPersona().getCarrito().setCarritosAlbum(new ArrayList<CarritoAlbum>());
				this.sessionBean.setTotalCompra(0);
				
				cambiarPasos("/pages/cliente/confirmacion.xhtml", 2);
				
			}else {
				
			}
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Se ha generado la factura exitosamente");
		}else {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "OK!", "No se genero la factura");
		}
		
	}
	
	//metodo permite redireccionar al siguiente paso de compra
	public void cambiarPasos(String url, int paso) {
		try {
			
			this.sessionBean.setPaso(paso);
			
			CommonsUtils.redireccionar(url);
		} catch (IOException e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡UPS!",
					"Hubo un problema al trata de ingresar al siguiente paso de la compra");
			LOGGER.error("Ver Detalle : " +  e.getMessage());
			e.printStackTrace();
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

	public ReportesServiceClient getReportesServiceClient() {
		return reportesServiceClient;
	}

	public void setReportesServiceClient(ReportesServiceClient reportesServiceClient) {
		this.reportesServiceClient = reportesServiceClient;
	}

}
