package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.entities.Album;
import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Disquera;

public interface AdminAlbumsService {
	
	//consulta disqueras
	List<Disquera> consultarDisqueras();
	//consulta artistas
	List<Artista> consultarArtistas();
	
	//guardar
	Album guardarAlbum(Album album);
	
}
