package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class PedidoVendaDAO {

	@SuppressWarnings("unchecked")
	public List<PedidoVenda> pesquisar(PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder();

		broker.setSQL(sql.toString());

		return broker.getCollectionBean(PedidoVenda.class, "id", "descricao");
	}

}
