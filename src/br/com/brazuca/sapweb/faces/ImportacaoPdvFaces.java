package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.sap.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.sap.model.Empresa;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "importacaoPdvFaces")
public class ImportacaoPdvFaces extends TSMainFaces {

	private PedidoVenda pedidoVenda;
	private List<PedidoVenda> pedidosVenda;
	private boolean todos;

	public ImportacaoPdvFaces() {

		this.clearFields();
	}

	@Override
	protected void clearFields() {

		this.pedidoVenda = new PedidoVenda();
		this.pedidoVenda.setCliente(new ParceiroNegocio());

		br.com.brazuca.sapweb.model.Empresa empresa = (br.com.brazuca.sapweb.model.Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA);
		this.pedidoVenda.setEmpresa(new Empresa(empresa.getId(), empresa.getJndi()));

		this.pedidosVenda = new ArrayList<PedidoVenda>();
		this.todos = false;
	}

	public void limpar() {

		this.clearFields();
	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.pedidoVenda.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.pedidoVenda.getCliente().getNome())) && TSUtil.isEmpty(this.pedidoVenda.getDataDocumento()) && TSUtil.isEmpty(this.pedidoVenda.getDataDocumentoFinal()) && TSUtil.isEmpty(this.pedidoVenda.getDataLancamento()) && TSUtil.isEmpty(this.pedidoVenda.getDataLancamentoFinal()) && TSUtil.isEmpty(this.pedidoVenda.getDataVencimento()) && TSUtil.isEmpty(this.pedidoVenda.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	public String pesquisar() {

		if (this.validaCamposPesquisa()) {

			this.pedidosVenda = new PedidoVendaDAO().pesquisar(this.pedidoVenda);

			TSFacesUtil.gerarResultadoLista(this.pedidosVenda);
		}

		return null;
	}

	public void setarTodos() {

		for (PedidoVenda item : this.pedidosVenda) {

			item.setSelecionado(this.todos);
		}

	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public List<PedidoVenda> getPedidosVenda() {
		return pedidosVenda;
	}

	public void setPedidosVenda(List<PedidoVenda> pedidosVenda) {
		this.pedidosVenda = pedidosVenda;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}
}