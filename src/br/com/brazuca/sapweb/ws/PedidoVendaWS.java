package br.com.brazuca.sapweb.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.Constantes;

@Path(value = "/pedidoVendaWS")
public class PedidoVendaWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/pesquisarInterface")
	public Response pesquisarInterface(PedidoVenda model) {

		model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

		List<PedidoVenda> lista = new ArrayList<PedidoVenda>();

		lista.addAll(new PedidoVendaDAO().pesquisarInterface(model, Constantes.JNDI_SAP_SERVICO_LOCAL));

		lista.addAll(new PedidoVendaDAO().pesquisarInterface(model, Constantes.JNDI_SAP_SERVICO_MATRIZ));

		GenericEntity<List<PedidoVenda>> entity = new GenericEntity<List<PedidoVenda>>(lista) {};

		Response response = Response.status(201).entity(entity).build();

		return response;

	}

	
}
