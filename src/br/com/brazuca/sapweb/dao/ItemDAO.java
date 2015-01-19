package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.Item;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ItemDAO {

	@SuppressWarnings("unchecked")
	public List<Item> pesquisar(Item model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("itemdaoI.pesquisar", model.getEstoque().getId());

        return broker.getCollectionBean(Item.class, "catalogo","id", "descricao", "preco", "estoqueItem.quantidadeDisponivel", "estoque.id", "empresa.id");
	}

	public Item inserir(Item model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("itemdaoI.inserir", model.getCatalogo(), model.getId(), model.getDescricao(), model.getPreco(), model.getEstoqueItem().getQuantidadeDisponivel(), model.getEstoque().getId(), model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}

	public Item excluir(Item model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("itemdaoI.excluir", model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}

}
