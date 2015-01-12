package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class NotaFiscalSaidaLinhaDAO {

	public void inserir(NotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("notafiscalsaidalinhadao.inserir",

		model.getNotaFiscalSaida().getId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(),

		model.getCodigoImposto().getId(), model.getCodigoBarras(), model.getPedidoVendaLinha().getNumero(), model.getPedidoVendaLinha().getPedidoVenda().getId(), model.getItem().getDescricao());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaidaLinha> pesquisarInterface(NotaFiscalSaida model, String jndi) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		broker.setPropertySQL("notafiscalsaidalinhadao.pesquisar", model.getId());

		return broker.getCollectionBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id", "item.descricao");
	}

	public NotaFiscalSaidaLinha pesquisarPorPedidoVenda(PedidoVenda model, String jndi) {
		
		TSDataBaseBrokerIf broker = null;

		if (!TSUtil.isEmpty(TSUtil.tratarString(jndi))) {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		} else {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		}

		broker.setPropertySQL("notafiscalsaidalinhadao.pesquisarPorPedidoVenda", model.getId());

		return (NotaFiscalSaidaLinha) broker.getObjectBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id", "item.descricao");
	}

}
