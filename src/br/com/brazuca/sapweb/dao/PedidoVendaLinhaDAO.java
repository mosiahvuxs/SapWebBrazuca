package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class PedidoVendaLinhaDAO {

	public void inserir(PedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("pedidovendalinhadao.inserir",

		model.getItem().getId(), model.getCodigoBarras(), model.getQuantidade(), model.getValor(), model.getValorUnitario(), model.getCodigoImposto().getId(), model.getNumero(), model.getPedidoVenda().getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<br.com.brazuca.sapweb.model.PedidoVendaLinha> pesquisar(br.com.brazuca.sapweb.model.PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT ID, ITEM_ID, CODIGO_BARRAS, QUANTIDADE, VALOR, VALOR_UNITARIO, CODIGO_IMPOSTO, NUMERO, PEDIDO_VENDA_ID FROM PUBLIC.PEDIDO_VENDAS_LINHAS WHERE 1 = 1 AND PEDIDO_VENDA_ID = ?");

		broker.setSQL(sql.toString());

		broker.set(model.getId());

		return broker.getCollectionBean(br.com.brazuca.sapweb.model.PedidoVendaLinha.class, "id", "item.id", "item.codigoBarras", "item.quantidade", "valor", "valorUnitario", "codigoImposto", "numero", "pedidoVenda.id");
	}

}
