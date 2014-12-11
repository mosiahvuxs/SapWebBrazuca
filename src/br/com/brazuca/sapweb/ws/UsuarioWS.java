package br.com.brazuca.sapweb.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.brazuca.sapweb.dao.UsuarioDAO;
import br.com.brazuca.sapweb.model.Usuario;
import br.com.brazuca.sapweb.validation.UsuarioValidation;

@Path("/usuarioWS")
public class UsuarioWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/pesquisar")
	public Response pesquisar(Usuario model) {
			
		List<Usuario> lista = new UsuarioDAO().pesquisar(model);
				
		GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(lista) {};
		
		Response response = Response.ok(entity).build();

		return response;

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/validarPesquisa")
	public Response validarPesquisa(Usuario model) {

		new UsuarioValidation().validarPesquisa(model);
		
		return Response.status(201).entity(model).build();		

	}	
	


}
