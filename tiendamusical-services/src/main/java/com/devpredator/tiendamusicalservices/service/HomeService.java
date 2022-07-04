package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;

//interfaz contrato usado en pantalla home

public interface HomeService {
	
	//consulta album en filtro de home 
	List<ArtistaAlbumDTO> consultarAlbumFiltro(String filtro);
	
	
	
}
