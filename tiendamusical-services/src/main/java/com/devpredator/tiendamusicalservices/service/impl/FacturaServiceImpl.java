package com.devpredator.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.FacturaDAO;
import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.FacturaService;
import com.paypal.orders.Order;

//clase implementa metodos de logica de negocio para tabla factura 

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;
	
	@Override
	public Factura guardarFactura(Factura factura, Order order, Persona persona) {
		
		double envio = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shipping().value());
		double envioDescuento = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shippingDiscount().value());
		double handling = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().handling().value());
		double total = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().itemTotal().value());
		double impuestoTotal = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().taxTotal().value());

		
		factura.setOrderId(order.id());
		factura.setFechaCreacion(LocalDateTime.now());
		factura.setFechaModificacion(LocalDateTime.now());
		factura.setEstatus(true);
		factura.setImpuestoTotal(impuestoTotal);
		factura.setEnvio(envio);
		factura.setEnvioDescuento(envioDescuento);
		factura.setHandling(handling);
		factura.setTotal(total);
		factura.setDireccion(order.payer().addressPortable().addressLine1() + ", " + order.payer().addressPortable().addressLine2());
		factura.setCodigoPostal(order.payer().addressPortable().postalCode());
		factura.setPais(order.payer().addressPortable().adminArea1());
		factura.setCiudad(order.payer().addressPortable().adminArea2());
		factura.setDivisa(order.purchaseUnits().get(0).amountWithBreakdown().currencyCode());
		factura.setPersona(persona);
		
		return this.facturaDAO.save(factura);
		
		
	}

	@Override
	public List<Factura> consultarFacturaPerson(Persona persona) {
		// TODO Auto-generated method stub
		return this.facturaDAO.findAllByPersona(persona);
	}

}
