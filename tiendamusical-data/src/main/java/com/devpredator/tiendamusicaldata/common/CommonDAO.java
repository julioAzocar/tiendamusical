package com.devpredator.tiendamusicaldata.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
 * metodos genericos para clases 
 * crud reautilizan en todas las entidades
 * E - significa entidad a usar en crud
 * R - significa repositorio a utilizar de jpa para ejecutar CRUD
 * */
public class CommonDAO <E,R extends PagingAndSortingRepository<E, Long>>{

	@Autowired
	protected R repository;
	
	/*metodo permite consultar lista de datos paginable de una entidad
	 * desde, a partir de que valor inicia
	 * hasta, indica el limite de resultados paginados
	 * orderBy, indica apartir de que campo se ordenan
	 * return, lista de datos consultados paginados
	 * */
	
	
	public List<E> consultarListaPaginable(int desde,int hasta,String orderBy) {
		
		Pageable pageable = PageRequest.of(desde, hasta, Sort.by(orderBy));
		Page<E> page = this.repository.findAll(pageable);
		return page.getContent();
		
	} 
	
	/*
	 * persiste entidad
	 * e, entidad a persistir
	 * return, entidad persistida
	 * */
	public E guardar(E e) {
	
		return this.repository.save(e);
		
	}
	
	/*
	 * actualizar entidad
	 * e, entidad a actualizar
	 * return, entidad actualizar
	 * */
	public E actualizar(E e) {
	
		return this.repository.save(e);
		
	}
	
	/*
	 * entidad
	 * e, entidad a elimina
	 * */
	public void eliminar(E e) {
	
		this.repository.delete(e);
		
	}
	
	
}

