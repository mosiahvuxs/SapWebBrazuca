package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class HistoricoNotaFiscalSaidaLinhaDAO {

	public void inserir(HistoricoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("historiconotafiscalsaidalinhadao.inserir",

		model.getHistoricoNotaFiscalSaida().getId(), model.getNotaFiscalSaidaLinha().getItem().getId(), model.getNotaFiscalSaidaLinha().getQuantidade(), model.getNotaFiscalSaidaLinha().getValorUnitario(), model.getNotaFiscalSaidaLinha().getValor(),

		model.getNotaFiscalSaidaLinha().getCodigoImposto().getId(), model.getNotaFiscalSaidaLinha().getCodigoBarras(), model.getNotaFiscalSaidaLinha().getPedidoVendaLinha().getNumero(), model.getNotaFiscalSaidaLinha().getPedidoVendaLinha().getPedidoVenda().getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaidaLinha> pesquisar(NotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("notafiscalsaidalinhadao.pesquisar", model.getId());

		return broker.getCollectionBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id");
	}

	public NotaFiscalSaidaLinha pesquisarPorPedidoVenda(PedidoVenda model, String jndi) {

		TSDataBaseBrokerIf broker = null;

		if (!TSUtil.isEmpty(TSUtil.tratarString(jndi))) {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		} else {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		}

		broker.setPropertySQL("notafiscalsaidalinhadao.pesquisarPorPedidoVenda", model.getId());

		return (NotaFiscalSaidaLinha) broker.getObjectBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id");
	}

}
