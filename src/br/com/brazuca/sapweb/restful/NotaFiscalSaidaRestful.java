package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.util.WebServiceUtil;

public class NotaFiscalSaidaRestful {

	public NotaFiscalSaida inserirLote(NotaFiscalSaida model, String restful){
		
		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(restful, "notaFiscalSaidaWS", "inserirLote");

			JAXBContext jc = JAXBContext.newInstance(NotaFiscalSaida.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			model = this.populaNotaFiscalSaidaREST(resp);

			return model;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;
		
		
		
	}
	
	private NotaFiscalSaida populaNotaFiscalSaidaREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(NotaFiscalSaida.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<NotaFiscalSaida> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), NotaFiscalSaida.class);

		return catalog.getValue();
	}		
}

