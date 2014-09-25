package br.com.brazuca.sapweb.business;

import java.util.List;

import br.com.brazuca.sapweb.dao.ItemEstruturadoDAO;
import br.com.brazuca.sapweb.model.ItemEstruturado;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ItemEstruturadoBusiness {

	public void importar() throws TSApplicationException {

		ItemEstruturadoDAO itemEstruturadoDAO = new ItemEstruturadoDAO();

		itemEstruturadoDAO.excluirTodosRegistros();

		List<ItemEstruturado> itens = itemEstruturadoDAO.pesquisarSqlServerMatriz(new ItemEstruturado());

		if (!TSUtil.isEmpty(itens)) {

			itemEstruturadoDAO.inserir(itens);

		}

	}
}
