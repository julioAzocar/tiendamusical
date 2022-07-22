package com.devpredator.tiendamusicaldata.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.devpredator.tiendamusicalentities.entities.Album;

//clase dao crud jpa tabla de album
public interface AlbumDAO extends PagingAndSortingRepository<Album, Long> {

//	SELECT a.*, sum(ca.cantidad) 
//	FROM tienda_musical.carrito_album ca
//	inner join tienda_musical.album a on ca.idalbum = a.idalbum
//	where ca.estatus ='PAGADO'
//	group by a.nombre 
//	order by sum(ca.cantidad) desc;
	
	@Query("SELECT new com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO(ca, sum(ca.cantidad) as cantidadSuma) "
			+ "FROM CarritoAlbum ca "
			+ "INNER JOIN ca.album a WHERE ca.estatus = 'PAGADO' "
			+ "GROUP BY a.nombre")
	Page<AlbumTopTenDTO> consultarAlbumsTopTen(Pageable pageable);

	
	
}
