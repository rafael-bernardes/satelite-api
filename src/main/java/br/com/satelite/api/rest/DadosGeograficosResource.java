package br.com.satelite.api.rest;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dados-geograficos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DadosGeograficosResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
	public Response getSateliteData() {
		return Response.ok("funcionou satelite-api!").build();
	}
}