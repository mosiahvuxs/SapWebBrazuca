package br.com.brazuca.sapweb.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.dao.VendedorDAO;
import br.com.brazuca.sapweb.sap.model.Vendedor;
import br.com.brazuca.sapweb.validation.VendedorValidation;

@Path(value = "/vendedorWS")
public class VendedorWS {
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/pesquisar")
	public Response pesquisar(Vendedor model) {
		
		model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
		
		List<Vendedor> lista = new VendedorDAO().pesquisar(model);
		
		GenericEntity<List<Vendedor>> entity = new GenericEntity<List<Vendedor>>(lista) {};

		Response response = Response.ok(entity).build();

		return response;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/validarPesquisa")
	public Response validarPesquisa(Vendedor model) {

		new VendedorValidation().validarPesquisa(model);

		return Response.status(201).entity(model).build();

	}
}
