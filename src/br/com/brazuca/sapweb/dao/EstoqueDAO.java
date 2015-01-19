package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.Estoque;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class EstoqueDAO {
	
	@SuppressWarnings("unchecked")
	public List<Estoque> pesquisar(Estoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("estoquedaoI.pesquisar", model.getEmpresa().getId());
					
		return  broker.getCollectionBean(Estoque.class, "id", "descricao", "empresa.id");
		
	}

	public Estoque inserir(Estoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("estoquedaoI.inserir", model.getId(), model.getDescricao(), model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}

	public Estoque excluir(Estoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("estoquedaoI.excluir", model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}
	
}
