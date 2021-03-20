package br.com.satelite.api.rest;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

@Path("dados-geograficos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DadosGeograficosResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
	public Response getSateliteData() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		WebTarget target = client.target("https://upload.wikimedia.org/wikipedia/commons/7/71/Belo_Horizonte%2C_Minas_Gerais%2C_Brazil_satellite_shot.JPG");
		
		Response response = target.request().get();
		
		if(!Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
			if(Status.NOT_FOUND.getStatusCode() == response.getStatus()) {
				throw new NotFoundException("Imagem de satélite não encontrada");
			}else if(Status.INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatus()) {
				throw new InternalServerErrorException("Falha no Servidor ao obter imagem de satélite");
			}else {
				throw new Exception("Falha ao obter imagem de satélite");
			}
		}
		
		byte[] imagem = response.readEntity(byte[].class);
		
		return Response.ok(imagem).build();
	}
}