package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "conferenciaPdvFaces")
public class ConferenciaPdvFaces extends TSMainFaces {

	private PedidoVenda pedidoVendaPesquisa;
	private PedidoVenda pedidoVenda;
	private List<PedidoVenda> pedidos;
	private String codigoBarras;
	private Integer quantidade;

	public ConferenciaPdvFaces() {

		this.limpar();
	}

	public void limpar() {

		this.pedidoVenda = new PedidoVenda();

		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());

		this.pedidos = new ArrayList<PedidoVenda>();

		this.quantidade = 1;
	}

	public String pesquisar() {

		if (this.validaCamposPesquisa()) {

			this.pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVenda);

			TSFacesUtil.gerarResultadoLista(this.pedidos);

		}

		return null;
	}

	public void pesquisarLinhas() {

		this.pedidoVenda.setLinhas(new br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO().pesquisar(this.pedidoVenda));

	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.pedidoVendaPesquisa.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.pedidoVendaPesquisa.getCliente().getNome())) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);
		super.setDefaultMessage(false);

		return null;
	}

	public void remover(PedidoVendaLinha model) {

		this.pedidoVenda.getLinhas().remove(model);
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

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
