package com.devpredator.tiendamusicalentities.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * clase que contiene entidad de personas
 * */
@Entity
@Table(name="persona")
public class Persona extends Common{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPersona")
	private Long idPersona;
	
	@Column(name="numeroIdentificacion", length = 12)
	private String numeroIdentificacion;
	
	@Column(name="nombre", length = 100,nullable = false)
	private String nombre;
	
	@Column(name="primerApellido", length = 100,nullable = false)
	private String primerApellido;
	
	@Column(name="segundoApellido", length = 45,nullable = false)
	private String segundoApellido;
	
	@Column(name="email", length = 200,nullable = false)
	private String email;
	
	@Column(name="imagen", length = 100)
	private String imagen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoIdentificacion")
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(name="usuario", length = 45,nullable = false)
	private String usuario;
	
	@Column(name="password", length = 45,nullable = false)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRol")
	private Rol rol;

	
	
	
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	@OneToOne(mappedBy = "persona")
	private Carrito carrito;
	
	
	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
