package com.devpredator.tiendamusicalservices.enums;


//ejemplo uso 
//RolEnum.CLIENTE.getClave();

public enum RolEnum {


	CLIENTE(4L, "CLIENTE"),
	ADMIN(3L, "ADMIN");
	
	
	/*
	 * clave de perfil
	 */
	private Long clave;
	
	/*
	 * descripcion de perfil
	 */
	private String descripcion;
	
	
	//contructor inicializa variables de enumeracion
	private RolEnum(Long clave,String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;
	}


	public Long getClave() {
		return clave;
	}


	public void setClave(Long clave) {
		this.clave = clave;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
