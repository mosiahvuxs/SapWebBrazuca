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
import br.com.brazuca.sapweb.dao.ParceiroNegocioDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.validation.ParceiroNegocioValidation;
import br.com.topsys.util.TSUtil;

@Path(value = "/clienteWS")
public class ClienteWS {
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/pesquisar")
	public Response pesquisar(ParceiroNegocio model) {
		
		model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
		
		List<ParceiroNegocio> lista = new ParceiroNegocioDAO().pesquisar(model);
		
		GenericEntity<List<ParceiroNegocio>> entity = new GenericEntity<List<ParceiroNegocio>>(lista) {};

		Response response = Response.ok(entity).build();

		return response;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/validarPesquisa")
	public Response validarPesquisa(ParceiroNegocio model) {

		new ParceiroNegocioValidation().validarPesquisa(model);

		return Response.status(201).entity(model).build();

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/permitirSincronizacao")		
	public Response permitirSincronizacao(ParceiroNegocio model) {
		
		Response response;
		
		PedidoVenda pedido = new PedidoVenda();
		
		try {

			pedido.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

			List<PedidoVenda> lista = new ArrayList<PedidoVenda>();

			lista.addAll(new PedidoVendaDAO().pesquisarInterface(pedido, Constantes.JNDI_SAP_SERVICO_LOCAL));

			lista.addAll(new PedidoVendaDAO().pesquisarInterface(pedido, Constantes.JNDI_SAP_SERVICO_MATRIZ));
			
			if(!TSUtil.isEmpty(lista) && lista.size()>0){
				
				response = Response.status(403).build();
				
			}else{
				
				response = Response.status(201).build();
				
			}

		} catch (Exception e) {

			response = Response.status(403).build();
			
		}
		

		return response;
		
	}


}
