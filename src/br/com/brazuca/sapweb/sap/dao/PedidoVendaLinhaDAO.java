package br.com.brazuca.sapweb.sap.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class PedidoVendaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<PedidoVendaLinha> pesquisar(PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder("SELECT LINHA.DSCRIPTION, LINHA.ITEMCODE AS ITEM_ID, ITEM.CODEBARS AS ITEM_CODIDO_BARRAS, LINHA.OPENQTY AS QUANTIDADE, LINHA.TOTALSUMSY AS VALOR, LINHA.PRICE AS VALOR_UNITARIO, LINHA.VATGROUP AS CODIGO_IMPOSTO, LINHA.LINENUM AS NUMERO FROM RDR1 AS LINHA WITH(NOLOCK) INNER JOIN OITM AS ITEM WITH(NOLOCK) ON (ITEM.ITEMCODE = LINHA.ITEMCODE) WHERE LINHA.DOCENTRY = ?");

		broker.setSQL(sql.toString());

		broker.set(model.getId());

		return broker.getCollectionBean(PedidoVendaLinha.class,

		"item.descricao", "item.id", "codigoBarras", "quantidade", "valor", "valorUnitario", "codigoImposto.id", "numero");
	}

}
