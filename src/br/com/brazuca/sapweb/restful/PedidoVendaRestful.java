package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.WebServiceUtil;

public class PedidoVendaRestful {

	public PedidoVenda inserirLote(PedidoVenda model, String restful){
		
		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(restful, "pedidoVendaWS", "inserirLote");

			JAXBContext jc = JAXBContext.newInstance(PedidoVenda.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			model = this.populaPedidoVendaREST(resp);

			return model;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;
		
		
		
	}
	
	private PedidoVenda populaPedidoVendaREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(PedidoVenda.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<PedidoVenda> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), PedidoVenda.class);

		return catalog.getValue();
	}		
}

