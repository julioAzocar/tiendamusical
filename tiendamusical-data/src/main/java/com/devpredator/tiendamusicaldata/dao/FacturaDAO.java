package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.entities.Factura;

//interfaz define metodos crud de tabla factura
public interface FacturaDAO extends PagingAndSortingRepository<Factura, Long>{

}
