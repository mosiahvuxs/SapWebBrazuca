package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.CondicaoPagamento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class CondicaoPagamentoDAO {

	public void excluir(CondicaoPagamento model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("condicaopagamentodaoI.excluir", model.getEmpresa().getId());
		
		broker.execute();
		
	}

	public CondicaoPagamento inserir(CondicaoPagamento model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("condicaopagamentodaoI.inserir", model.getId(), model.getDescricao(), model.getDiasVencimento(),model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CondicaoPagamento> pesquisar(CondicaoPagamento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("condicaopagamentodaoI.pesquisar", model.getEmpresa().getId());
		
		return broker.getCollectionBean(CondicaoPagamento.class, "id", "descricao", "diasVencimento","empresa.id");
		
	}	

}
