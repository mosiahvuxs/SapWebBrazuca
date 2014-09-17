package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.brazuca.sapweb.model.ItemEstruturado;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ItemEstruturadoDAO {

	public Long getQuantidadeRegistrosImportacao() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SAP_WEB_BRAZUCA_SQL_SERVER_MATRIZ);

		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM OITT AS ESTRUTURA WITH(NOLOCK) INNER JOIN ITT1 AS LINHA WITH(NOLOCK) ON (LINHA.FATHER = ESTRUTURA.CODE) INNER JOIN OITM AS ITEM WITH(NOLOCK) ON(ITEM.ITEMCODE = ESTRUTURA.CODE)");

		broker.setSQL(sql.toString());

		return (Long) broker.getObject();
	}

	@SuppressWarnings("unchecked")
	public List<ItemEstruturado> pesquisarSqlServerMatriz(ItemEstruturado model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SAP_WEB_BRAZUCA_SQL_SERVER_MATRIZ);

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ESTRUTURA.CODE AS ESTRUTURA_ID, ITEM.CODEBARS AS ITEM_CODIGO_BARRAS, LINHA.CODE AS ITEM_ID, LINHA.QUANTITY * ESTRUTURA.QAUNTITY AS QUANTIDADE FROM OITT AS ESTRUTURA WITH(NOLOCK) INNER JOIN ITT1 AS LINHA WITH(NOLOCK) ON (LINHA.FATHER = ESTRUTURA.CODE) INNER JOIN OITM AS ITEM WITH (NOLOCK) ON (ITEM.ITEMCODE = ESTRUTURA.CODE)");

		broker.setSQL(sql.toString());

		return broker.getCollectionBean(ItemEstruturado.class, "estruturaId", "item.codigoBarras", "item.id", "item.quantidade");
	}

	public void excluirTodosRegistros() throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itemestruturadodao.excluirTodosRegistros");

		broker.execute();

	}

	public void inserirRotina(List<ItemEstruturado> itens) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		for (ItemEstruturado model : itens) {

			broker.setPropertySQL("itemestruturadodao.inserir", model.getEstruturaId(), model.getItem().getCodigoBarras(), model.getItem().getId(), model.getItem().getQuantidade(), new Timestamp(System.currentTimeMillis()));

			broker.execute();
		}

		broker.endTransaction();

	}

}
