package com.devpredator.tiendamusicalentities.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCarrito")
	private Long idCarrito;
	
	@OneToOne()
	@JoinColumn(name = "idPersona")
	private Persona persona;

	@OneToMany(mappedBy = "carrito",fetch = FetchType.EAGER)
	private List<CarritoAlbum> carritosAlbum;
	
	
	public List<CarritoAlbum> getCarritosAlbum() {
		return carritosAlbum;
	}

	public void setCarritosAlbum(List<CarritoAlbum> carritosAlbum) {
		this.carritosAlbum = carritosAlbum;
	}

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
