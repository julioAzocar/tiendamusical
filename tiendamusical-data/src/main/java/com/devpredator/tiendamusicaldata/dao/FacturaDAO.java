package com.devpredator.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.entities.Factura;
import com.devpredator.tiendamusicalentities.entities.Persona;

//interfaz define metodos crud de tabla factura
public interface FacturaDAO extends PagingAndSortingRepository<Factura, Long>{

	//consulta lista de facturas de una persona
	List<Factura> findAllByPersona(Persona persona);
	
}
