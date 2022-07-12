package com.debpredator.tiendamusicalweb.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

/*
 * mantendra informacion en sesion de usuario
 * se crea una vez por sesion
 * 
 * */

@ManagedBean
@SessionScoped
public class SessionBean {

	//objeto persona que se mantendra en sesion
	private Persona persona;
	private ArtistaAlbumDTO artistaAlbumDTO;//album selecionado en home
	//total compra por sesion
	private float totalCompra;
	
	//orden generada por Paypal
	private HttpResponse<Order> order;
	
	public float getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}

	public ArtistaAlbumDTO getArtistaAlbumDTO() {
		return artistaAlbumDTO;
	}

	public void setArtistaAlbumDTO(ArtistaAlbumDTO artistaAlbumDTO) {
		this.artistaAlbumDTO = artistaAlbumDTO;
	}

	@PostConstruct
	public void init() {
		System.out.println("Clase sesion creada");
		
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	public Persona getPersona() {
		return persona;
	}

	public void setOrder(HttpResponse<Order> response) {
		// TODO Auto-generated method stub
		this.order = response;
	}

	public HttpResponse<Order> getOrder() {
		return order;
	}



}
