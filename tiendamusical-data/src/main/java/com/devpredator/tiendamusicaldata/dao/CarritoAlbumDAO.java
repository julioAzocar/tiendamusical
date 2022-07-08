package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;

//interfaz que implementa metodos genericos para el crud con spring jpa
//hacia la tabla de Carrito_Album 
public interface CarritoAlbumDAO extends PagingAndSortingRepository<CarritoAlbum, Long> {

	
}
