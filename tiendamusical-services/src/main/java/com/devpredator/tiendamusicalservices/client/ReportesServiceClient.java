package com.devpredator.tiendamusicalservices.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//consume webservice de reportes 

@Service
public class ReportesServiceClient {

	@Value("${spring.base.url.ws.reportes}")
	String urlReportesWS;
	
	//consume webservice para generar reporte jasper
	
	public Response generarReporte(String orderID, String destinatario,String cliente) {
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(urlReportesWS).path("generarReporte");
		
		Form form = new Form();
		form.param("orderID", orderID);
		form.param("cliente", cliente);
		form.param("destinatario", destinatario);
		
		Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
		
		return response;
		
		
	}
}
