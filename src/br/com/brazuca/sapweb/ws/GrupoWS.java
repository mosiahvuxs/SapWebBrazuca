package br.com.brazuca.sapweb.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.brazuca.sapweb.dao.GrupoDAO;
import br.com.brazuca.sapweb.dao.PermissaoDAO;
import br.com.brazuca.sapweb.model.Grupo;

@Path("/grupoWS")
public class GrupoWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/obter")
	public Response obter(Grupo model) {
		
		Grupo grupo = new GrupoDAO().obter(model);
		
		grupo.setPermissoes(new PermissaoDAO().pesquisar(grupo));
		
		Response response = Response.ok(grupo).build();

		return response;

	}
	
}
