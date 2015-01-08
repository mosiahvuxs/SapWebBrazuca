package br.com.brazuca.sapweb.restful;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.brazuca.sapweb.sap.model.SaidaMercadoria;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.WebServiceUtil;

public class SaidaMercadoriaRestful {

	public SaidaMercadoria inserirLote(SaidaMercadoria model, String restful) {

		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(restful, "saidaMercadoriaWS", "inserirLote");

			JAXBContext jc = JAXBContext.newInstance(SaidaMercadoria.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());

			SaidaMercadoria contasReceber = this.populaSaidaMercadoriaREST(resp);

			return contasReceber;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	public SaidaMercadoria inserir(SaidaMercadoria model) {

		try {

			WebServiceUtil webServiceUtil;

			webServiceUtil = new WebServiceUtil(Constantes.URL_RESTFUL_BRAZUCA_LOCAL, "saidaMercadoriaWS", "inserir");

			JAXBContext jc = JAXBContext.newInstance(SaidaMercadoria.class);

			javax.xml.bind.Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(model, stringWriter);

			// System.out.println(stringWriter.toString());

			String resp = webServiceUtil.post(stringWriter.toString());
			
			SaidaMercadoria saida = this.populaSaidaMercadoriaREST(resp);
			
			model.setMensagemErro(saida.getMensagemErro());

			return saida;

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return null;

	}

	private SaidaMercadoria populaSaidaMercadoriaREST(String resp) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(SaidaMercadoria.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<SaidaMercadoria> catalog = unmarshaller.unmarshal(new StreamSource(new StringReader(resp)), SaidaMercadoria.class);

		return catalog.getValue();
	}

}

