package com.devpredator.tiendamusicalentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subgenero")
public class SubGenero extends Common{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSubGenero")
	private Long idSubGenero;
	
	@Column(name="descripcion", length = 100,nullable = false)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGenero")
	private Genero genero;

	public Long getIdSubGenero() {
		return idSubGenero;
	}

	public void setIdSubGenero(Long idSubGenero) {
		this.idSubGenero = idSubGenero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
}
