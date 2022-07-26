package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Album;
import com.devpredator.tiendamusicalentities.entities.Artista;
import com.devpredator.tiendamusicalentities.entities.Disquera;
import com.devpredator.tiendamusicalservices.service.AdminAlbumsService;
import com.devpredator.tiendamusicalservices.service.AlbumService;
import com.devpredator.tiendamusicalservices.service.CarritoService;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class AdminAlbumsController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(AdminAlbumsController.class);//import org.apache.logging.log4j.Logger;
	
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	
	private List<Album> albums;
	
	private List<Album> albumsFiltrados;
	
	@ManagedProperty("#{adminAlbumsServiceImpl}")
	private AdminAlbumsService adminAlbumsServiceImpl;
	
	
	private List<Disquera> disqueras;
	private List<Artista> artistas;
	

	private Album album;
	
	//onjeto para almacenar archivo imagen de album a cargar de forma temporal
	private UploadedFile uploadedFile;
	private InputStream inputStream;
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	String absolutePath = null;
	
	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler admin albums ");
 
		this.consultar();
		
		this.inicializarServicios();
		
		this.limpiarComponentes();
	}

	public void inicializarServicios() {
		this.disqueras  =  this.adminAlbumsServiceImpl.consultarDisqueras();
		this.artistas = this.adminAlbumsServiceImpl.consultarArtistas();
		
		String relativePath = "resources/imagenes/albums";
		this.absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
		
		
	}
	public void limpiarComponentes() {
		this.album = new Album();
		this.album.setDisquera(new Disquera());
		this.album.setArtista(new Artista());
		
	}
	public void consultar() {
		this.albums = this.albumServiceImpl.consultarAlbums();
		
	}
	
	//permite inicializar imagen de carga temporal para album
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		
		try {
			this.inputStream = fileUploadEvent.getFile().getInputStream();
		} catch (IOException e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Error en cargar archivo de imagen de album");
			
			e.printStackTrace();
		}
		
	}
	
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
    
//	public void upload() {
//	    if (uploadedFile != null) {
//	        FacesMessage message = new FacesMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
//	        FacesContext.getCurrentInstance().addMessage(null, message);
//	    }
//	}
	   
	//guarda album
	public void guardar() {
		
		
		try {
			CommonsUtils.guardarImagen(this.absolutePath, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Error en guardar imagen de album");
			
			e.printStackTrace();
		}
		
		this.album.setImagen(this.uploadedFile.getFileName());

		
		Album albumGuardado = this.adminAlbumsServiceImpl.guardarAlbum(this.album);
		
		if (albumGuardado.getIdAlbum() != null) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Album guardado : " + this.album.getNombre());
			
			//ocultar modal guadado ok
			PrimeFaces.current().executeScript("PF('dlgAlbums').hide()");
			
			this.consultar();
		}
	}
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public AlbumService getAlbumServiceImpl() {
		return albumServiceImpl;
	}

	public void setAlbumServiceImpl(AlbumService albumServiceImpl) {
		this.albumServiceImpl = albumServiceImpl;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Album> getAlbumsFiltrados() {
		return albumsFiltrados;
	}

	public void setAlbumsFiltrados(List<Album> albumsFiltrados) {
		this.albumsFiltrados = albumsFiltrados;
	}
	

	public List<Disquera> getDisqueras() {
		return disqueras;
	}

	public void setDisqueras(List<Disquera> disqueras) {
		this.disqueras = disqueras;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public AdminAlbumsService getAdminAlbumsServiceImpl() {
		return adminAlbumsServiceImpl;
	}

	public void setAdminAlbumsServiceImpl(AdminAlbumsService adminAlbumsServiceImpl) {
		this.adminAlbumsServiceImpl = adminAlbumsServiceImpl;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	
}
