package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;

public class NotaFiscalSaidaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaidaLinha> pesquisarInterface(NotaFiscalSaida model, String jndi) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		broker.setPropertySQL("notafiscalsaidalinhadaoI.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(NotaFiscalSaidaLinha.class, "interfaceId", "notaFiscalSaida.interfaceId", "item.id", "quantidade", "valorUnitario",
																	"valor", "codigoImposto.id", "cstCOFINS.codigo", "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", "contaContabil.id", "cfop.codigo", "codigoBarras",
																	"pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id", "item.descricao", "utilizacao.id");

	}

	public NotaFiscalSaidaLinha pesquisarPorPedidoVenda(PedidoVenda model, String jndi) {
		
		TSDataBaseBrokerIf broker = null;

		if (!TSUtil.isEmpty(TSUtil.tratarString(jndi))) {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		} else {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		}

		broker.setPropertySQL("notafiscalsaidalinhadaoI.pesquisarPorPedidoVenda", model.getId());

		return (NotaFiscalSaidaLinha) broker.getObjectBean(NotaFiscalSaidaLinha.class, "id", "notaFiscalSaida.id", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "codigoBarras", "pedidoVendaLinha.numero", "pedidoVendaLinha.pedidoVenda.id", "item.descricao");
	}

}
