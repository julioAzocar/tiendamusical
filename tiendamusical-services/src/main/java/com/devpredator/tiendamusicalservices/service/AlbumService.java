package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;

public interface AlbumService {
	List<AlbumTopTenDTO> consultarAlbumsTopTenDTO();
	
}
