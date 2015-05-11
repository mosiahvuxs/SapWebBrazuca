package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.Entrega;
import br.com.brazuca.sapweb.util.WebServiceUtil;

public class EntregaRestful {

	public Entrega inserirLote(Entrega model, String restful){
		
		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(restful, "entregaWS", "inserirLote");

			JAXBContext jc = JAXBContext.newInstance(Entrega.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			Entrega nota = this.populaEntregaREST(resp);

			return nota;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;
			
	}
	
	private Entrega populaEntregaREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Entrega.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Entrega> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), Entrega.class);

		return catalog.getValue();
	}		
}

