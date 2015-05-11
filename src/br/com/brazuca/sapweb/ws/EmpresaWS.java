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
import br.com.brazuca.sapweb.model.Empresa;

@Path("/empresaWS")
public class EmpresaWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/pesquisar")
	public Response pesquisar(Empresa model) {
			
		List<Empresa> lista = new EmpresaDAO().pesquisar(model);
				
		GenericEntity<List<Empresa>> entity = new GenericEntity<List<Empresa>>(lista) {};
		
		Response response = Response.ok(entity).build();

		return response;

	}
	
}
