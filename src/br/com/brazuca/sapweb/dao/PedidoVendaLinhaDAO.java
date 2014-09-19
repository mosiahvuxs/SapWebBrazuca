package br.com.brazuca.sapweb.dao;

import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class PedidoVendaLinhaDAO {

	public void inserir(PedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		broker.setPropertySQL("pedidovendalinhadao.inserir",

		model.getItem().getId(), model.getCodigoBarras(), model.getQuantidade(), model.getValor(), model.getValorUnitario(), model.getCodigoImposto().getId(), model.getNumero(), model.getPedidoVenda().getId());

		broker.execute();

	}

}
