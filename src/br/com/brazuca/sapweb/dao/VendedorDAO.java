/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.Vendedor;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class VendedorDAO {

	@SuppressWarnings("unchecked")
	public List<Vendedor> pesquisar(Vendedor model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("vendedordaoI.pesquisar", model.getEmpresa().getId());
		
		return broker.getCollectionBean(Vendedor.class, "id", "nome", "email", "empresa.id");
	}

	public Vendedor inserir(Vendedor model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("vendedordaoI.inserir", model.getId(), model.getNome(),model.getEmail(), model.getEmpresa().getId());
		
		broker.execute();
		
		return model;
		
	}

	public void excluir(Vendedor model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("vendedordaoI.excluir", model.getEmpresa().getId());
		
		broker.execute();
		
	}

}
