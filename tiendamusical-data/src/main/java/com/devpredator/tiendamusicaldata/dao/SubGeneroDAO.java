package com.devpredator.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.Genero;
import com.devpredator.tiendamusicalentities.entities.SubGenero;

public interface SubGeneroDAO extends PagingAndSortingRepository<SubGenero, Long> {

//filtra subgeneros por parametro genero
	List<SubGenero> findAllByGenero(Genero Genero);
	
	@Query("SELECT sb FROM SubGenero sb WHERE sb.genero.idGenero = :idGenero")
	List<SubGenero> findAllByIdGenero(@Param("idGenero") Long idGenero);
}
