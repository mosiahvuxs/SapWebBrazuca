package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.Estoque;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.WebServiceUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class EstoqueRestful {

	public List<Estoque> pesquisar(Estoque model) {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(Estoque.class);

		Client client = Client.create(cc);
		client.addFilter(new LoggingFilter());

		WebResource resource = client.resource(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

		List<Estoque> lista = resource.path("estoqueWS").path("pesquisar").accept("application/xml").type("application/xml").post(new GenericType<List<Estoque>>() {}, model);

		return lista;

	}

	public Estoque validarPesquisa(Estoque model) {

		try {

			WebServiceUtil webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ, "estoqueWS", "validarPesquisa");

			JAXBContext jc = JAXBContext.newInstance(Estoque.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			Estoque estoque = this.populaEstoqueREST(resp);

			return estoque;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	private Estoque populaEstoqueREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Estoque.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Estoque> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), Estoque.class);

		return catalog.getValue();
	}

}
