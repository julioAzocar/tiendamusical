package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.devpredator.tiendamusicalentities.entities.Disquera;

/*
 * clase para el crud hacia tabla de disqueras
 * 
 * */

public interface DisqueraDAO extends PagingAndSortingRepository<Disquera, Long> {

	
}
