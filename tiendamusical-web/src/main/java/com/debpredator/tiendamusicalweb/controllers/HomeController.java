package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalservices.service.CarritoService;
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
	
	//obtiene logica de negocio de carrito
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	//bean de sesion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

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
	
	public void verDetalleAlbum(ArtistaAlbumDTO artistaAlbumDTO) {
		
		this.sessionBean.setArtistaAlbumDTO(artistaAlbumDTO);
		
		try {
			CommonsUtils.redireccionar("/pages/cliente/detalle.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"¡ERROR!",e.getMessage());
			LOGGER.error("Ver Detalle : " +  e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	
	//permite agregar 1 album en carrito
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO) {
		LOGGER.info("Agregando album : " +  artistaAlbumDTO.getAlbum().getNombre());
		
		CarritoAlbum carritoAlbum = this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDTO, this.sessionBean.getPersona().getCarrito(), 1);
		
		this.sessionBean.getPersona().getCarrito().getCarritosAlbum().add(carritoAlbum);
		
		
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

	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}
	
	
}
