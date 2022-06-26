package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.entities.Persona;

/*
 * 
 * clase dao realiza crud con spring jpa tabla de persona
 * CrudRepositoty hace los crud
 * PagingAndSortingRepository pagina el resultado de la query
 * */


public interface PersonaDAO extends PagingAndSortingRepository<Persona, Long>{

	/*
	 * metodo consulta usuario que hace login
	 * 
	 * */
	@Query("SELECT p FROM Persona p WHERE p.usuario = ?1 AND p.password = ?2")
	Persona findByUsuarioAndPassword(String usuario,String password);
	
	//insert into rol (nombre,fechacreacion,estatus)values('admin',NOW(),1);
	//select * from rol;
	
	
	

}
