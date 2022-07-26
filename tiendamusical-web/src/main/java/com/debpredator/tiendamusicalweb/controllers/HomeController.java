package com.debpredator.tiendamusicalweb.controllers;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.debpredator.tiendamusicalweb.enums.ColorEnum;
import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.devpredator.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.devpredator.tiendamusicalentities.entities.CarritoAlbum;
import com.devpredator.tiendamusicalservices.service.AlbumService;
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
	
	
	//graficas barras chart https://www.primefaces.org/showcase/ui/chart/bar.xhtml?jfwid=d980b
	private BarChartModel barChartModel;//import org.primefaces.model.charts.bar.BarChartModel;
	
	//obtiene logica de negocio de total albums para graficas
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	
	
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
		
		if (this.sessionBean.getPersona().getRol().getIdRol() == 3) {
			this.crearGraficaTopTenAlbumsVendidos();
		}
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
	
	//metodo invoca servicio top ten de albums vendidos 
	public void crearGraficaTopTenAlbumsVendidos() {
		
		this.barChartModel = new BarChartModel();
		ChartData chartData = new ChartData();
		
		//info albums top ten vendidos
		List<AlbumTopTenDTO> albumsTopTen = this.albumServiceImpl.consultarAlbumsTopTenDTO();
		
		String[] meses = new DateFormatSymbols().getMonths();
		
		//itera lista topten y se integra valores en grafica
		for (int i=0; i < albumsTopTen.size(); i++) {
			BarChartDataSet barChartDataSet = new BarChartDataSet();
			barChartDataSet.setLabel(albumsTopTen.get(i).getCarritoAlbum().getAlbum().getNombre());
			
			String color = ColorEnum.values()[i].getDescripcion();
			barChartDataSet.setBackgroundColor(color);
			barChartDataSet.setBorderWidth(1);
			
			List<Number> numeros = new ArrayList<Number>();
			
			//obtiene mes de compra album
			String mesCompra = albumsTopTen.get(i).getCarritoAlbum().getFechaCompra()
					.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","MX"));
			
			for (int j = 0; j < meses.length; j++ ) {
				String mes = meses[j];
				if (mes.equals(mesCompra)) {
					numeros.add(albumsTopTen.get(i).getCantidadSuma());
				}else {
					numeros.add(0);
				}
			}
			
			barChartDataSet.setData(numeros);
			chartData.addChartDataSet(barChartDataSet);
			
			
		}
		//meses
		List<String> etiquetasMeses = new ArrayList<String>();
		etiquetasMeses.add("Enero");
		etiquetasMeses.add("Febreo");
		etiquetasMeses.add("Marzo");
		etiquetasMeses.add("Abril");
		etiquetasMeses.add("Mayo");
		etiquetasMeses.add("Junio");
		etiquetasMeses.add("Julio");
		etiquetasMeses.add("Agosto");
		etiquetasMeses.add("Septiembre");
		etiquetasMeses.add("Octubre");
		etiquetasMeses.add("Noviembre");
		etiquetasMeses.add("Diciembre");
		
		chartData.setLabels(etiquetasMeses);
		
		this.barChartModel.setData(chartData);
		
		BarChartOptions barChartOptions = new BarChartOptions();
		CartesianScales cartesianScales = new CartesianScales();
		CartesianLinearAxes cartesianLinearAxes = new CartesianLinearAxes();
		CartesianLinearTicks cartesianLinearTicks = new CartesianLinearTicks();
		
		cartesianLinearTicks.setBeginAtZero(true);
		cartesianLinearAxes.setTicks(cartesianLinearTicks);
		cartesianScales.addYAxesData(cartesianLinearAxes);
		barChartOptions.setScales(cartesianScales);
		
		
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Top 10 Albums mas vendidos por mes");
		barChartOptions.setTitle(title);
		
		this.barChartModel.setOptions(barChartOptions);
		
		
		
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

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public AlbumService getAlbumServiceImpl() {
		return albumServiceImpl;
	}

	public void setAlbumServiceImpl(AlbumService albumServiceImpl) {
		this.albumServiceImpl = albumServiceImpl;
	}
	
	
}
