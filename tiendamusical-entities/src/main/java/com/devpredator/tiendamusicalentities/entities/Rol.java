package com.devpredator.tiendamusicalentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * clase que contiene entidad de perfiles de usuario
 * */

@Entity
@Table(name="rol")
public class Rol extends Common{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	@Column(name="idRol")
	private Long idRol;
	
	
	@Column(name="nombre", length = 45,nullable = false)
	private String nombre;
	
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
}
