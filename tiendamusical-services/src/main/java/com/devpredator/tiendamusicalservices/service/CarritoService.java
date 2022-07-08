package com.devpredator.tiendamusicalservices.service;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.Carrito;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;

//interfaz que define metodos logica de negocio para carrito de compras 
public interface CarritoService {

	//permite guardar los album en carrito de compras
	CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO,Carrito  carrito, int cantidadAlbumSeleccionada);
	
	//calcula total compra
	float calcularTotal(Carrito carrito);
	
	//permite eliminar album de carrito
	void eliminarAlbumCarrito(CarritoAlbum carritoAlbum);
	
	//actualiza cantidad de album
	float actualizarAlbumCantidad(CarritoAlbum carritoAlbum,Carrito carrito);
	
}
