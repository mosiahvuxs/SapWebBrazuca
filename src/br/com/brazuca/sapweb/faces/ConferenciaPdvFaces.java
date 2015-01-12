package br.com.brazuca.sapweb.faces;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.business.NotaFiscalSaidaBusiness;
import br.com.brazuca.sapweb.dao.ItemEstruturadoDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.model.ItemEstruturado;
import br.com.brazuca.sapweb.restful.NotaFiscalSaidaRestful;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.brazuca.sapweb.util.Constantes;
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
	private Empresa empresa;
	private String mensagem;
	private boolean limparCampos;

	@PostConstruct
	public void init() {

		this.initObjetosNaSecao();

		this.clearFields();

	}

	private void initObjetosNaSecao() {

		this.setEmpresa((Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA));

	}

	public void clearFields() {

		this.pedidoVenda = new PedidoVenda();
		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());
		this.pedidoVendaLinha = new PedidoVendaLinha();
		this.pedidos = new ArrayList<PedidoVenda>();
		this.quantidade = 1;
		this.mensagem = null;
		this.limparCampos = false;
	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.pedidoVendaPesquisa.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.pedidoVendaPesquisa.getCliente().getNome())) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataDocumentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataLancamentoFinal()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimento()) && TSUtil.isEmpty(this.pedidoVendaPesquisa.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	@Override
	protected String find() {

		if (this.validaCamposPesquisa()) {

			this.pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVendaPesquisa, null);

			TSFacesUtil.gerarResultadoLista(this.pedidos);

		}

		return null;
	}

	public void pesquisarLinhas() {

		this.codigoBarras = "";

		this.quantidade = 1;

		this.pedidoVenda.setLinhas(new br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO().pesquisar(this.pedidoVenda, null));

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

		this.codigoBarras = "";
		this.quantidade = 1;

	}

	private boolean setarQuantidadePedidoVendaLinha(String codigoBarras) {

		boolean existe = false;

		for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

			if (!TSUtil.isEmpty(linha.getCodigoBarras()) && linha.getCodigoBarras().equals(codigoBarras)) {

				existe = true;

				BigDecimal quantidadeFinal = new BigDecimal(linha.getQuantidadeLiberada().intValueExact() + this.quantidade.intValue());

				if (quantidadeFinal.intValue() <= linha.getQuantidade().intValue()) {

					linha.setQuantidadeLiberada(quantidadeFinal);

				} else {

					super.addErrorMessage("A quantidade solicitada é maior que a quantidade requisitada para o Item " + linha.getItem().getDescricao() + ".");
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

			for (ItemEstruturado itemEstruturado : itens) {

				for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

					if (itemEstruturado.getItem().getId().equals(linha.getItem().getId())) {

						existe = true;

						if (!itensAux.contains(itemEstruturado)) {

							itensAux.add(itemEstruturado);
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

			} else {

				for (ItemEstruturado itemEstruturado : itens) {

					for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

						if (itemEstruturado.getItem().getId().equals(linha.getItem().getId())) {

							Integer quantidadeTotal = itemEstruturado.getItem().getQuantidade().intValue() * this.quantidade.intValue();

							BigDecimal quantidadeFinal = new BigDecimal(linha.getQuantidadeLiberada().intValueExact() + quantidadeTotal.intValue());

							if (quantidadeFinal.intValue() <= linha.getQuantidade().intValue()) {

								linha.setQuantidadeLiberada(quantidadeFinal);

							} else {

								super.addErrorMessage("A quantidade máxima já foi atingida para o Item " + linha.getItem().getDescricao() + ".");
							}

						}
					}
				}

			}
		}

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		this.mensagem = null;

		this.limparCampos = false;

		List<PedidoVendaLinha> linhas = new ArrayList<PedidoVendaLinha>();

		for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

			if (linha.getQuantidadeLiberada().intValueExact() > 0) {

				linha.setValor(linha.getValorUnitario().multiply(linha.getQuantidadeLiberada()).setScale(2, RoundingMode.HALF_UP));

				linhas.add(linha);
			}
		}

		if (!TSUtil.isEmpty(linhas)) {

			NotaFiscalSaida nota = new NotaFiscalSaidaBusiness().inserir(this.pedidoVenda, linhas);
			
			nota  = new NotaFiscalSaidaRestful().inserirLote(nota, Constantes.URL_RESTFUL_BRAZUCA_LOCAL);

			if (TSUtil.isEmpty(nota.getMensagemErro())) {
				
				this.pedidos.remove(this.pedidoVenda);				
				
				this.limparCampos = true;

				this.mensagem = "Operação realizada com sucesso";				

			} else {
				
				this.mensagem = "Não foi possível exportar o Pedido número : " + this.pedidoVenda.getId() + ". " + nota.getMensagemErro();

			}
			

		} else {

			this.mensagem = "Para realizar a operação é necessário que um dos Itens do Pedido nº " + this.pedidoVenda.getId() + " tenha a Quantidade Liberada maior que Zero.";
		}

		return null;
	}

	public void remoteCommand() {

		if (!TSUtil.isEmpty(this.mensagem)) {

			TSFacesUtil.addInfoMessage(this.mensagem);
		}

		if (this.limparCampos) {

			this.pedidoVenda = new PedidoVenda();
			
			this.pedidoVendaPesquisa = new PedidoVenda();
			
			this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());
		}

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

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isLimparCampos() {
		return limparCampos;
	}

	public void setLimparCampos(boolean limparCampos) {
		this.limparCampos = limparCampos;
	}

}
