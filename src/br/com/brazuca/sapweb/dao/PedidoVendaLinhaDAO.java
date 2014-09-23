package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.Item;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class PedidoVendaLinhaDAO {

	public void inserir(PedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("pedidovendalinhadao.inserir",

		model.getItem().getId(), model.getCodigoBarras(), model.getQuantidade(), model.getValor(), model.getValorUnitario(), model.getCodigoImposto().getId(), model.getNumero(), model.getPedidoVenda().getId(), model.getItem().getDescricao());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<PedidoVendaLinha> pesquisar(PedidoVenda model, String filtro) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT ID, DESCRICAO, ITEM_ID, CODIGO_BARRAS, QUANTIDADE, VALOR, VALOR_UNITARIO, CODIGO_IMPOSTO, NUMERO, PEDIDO_VENDA_ID, QUANTIDADE_LIBERADA FROM PUBLIC.PEDIDO_VENDAS_LINHAS WHERE 1 = 1 AND PEDIDO_VENDA_ID = ?");

		if(!TSUtil.isEmpty(filtro)){
			
			sql.append(filtro);
		}
		
		sql.append(" ORDER BY DESCRICAO");
		
		broker.setSQL(sql.toString());

		broker.set(model.getSerial());

		return broker.getCollectionBean(PedidoVendaLinha.class, "id", "item.descricao", "item.id", "codigoBarras", "quantidade", "valor", "valorUnitario", "codigoImposto.id", "numero", "pedidoVenda.id", "quantidadeLiberada");
	}

	public PedidoVendaLinha obter(Item model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT ID, DESCRICAO, ITEM_ID, CODIGO_BARRAS, QUANTIDADE, VALOR, VALOR_UNITARIO, CODIGO_IMPOSTO, NUMERO, PEDIDO_VENDA_ID, QUANTIDADE_LIBERADA FROM PUBLIC.PEDIDO_VENDAS_LINHAS WHERE CODIGO_BARRAS = ? LIMIT 1");

		broker.setSQL(sql.toString());

		broker.set(model.getId());

		return (PedidoVendaLinha) broker.getObjectBean(PedidoVendaLinha.class, "id", "item.descricao", "item.id", "codigoBarras", "quantidade", "valor", "valorUnitario", "codigoImposto.id", "numero", "pedidoVenda.id", "quantidadeLiberada");
	}

	public void excluir(PedidoVendaLinha model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("pedidovendalinhadao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(List<PedidoVendaLinha> linhas) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		for (PedidoVendaLinha model : linhas) {

			broker.setPropertySQL("pedidovendalinhadao.alterar", model.getQuantidadeLiberada(), model.getId());

			broker.execute();

		}

		broker.endTransaction();

	}
}
