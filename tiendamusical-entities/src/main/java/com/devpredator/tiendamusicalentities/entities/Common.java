package com.devpredator.tiendamusicalentities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/*
 * campos de bd que se pueden utilizar en varias tablas
 * */


@MappedSuperclass//notacion para clase que es compartida
public class Common {

	@Column(name="fechaCreacion", nullable = false)
	private LocalDateTime fechaCreacion;
	
	@Column(name="fechaModificacion", nullable = false)
	private LocalDateTime fechaModificacion;
	
	@Column(name="estatus")
	private boolean estatus;

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
}
