package com.devpredator.tiendamusicalservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devpredator.tiendamusicaldata.dao.AlbumDAO;
import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.devpredator.tiendamusicalentities.entities.Album;
import com.devpredator.tiendamusicalservices.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDAO albumDAO;
	
	@Override
	public List<AlbumTopTenDTO> consultarAlbumsTopTenDTO() {

		Pageable pageable = PageRequest.of(0, 10,Sort.by("a.nombre"));
		Page<AlbumTopTenDTO> page = this.albumDAO.consultarAlbumsTopTen(pageable);
		return page.getContent();
		
	}

	@Override
	public List<Album> consultarAlbums() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("nombre"));
		Page<Album> page = this.albumDAO.findAll(pageable);
		return page.getContent();
	}

}
