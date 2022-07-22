package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.paypal.orders.Order;

//define metodos logica de negocio para factura

public interface FacturaService {

	//factura generada en compra
	//order de compra
	//persona de factura
	Factura guardarFactura(Factura factura, Order order,Persona persona);
	
	//consulta factura de persona
	List<Factura> consultarFacturaPerson(Persona persona);
	
	
	
	
}
