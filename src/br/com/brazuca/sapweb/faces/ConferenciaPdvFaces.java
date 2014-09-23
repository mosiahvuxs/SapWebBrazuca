package br.com.brazuca.sapweb.faces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.ItemEstruturadoDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO;
import br.com.brazuca.sapweb.model.ItemEstruturado;
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
	private PedidoVendaLinha pedidoVendaLinha;
	private List<PedidoVenda> pedidos;
	private String codigoBarras;
	private Integer quantidade;
	private boolean inserir;

	public ConferenciaPdvFaces() {

		this.limpar();
	}

	public void limpar() {

		this.pedidoVenda = new PedidoVenda();

		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());

		this.pedidoVendaLinha = new PedidoVendaLinha();

		this.pedidos = new ArrayList<PedidoVenda>();

		this.quantidade = 1;

		this.inserir = false;
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

			this.pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVendaPesquisa);

			TSFacesUtil.gerarResultadoLista(this.pedidos);

		}

		return null;
	}

	public void pesquisarLinhas() {

		this.codigoBarras = "";

		this.quantidade = 1;

		this.pedidoVenda.setLinhas(new br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO().pesquisar(this.pedidoVenda));

		if (TSUtil.isEmpty(this.pedidoVenda.getLinhas())) {

			super.addErrorMessage("Não vai ser possível fazer a conferência do Pedido, pois não existem itens.");
		}

	}

	public void setarQuantidadeItens() {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigoBarras))) {

			if (!this.setarQuantidadePedidoVendaLinha(this.codigoBarras)) {

				this.verificarItemEstruturado(this.codigoBarras);
			}

		} else {

			super.addErrorMessage("Insira o código de barras para realizar a operação.");
		}

	}

	private boolean setarQuantidadePedidoVendaLinha(String codigoBarras) {

		boolean existe = false;

		for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

			if (linha.getCodigoBarras().equals(codigoBarras)) {

				existe = true;

				if (this.quantidade.intValue() <= linha.getQuantidade().intValueExact()) {

					linha.setQuantidadeLiberada(new BigDecimal(this.quantidade.intValue()));

				} else {

					super.addErrorMessage("A quantidade máxima já foi atingida para o Item " + linha.getItem().getDescricao() + ".");
				}

			}
		}

		return existe;
	}

	private void verificarItemEstruturado(String codigoBarras) {

		boolean existe = false;

		List<ItemEstruturado> itens = new ItemEstruturadoDAO().pesquisar(this.codigoBarras);

		List<ItemEstruturado> itensAux = new ArrayList<ItemEstruturado>();

		if (!TSUtil.isEmpty(itens)) {

			for (ItemEstruturado model : itens) {

				for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

					if (model.getItem().getId().equals(linha.getItem().getId())) {

						existe = true;

						if (this.quantidade.intValue() <= linha.getQuantidade().intValueExact()) {

							linha.setQuantidadeLiberada(new BigDecimal(this.quantidade.intValue()));

						} else {

							super.addErrorMessage("A quantidade máxima já foi atingida para o Item " + linha.getItem().getDescricao() + ".");
						}

						if (!itensAux.contains(model)) {

							itensAux.add(model);
						}

					}
				}
			}

		}

		if (!existe) {

			super.addErrorMessage("Não existem itens associados ao código de barras informado.");

		} else {

			if (itens.size() != itensAux.size()) {

				super.addErrorMessage("O código de barras informado contém itens estruturados que não estão no Pedido de Venda.");
			}
		}

	}

	@Override
	protected String update() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		List<PedidoVendaLinha> linhas = new ArrayList<PedidoVendaLinha>();
		
		for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

			if (linha.getQuantidadeLiberada().intValueExact() > 0) {

				linhas.add(linha);
			}
		}

		if (!TSUtil.isEmpty(linhas)) {
			
			new PedidoVendaLinhaDAO().alterar(linhas);
			
			super.setDefaultMessage(true);
			
		} else {

			super.addErrorMessage("Para realizar a operação é necessário que um dos Itens tenha a Quantidade Liberada maior que Zero.");
		}

		return null;
	}

	@Override
	protected String delete() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		new PedidoVendaLinhaDAO().excluir(this.pedidoVendaLinha);

		this.pedidoVenda.getLinhas().remove(this.pedidoVendaLinha);

		TSFacesUtil.addInfoMessage("Registro removido com sucesso.");

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

	public boolean isInserir() {
		return inserir;
	}

	public void setInserir(boolean inserir) {
		this.inserir = inserir;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

}
