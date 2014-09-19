package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class NotaFiscalSaidaLinhaDAO {

	public void inserir(NotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.beginTransaction();

		broker.setPropertySQL("notafiscalsaidalinhadao.inserir",

		model.getNotaFiscalSaida().getId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(),

		model.getCodigoImposto().getId(), model.getCodigoBarras(), model.getPedidoVendaLinha().getNumero(), model.getPedidoVendaLinha().getPedidoVenda().getId());

		broker.execute();
		
		broker.endTransaction();

	}

	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaidaLinha> pesquisar(NotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("notafiscalsaidalinhadao.pesquisar", model.getId());

		return broker.getCollectionBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id");
	}

}
