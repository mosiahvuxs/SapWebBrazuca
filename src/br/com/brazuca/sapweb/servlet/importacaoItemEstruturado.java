package br.com.brazuca.sapweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.brazuca.sapweb.dao.ItemEstruturadoDAO;
import br.com.brazuca.sapweb.model.ItemEstruturado;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

@WebServlet("/importacaoItemEstruturado")
public class importacaoItemEstruturado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public importacaoItemEstruturado() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ItemEstruturadoDAO itemEstruturadoDAO = new ItemEstruturadoDAO();

		try {

			itemEstruturadoDAO.excluirTodosRegistros();

			List<ItemEstruturado> itens = itemEstruturadoDAO.pesquisarSqlServerMatriz(new ItemEstruturado());

			if (!TSUtil.isEmpty(itens)) {

				itemEstruturadoDAO.inserirRotina(itens);

			}

		} catch (TSApplicationException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
