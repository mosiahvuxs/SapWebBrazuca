package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.EntradaMercadoria;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.WebServiceUtil;

public class EntradaMercadoriaRestful {

	public EntradaMercadoria inserirLote(EntradaMercadoria model) {

		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA, "EntradaMercadoriaWS", "inserirLote");

			JAXBContext jc = JAXBContext.newInstance(EntradaMercadoria.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			EntradaMercadoria contasReceber = this.populaEntradaMercadoriaREST(resp);

			return contasReceber;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	public EntradaMercadoria inserir(EntradaMercadoria model) {

		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA, "entradaMercadoriaWS", "inserir");

			JAXBContext jc = JAXBContext.newInstance(EntradaMercadoria.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());
			
			EntradaMercadoria entrada = this.populaEntradaMercadoriaREST(resp);			
			
			model.setMensagemErro(entrada.getMensagemErro());

			return entrada;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	private EntradaMercadoria populaEntradaMercadoriaREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(EntradaMercadoria.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<EntradaMercadoria> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), EntradaMercadoria.class);

		return catalog.getValue();
	}

}

