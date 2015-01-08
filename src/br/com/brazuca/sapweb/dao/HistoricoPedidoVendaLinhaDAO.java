package br.com.brazuca.sapweb.dao;

import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;


public class HistoricoPedidoVendaLinhaDAO {

	public void inserirComBroker(PedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_pedidovenda_linhas_id_seq"));

		broker.setPropertySQL("historicopedidovendalinhadao.inserirComBroker", model.getInterfaceId(), model.getPedidoVenda().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                  model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getId(), model.getCstICMS().getId(), model.getCstIPI().getId(), model.getCstPIS().getId(), model.getContaContabil().getId(), model.getCfop().getCodigo(), model.getCodigoBarras(), model.getUtilizacao().getId());
		
		broker.execute();
		
	}

}
