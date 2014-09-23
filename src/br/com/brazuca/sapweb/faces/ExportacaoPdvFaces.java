package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "exportacaoPdvFaces")
public class ExportacaoPdvFaces extends TSMainFaces {

	private PedidoVenda pedidoVendaPesquisa;
	private List<PedidoVenda> pedidos;
	private Empresa empresa;
	private boolean todos;

	public ExportacaoPdvFaces() {

		this.limpar();
	}

	public void limpar() {

		this.empresa = (Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA);

		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());

		this.pedidos = new ArrayList<PedidoVenda>();

		this.todos = false;

	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.pedidoVendaPesquisa.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.pedidoVendaPesquisa.getCliente().getNome())) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	public String pesquisar() {

		if (this.validaCamposPesquisa()) {

			this.pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVendaPesquisa, Constantes.FILTRO_QUERY_PDV);

			TSFacesUtil.gerarResultadoLista(this.pedidos);

		}

		return null;
	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		List<PedidoVenda> pedidosSelecionados = new ArrayList<PedidoVenda>();

		for (PedidoVenda item : this.pedidos) {

			if (!TSUtil.isEmpty(item.isSelecionado()) && item.isSelecionado()) {

				item.setEmpresa(new br.com.brazuca.sapweb.sap.model.Empresa(this.empresa.getId(), this.empresa.getJndi()));

				item.setLinhas(new br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO().pesquisar(item, Constantes.FILTRO_QUERY_PDV_LINHA));

				if (!TSUtil.isEmpty(item.getLinhas())) {

					pedidosSelecionados.add(item);

				}
			}
		}

		if (!TSUtil.isEmpty(pedidosSelecionados)) {

			this.limpar();

			super.addInfoMessage("Exportação realizada com sucesso.");

		} else {

			super.addErrorMessage("Para realizar a operação selecione algum Pedido de Venda.");

		}

		return null;
	}

	public void setarTodos() {

		for (PedidoVenda item : this.pedidos) {

			item.setSelecionado(this.todos);
		}

	}

	public PedidoVenda getPedidoVendaPesquisa() {
		return pedidoVendaPesquisa;
	}

	public void setPedidoVendaPesquisa(PedidoVenda pedidoVendaPesquisa) {
		this.pedidoVendaPesquisa = pedidoVendaPesquisa;
	}

	public List<PedidoVenda> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoVenda> pedidos) {
		this.pedidos = pedidos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

}