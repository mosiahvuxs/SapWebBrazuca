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

import br.com.brazuca.sapweb.sap.model.CondicaoPagamento;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.WebServiceUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class CondicaoPagamentoRestful {

	public List<CondicaoPagamento> pesquisar(CondicaoPagamento model) {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(CondicaoPagamento.class);

		Client client = Client.create(cc);
		client.addFilter(new LoggingFilter());

		WebResource resource = client.resource(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

		List<CondicaoPagamento> lista = resource.path("condicaoPagamentoWS").path("pesquisar").accept("application/xml").type("application/xml").post(new GenericType<List<CondicaoPagamento>>() {}, model);

		return lista;

	}
	
	public CondicaoPagamento validarPesquisa(CondicaoPagamento model) {

		try {

			WebServiceUtil webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA_MATRIZ, "condicaoPagamentoWS", "validarPesquisa");

			JAXBContext jc = JAXBContext.newInstance(CondicaoPagamento.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			CondicaoPagamento condicaoPagamento = this.populaCondicaoPagamentoREST(resp);

			return condicaoPagamento;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	private CondicaoPagamento populaCondicaoPagamentoREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(CondicaoPagamento.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<CondicaoPagamento> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), CondicaoPagamento.class);

		return catalog.getValue();
	}

}
