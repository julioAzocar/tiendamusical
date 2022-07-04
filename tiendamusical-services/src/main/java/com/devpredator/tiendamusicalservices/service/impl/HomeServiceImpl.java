package com.devpredator.tiendamusicalservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.ArtistaDAO;
import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.Persona;
import com.devpredator.tiendamusicalservices.service.HomeService;


@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	private ArtistaDAO artistaDAOImpl;

	@Override
	public List<ArtistaAlbumDTO> consultarAlbumFiltro(String filtro) {
		// TODO Auto-generated method stub
		return this.artistaDAOImpl.consultarArtistasAlbumsPorFiltro(filtro);
	}
	

	
	
}
