package com.debpredator.tiendamusicalweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalservices.service.HomeService;

//controla flujo de informacion de pantalla de home

@ManagedBean
@ViewScoped//genera un solo bean homeController ,import javax.faces.bean.ViewScoped;
public class HomeController {
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);//import org.apache.logging.log4j.Logger;
	
	private String filtro;
	
	private List<ArtistaAlbumDTO> artistaAlbumDTO;
	
	//obtiene logica de negocio de home
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	
	@PostConstruct
	public void init() {
		//System.out.println("Home inicializado");
		
		LOGGER.info("Home inicializado");
		
		LOGGER.info("INFO");
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.fatal("FALTAL");
		
	}

	public void consultarAlbumsArtistasPorFiltro() {
		
		LOGGER.info("BUSQUEDA: " + this.filtro);
		
		this.artistaAlbumDTO = this.homeServiceImpl.consultarAlbumFiltro(this.filtro);
		
		if (this.artistaAlbumDTO != null) {
			
			this.artistaAlbumDTO.forEach(artistaAlbumDTO -> {
				LOGGER.info("Artista : " + artistaAlbumDTO.getArtista().getNombre());
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
