package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO;
import br.com.brazuca.sapweb.model.Cliente;
import br.com.brazuca.sapweb.model.PedidoVenda;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "conferenciaPdvFaces")
public class ConferenciaPdvFaces extends TSMainFaces {

	private PedidoVenda pedidoVendaPesquisa;
	private PedidoVenda pedidoVenda;
	private List<PedidoVenda> pedidos;

	public ConferenciaPdvFaces() {

		this.limpar();
	}

	public void limpar() {

		this.pedidoVenda = new PedidoVenda();

		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new Cliente());

		this.pedidos = new ArrayList<PedidoVenda>();
	}

	public String pesquisar() {

		this.pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVenda);

		TSFacesUtil.gerarResultadoLista(this.pedidos);

		return null;
	}

	public void pesquisarLinhas() {

		this.pedidoVenda.setLinhasPedidoVenda(new PedidoVendaLinhaDAO().pesquisar(this.pedidoVenda));

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);
		super.setDefaultMessage(false);

		return null;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public List<PedidoVenda> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoVenda> pedidos) {
		this.pedidos = pedidos;
	}

	public PedidoVenda getPedidoVendaPesquisa() {
		return pedidoVendaPesquisa;
	}

	public void setPedidoVendaPesquisa(PedidoVenda pedidoVendaPesquisa) {
		this.pedidoVendaPesquisa = pedidoVendaPesquisa;
	}

}
