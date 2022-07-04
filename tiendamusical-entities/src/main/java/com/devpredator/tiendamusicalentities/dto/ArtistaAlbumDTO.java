package com.devpredator.tiendamusicalentities.dto;

import com.devpredator.tiendamusicalentities.entities.Album;
import com.devpredator.tiendamusicalentities.entities.Artista;

//clase dto para cruzar info entre albums y artistas

public class ArtistaAlbumDTO {

	//info de artista
	private Artista artista;
	//info de album
	private Album album;
	
	public ArtistaAlbumDTO() {
		super();
	}
	//inicializa constructor para query ArtistaDAO consultarArtistasAlbumsPorFiltro
	public ArtistaAlbumDTO(Album album, Artista artista) {
		this.album = album;
		this.artista = artista;
		
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	
}
