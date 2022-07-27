package com.devpredator.tiendamusicalservices.service;

import java.util.List;

import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Genero;
import com.devpredator.tiendamusicalentities.entities.Nacionalidad;
import com.devpredator.tiendamusicalentities.entities.SubGenero;


//mantenedor de artistas
public interface AdminArtistasService {
	
	//consulta artistas
	List<Artista> consultarArtistas();
	
	//consulta naciones
	List<Nacionalidad> consultarNacionalidades();
	
	//consultar generos
	List<Genero> consultarGeneros();
	
	//consultar subgeneros
	List<SubGenero> consultarSubGeneros();

	Artista guardarArtista(Artista artista);
	
	List<SubGenero> consultarSubGenerosPorGenero(Genero genero);
	
	List<SubGenero> consultarSubGenerosPorIdGenero(Long idGenero);
	
}
