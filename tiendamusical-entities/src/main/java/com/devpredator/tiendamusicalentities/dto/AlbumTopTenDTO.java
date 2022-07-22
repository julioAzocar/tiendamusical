package com.devpredator.tiendamusicalentities.dto;

import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;

//para generar grafica de top ten de album mas vendidos para admin

public class AlbumTopTenDTO {

	
	//informacion del album
	private CarritoAlbum carritoAlbum;
	
	private long cantidadSuma;

	//constructor
	public AlbumTopTenDTO() {
		
	}
	
	//constructor
	public AlbumTopTenDTO(CarritoAlbum carritoAlbum, long cantidad) {
		this.carritoAlbum = carritoAlbum;
		this.cantidadSuma = cantidad;
	}
	
	//constructor con valores
	public CarritoAlbum getCarritoAlbum() {
		return carritoAlbum;
	}

	public void setCarritoAlbum(CarritoAlbum carritoAlbum) {
		this.carritoAlbum = carritoAlbum;
	}

	public long getCantidadSuma() {
		return cantidadSuma;
	}

	public void setCantidadSuma(long cantidadSuma) {
		this.cantidadSuma = cantidadSuma;
	}
	
	
	
}
