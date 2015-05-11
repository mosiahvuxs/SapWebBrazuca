package br.com.brazuca.sapweb.restful;

import java.util.List;

import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.util.Constantes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class EmpresaRestful {

	public List<Empresa> pesquisar(Empresa model) {

		ClientConfig cc = new DefaultClientConfig();
		
		cc.getClasses().add(Empresa.class);

		Client client = Client.create(cc);
		//client.addFilter(new LoggingFilter());

		WebResource resource = client.resource(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

		List<Empresa> lista = resource.path("empresaWS").path("pesquisar").accept("application/xml").type("application/xml").post(new GenericType<List<Empresa>>() {}, model);

		return lista;

	}

}
