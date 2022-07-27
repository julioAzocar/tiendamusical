package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.InputStream;
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
import com.devpredator.tiendamusicalentities.entities.Genero;
import com.devpredator.tiendamusicalentities.entities.Nacionalidad;
import com.devpredator.tiendamusicalentities.entities.SubGenero;
import com.devpredator.tiendamusicalservices.service.AdminArtistasService;

@ManagedBean//javax.faces.bean.ManagedBean;
@ViewScoped //javax.faces.bean.ViewScoped;
public class AdminArtistasController {

	//bean de sesion informacion
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	//objeto permite mostrar los mensajes de log en consola de servidor o archivo externo
	private static final Logger LOGGER = LogManager.getLogger(AdminArtistasController.class);//import org.apache.logging.log4j.Logger;
	
	
	@ManagedProperty("#{adminArtistasServiceImpl}")
	private AdminArtistasService adminArtistasServiceImpl;
	
	private List<Artista> artistas;
	
	private List<Artista> artistasFiltrados;
	
	private Artista artista;
	//onjeto para almacenar archivo imagen de album a cargar de forma temporal
	private UploadedFile uploadedFile;
	private InputStream inputStream;
	String absolutePath = null;
	
	
	//combos
	List<Nacionalidad> nacionalidades = null;
	List<Genero> generos = null;
	List<SubGenero> subGeneros = null;
	
	@PostConstruct
	public void init() {

		LOGGER.info("iniciando controler admin albums ");
 
		this.consultar();
		
		this.inicializarServicios();
		
		this.limpiarComponentes();
	}

	public void inicializarServicios() {

		this.nacionalidades  =  this.adminArtistasServiceImpl.consultarNacionalidades();
		this.generos = this.adminArtistasServiceImpl.consultarGeneros();
		//this.subGeneros = this.adminArtistasServiceImpl.consultarSubGeneros();
		
		String relativePath = "resources/imagenes/artistas";
		this.absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
		
	}
	public void limpiarComponentes() {
		this.artista = new Artista();
		this.artista.setNacionalidad(new Nacionalidad());
		this.artista.setSubGenero(new SubGenero());
		this.artista.getSubGenero().setGenero(new Genero());
		
		this.uploadedFile = null;
	}
	
	public void consultar() {
		this.artistas = this.adminArtistasServiceImpl.consultarArtistas();
		
	}

	//filtra por genero seleccionado
	public void consultarSubGenerosPorGenero() {
		
		Genero genero = this.artista.getSubGenero().getGenero();
		
		//this.subGeneros = this.adminArtistasServiceImpl.consultarSubGenerosPorGenero(genero);
		
		this.subGeneros = this.adminArtistasServiceImpl.consultarSubGenerosPorIdGenero(genero.getIdGenero());
		
		this.subGeneros = this.subGeneros ;
		
	}
	
	
	//objeto selecionado en la fila de la tabla para actualizar
	public void cargarArtista(Artista artistaSeleccionado) {
		this.artista = artistaSeleccionado;
		
	}
	
	//permite inicializar imagen de carga temporal para artista
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		
		try {
			this.inputStream = fileUploadEvent.getFile().getInputStream();
			
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Imagen recibida: " + uploadedFile.getFileName());
			
		} catch (IOException e) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Error en cargar archivo de imagen");
			
			e.printStackTrace();
		}
		
	}
	
	

	//guarda album
	public void guardar() {
		
		if (this.uploadedFile != null) {
			try {
				CommonsUtils.guardarImagen(this.absolutePath, this.uploadedFile.getFileName(), this.inputStream);
			} catch (IOException e) {
				CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Error en guardar imagen de album");
				
				e.printStackTrace();
			}
			
			this.artista.setImagen(this.uploadedFile.getFileName());
		}


		Artista artistaGuardado = this.adminArtistasServiceImpl.guardarArtista(this.artista);
		
		if (artistaGuardado.getIdArtista() != null) {
			CommonsUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Artista guardado : " + this.artista.getNombre());
			
			//ocultar modal guadado ok
			PrimeFaces.current().executeScript("PF('dlgArtistas').hide()");
			
			this.consultar();
			this.limpiarComponentes();
		}
	}
	
	
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public AdminArtistasService getAdminArtistasServiceImpl() {
		return adminArtistasServiceImpl;
	}

	public void setAdminArtistasServiceImpl(AdminArtistasService adminArtistasServiceImpl) {
		this.adminArtistasServiceImpl = adminArtistasServiceImpl;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public List<Artista> getArtistasFiltrados() {
		return artistasFiltrados;
	}

	public void setArtistasFiltrados(List<Artista> artistasFiltrados) {
		this.artistasFiltrados = artistasFiltrados;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public List<SubGenero> getSubGeneros() {
		return subGeneros;
	}

	public void setSubGeneros(List<SubGenero> subGeneros) {
		this.subGeneros = subGeneros;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	
	
	
	
	
}
