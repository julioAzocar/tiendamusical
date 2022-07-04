package com.debpredator.tiendamusicalweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalservices.service.HomeService;

//controla flujo de informacion de pantalla de home

@ManagedBean
@ViewScoped//genera un solo bean homeController ,import javax.faces.bean.ViewScoped;
public class HomeController {

	private String filtro;
	
	private List<ArtistaAlbumDTO> artistaAlbumDTO;
	
	//obtiene logica de negocio de home
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	
	@PostConstruct
	public void init() {
		System.out.println("Home inicializado");
	}

	public void consultarAlbumsArtistasPorFiltro() {
		
		this.artistaAlbumDTO = this.homeServiceImpl.consultarAlbumFiltro(this.filtro);
		
		if (this.artistaAlbumDTO != null) {
			
			this.artistaAlbumDTO.forEach(artistaAlbumDTO -> {
				System.out.println("Artista : " + artistaAlbumDTO.getArtista().getNombre());
			});
		}
	}
	
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<ArtistaAlbumDTO> getArtistaAlbumDTO() {
		return artistaAlbumDTO;
	}

	public void setArtistaAlbumDTO(List<ArtistaAlbumDTO> artistaAlbumDTO) {
		this.artistaAlbumDTO = artistaAlbumDTO;
	}

	public HomeService getHomeServiceImpl() {
		return homeServiceImpl;
	}

	public void setHomeServiceImpl(HomeService homeServiceImpl) {
		this.homeServiceImpl = homeServiceImpl;
	}
	
	
}
