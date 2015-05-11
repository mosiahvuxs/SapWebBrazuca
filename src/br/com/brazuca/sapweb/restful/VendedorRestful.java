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

import br.com.brazuca.sapweb.sap.model.Vendedor;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.WebServiceUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class VendedorRestful {

	public List<Vendedor> pesquisar(Vendedor model) {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(Vendedor.class);

		Client client = Client.create(cc);
		//client.addFilter(new LoggingFilter());

		WebResource resource = client.resource(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

		List<Vendedor> lista = resource.path("vendedorWS").path("pesquisar").accept("application/xml").type("application/xml").post(new GenericType<List<Vendedor>>() {}, model);

		return lista;

	}
	
	public Vendedor validarPesquisa(Vendedor model) {

		try {

			WebServiceUtil webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ, "vendedorWS", "validarPesquisa");

			JAXBContext jc = JAXBContext.newInstance(Vendedor.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			Vendedor vendedor = this.populaVendedorREST(resp);

			return vendedor;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	private Vendedor populaVendedorREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Vendedor.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Vendedor> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), Vendedor.class);

		return catalog.getValue();
	}

}
