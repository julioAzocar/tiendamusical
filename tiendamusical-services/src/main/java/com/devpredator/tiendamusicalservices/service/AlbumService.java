package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.devpredator.tiendamusicalentities.entities.Album;

public interface AlbumService {
	
	List<AlbumTopTenDTO> consultarAlbumsTopTenDTO();
	
	List<Album> consultarAlbums();
	
}
