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
import br.com.brazuca.sapweb.dao.EstoqueDAO;
import br.com.brazuca.sapweb.dao.ItemDAO;
import br.com.brazuca.sapweb.sap.model.Estoque;
import br.com.brazuca.sapweb.sap.model.Item;
import br.com.brazuca.sapweb.validation.EstoqueValidation;

@Path(value = "/estoqueWS")
public class EstoqueWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/pesquisar")
	public Response pesquisar(Estoque model) {
		
		model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
		
		List<Estoque> lista = new EstoqueDAO().pesquisar(model);
		
		for (Estoque estoque : lista) {
			
			estoque.setItens(new ItemDAO().pesquisar(new Item(model.getEmpresa(), estoque)));
		}

		GenericEntity<List<Estoque>> entity = new GenericEntity<List<Estoque>>(lista) {};

		Response response = Response.ok(entity).build();

		return response;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/validarPesquisa")
	public Response validarPesquisa(Estoque model) {

		new EstoqueValidation().validarPesquisa(model);

		return Response.status(201).entity(model).build();

	}
}
