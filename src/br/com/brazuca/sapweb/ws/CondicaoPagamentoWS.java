package br.com.brazuca.sapweb.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.brazuca.sapweb.dao.CondicaoPagamentoDAO;
import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.sap.model.CondicaoPagamento;
import br.com.brazuca.sapweb.validation.CondicaoPagamentoValidation;

@Path(value = "/condicaoPagamentoWS")
public class CondicaoPagamentoWS {
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/pesquisar")
	public Response pesquisar(CondicaoPagamento model) {
		
		model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
		
		List<CondicaoPagamento> lista = new CondicaoPagamentoDAO().pesquisar(model);
		
		GenericEntity<List<CondicaoPagamento>> entity = new GenericEntity<List<CondicaoPagamento>>(lista) {};

		Response response = Response.ok(entity).build();

		return response;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/validarPesquisa")
	public Response validarPesquisa(CondicaoPagamento model) {

		new CondicaoPagamentoValidation().validarPesquisa(model);

		return Response.status(201).entity(model).build();

	}
}
