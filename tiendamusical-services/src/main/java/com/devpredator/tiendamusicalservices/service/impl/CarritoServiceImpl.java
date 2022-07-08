package com.devpredator.tiendamusicalservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.CarritoAlbumDAO;
import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.Carrito;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalservices.service.CarritoService;


//clase implementa metodos de logica de negocio de CarritoService para el carrito de compras

@Service
public class CarritoServiceImpl implements CarritoService {

	@Autowired
	private CarritoAlbumDAO carritoAlbumDAO;
	
	@Override
	public CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO, Carrito carrito, int cantidadAlbumSeleccionada) {
		// TODO Auto-generated method stub

		CarritoAlbum carritoAlbum = new CarritoAlbum();
		carritoAlbum.setAlbum(artistaAlbumDTO.getAlbum());
		carritoAlbum.setCarrito(carrito);
		carritoAlbum.setCantidad(cantidadAlbumSeleccionada);
		carritoAlbum.setEstatus("PENDIENTE");
		
		this.carritoAlbumDAO.save(carritoAlbum);
		
		return carritoAlbum;
		
	}

	//permite calcular total de compra
	@Override
	public float calcularTotal(Carrito carrito) {
		float total = 0.0F;

		for (CarritoAlbum carritoAlbum : carrito.getCarritosAlbum()) {
			total+= carritoAlbum.getAlbum().getValor() * carritoAlbum.getCantidad();
		}
		
		return total;
	}

	//permite eliminar album de carrito
	@Override
	public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
		// TODO Auto-generated method stub
		this.carritoAlbumDAO.delete(carritoAlbum);
	}

	@Override
	public float actualizarAlbumCantidad(CarritoAlbum carritoAlbum, Carrito carrito) {
		
		
		this.carritoAlbumDAO.save(carritoAlbum);
		
		return this.calcularTotal(carrito);
		
		
	}


}
