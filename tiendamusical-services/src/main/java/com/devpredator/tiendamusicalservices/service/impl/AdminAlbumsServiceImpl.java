package com.devpredator.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.AlbumDAO;
import com.devpredator.tiendamusicaldata.dao.ArtistaDAO;
import com.devpredator.tiendamusicaldata.dao.DisqueraDAO;
import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.devpredator.tiendamusicalentities.entities.Album;
import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Disquera;
import com.devpredator.tiendamusicalservices.service.AdminAlbumsService;

@Service
public class AdminAlbumsServiceImpl implements AdminAlbumsService {

	@Autowired
	private ArtistaDAO artistaDAO;
	
	@Autowired
	private DisqueraDAO disqueraDAO;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	@Override
	public List<Artista> consultarArtistas() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("nombre"));
		Page<Artista> page = this.artistaDAO.findAll(pageable);
		return page.getContent();
	}
	
	@Override
	public List<Disquera> consultarDisqueras() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("descripcion"));
		Page<Disquera> page = this.disqueraDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Album guardarAlbum(Album album) {
		
		if (album.getIdAlbum() != null) {
			album.setFechaModificacion(LocalDateTime.now());
		}else {
			album.setFechaCreacion(LocalDateTime.now());
			album.setEstatus(true);
		}

		return this.albumDAO.save(album);
	}




}
