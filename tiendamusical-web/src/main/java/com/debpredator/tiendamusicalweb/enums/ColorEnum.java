package com.debpredator.tiendamusicalweb.enums;

//enumeracion colores de grafica 
public enum ColorEnum {
	COLOR_ROJO(0,"rgba(244, 43, 69, 0.7)"),
	COLOR_AMARILLO(1,"rgba(244, 207, 69, 0.7)"),
	COLOR_VERDE(2,"rgba(92, 207, 69, 0.7)"),
	COLOR_MORADO(3,"rgba(197, 95, 230, 0.7)"),
	COLOR_ROSADO(4,"rgba(255, 18, 126, 0.3)"),
	COLOR_ROJO2(5,"rgba(244, 43, 69, 0.7)"),
	COLOR_AMARILLO3(6,"rgba(244, 207, 69, 0.7)"),
	COLOR_VERDE4(7,"rgba(92, 207, 69, 0.7)"),
	COLOR_MORADO5(8,"rgba(197, 95, 230, 0.7)"),
	COLOR_ROSADO6(9,"rgba(255, 18, 126, 0.3)");
	
	
	private int valor;
	private String descripcion;
	
	ColorEnum(int valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
		
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
