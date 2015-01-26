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
		this.pedidoVenda.setEmpresa(this.empresa);
		this.pedidoVendaPesquisa = new PedidoVenda();
		this.pedidoVendaPesquisa.setCliente(new ParceiroNegocio());
		this.pedidoVendaPesquisa.setEmpresa(this.empresa);
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

			if (!this.setarQuantidadePedidoVendaLinha(this.codigoBarras, this.pedidoVenda)) {

				this.verificarItemEstruturado(this.codigoBarras);
			}

		} else {

			super.addErrorMessage("Insira o código de barras para realizar a operação.");
		}

		this.codigoBarras = "";
		this.quantidade = 1;

	}

	private boolean setarQuantidadePedidoVendaLinha(String codigoBarras, PedidoVenda model) {

		boolean existe = false;

		//Boolean entrou = Boolean.FALSE;

		PedidoVendaLinha linha = new PedidoVendaLinha();

		linha.setPedidoVenda(model);

		linha.setCodigoBarras(codigoBarras);

		linha = new PedidoVendaLinhaDAO().obterQuantidade(linha);

		if (TSUtil.isEmpty(linha)) {
			return false;
		}

		if (!TSUtil.isEmpty(linha) && (linha.getQuantidade().intValue() < this.quantidade)) {

			super.addErrorMessage("A quantidade solicitada é maior que a quantidade requisitada para o código de barras " + this.codigoBarras + " ou código não existente na lista.");

			existe = true;

		} else {

			Integer quantidadeALiberar = this.quantidade;

			for (PedidoVendaLinha item : this.pedidoVenda.getLinhas()) {

				if (quantidadeALiberar == 0) {
					break;
				}

				if (!TSUtil.isEmpty(item.getCodigoBarras()) && item.getCodigoBarras().equals(codigoBarras)) {

					if (item.getQuantidade().intValue() == item.getQuantidadeLiberada().intValue()) {
						continue;
					}

					existe = true;

					if (quantidadeALiberar == item.getQuantidade().intValue()) {

						item.setQuantidadeLiberada(new BigDecimal(quantidadeALiberar));

						quantidadeALiberar = 0;

					} else {
						
						if(quantidadeALiberar>this.somarQuantidadesLiberadas(codigoBarras)){
							
							//se a parada for maior que a soma das liberadas dar um break;
							
							break;
							
						}

						if (quantidadeALiberar > (item.getQuantidade().intValue() - item.getQuantidadeLiberada().intValue())) {
							
							item.setQuantidadeLiberada(item.getQuantidadeLiberada().add(new BigDecimal(item.getQuantidade()).subtract(item.getQuantidadeLiberada())));

							quantidadeALiberar = quantidadeALiberar - item.getQuantidadeLiberada().intValue();

						} else {

							item.setQuantidadeLiberada(item.getQuantidadeLiberada().add(new BigDecimal(quantidadeALiberar)));

							quantidadeALiberar = quantidadeALiberar - item.getQuantidadeLiberada().intValue();

						}

					}

				}

			}

			if (quantidadeALiberar > 0) {

				existe = true;

				super.addErrorMessage("A quantidade solicitada é maior que a quantidade requisitada para o código de barras " + this.codigoBarras + " ou código não existente na lista. Zerando todas as liberações para este código de barras");

			}

		}

		return existe;
	}

	private Integer somarQuantidadesLiberadas(String codigoBarras) {
		
		Integer valor=0;

		for (PedidoVendaLinha item : this.pedidoVenda.getLinhas()) {
			
			if (!TSUtil.isEmpty(item.getCodigoBarras()) && item.getCodigoBarras().equals(codigoBarras)) {
				
				valor = valor + (item.getQuantidade().intValue() - item.getQuantidadeLiberada().intValue());
				
			}
			
		}
		
		return valor;
	}
	
	private Integer somarQuantidadesLiberadasEstruturado(String codigoBarras, List<ItemEstruturado> itens) {

		Integer valor=0;
		
		for (ItemEstruturado itemEstruturado : itens) {

			for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

				if (itemEstruturado.getItem().getId().equals(linha.getItem().getId())) {
					
					valor = valor + (linha.getQuantidade().intValue() - linha.getQuantidadeLiberada().intValue());					
					
				}
				
			}
			
		}
		
		return valor;
		
	}	

	private Boolean verificarItemEstruturado(String codigoBarras) {

		boolean existe = false;

		Boolean entrou = false;

		List<ItemEstruturado> itens = new ItemEstruturadoDAO().pesquisar(this.codigoBarras);

		List<ItemEstruturado> itensAux = new ArrayList<ItemEstruturado>();

		if (!TSUtil.isEmpty(itens)) {

			for (ItemEstruturado itemEstruturado : itens) {

				for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

					if (itemEstruturado.getItem().getId().equals(linha.getItem().getId()) && linha.getQuantidade().intValue() != linha.getQuantidadeLiberada().intValue()) {

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

			return true;

		} else {

			if (itens.size() != itensAux.size()) {

				super.addErrorMessage("O código de barras informado contém itens estruturados que não estão no Pedido de Venda ou sua quantidade ultrapassou a quantidade requisitada.");

				return true;

			} else {

				Integer quantidadeALiberar;

				for (ItemEstruturado itemEstruturado : itens) {

					quantidadeALiberar = itemEstruturado.getItem().getQuantidade().intValue() * this.quantidade;

					for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

						if (itemEstruturado.getItem().getId().equals(linha.getItem().getId())) {

							if (linha.getQuantidade().intValue() == linha.getQuantidadeLiberada().intValue()) {
								continue;
							}

							existe = true;

							if (quantidadeALiberar == linha.getQuantidade().intValue()) {

								linha.setQuantidadeLiberada(new BigDecimal(quantidadeALiberar));

								quantidadeALiberar = 0;

							} else {
								
								if(quantidadeALiberar>this.somarQuantidadesLiberadasEstruturado(codigoBarras,itens)){
									
									//se a parada for maior que a soma das liberadas dar um break;
									
									break;
									
								}

								if (quantidadeALiberar > (linha.getQuantidade().intValue() - linha.getQuantidadeLiberada().intValue())) {
									
									linha.setQuantidadeLiberada(linha.getQuantidadeLiberada().add(new BigDecimal(linha.getQuantidade()).subtract(linha.getQuantidadeLiberada())));

									quantidadeALiberar = quantidadeALiberar - linha.getQuantidadeLiberada().intValue();						
									
								} else {

									linha.setQuantidadeLiberada(linha.getQuantidadeLiberada().add(new BigDecimal(quantidadeALiberar)));

									quantidadeALiberar = quantidadeALiberar - linha.getQuantidadeLiberada().intValue();

								}

							}

						}
					}

					if (quantidadeALiberar > 0) {

						super.addErrorMessage("A quantidade solicitada é maior que a quantidade requisitada para o código de barras " + this.codigoBarras + " ou código não existente na lista. Zerando todas as liberações para este código de barras");

						entrou = Boolean.TRUE;

						break;

					}

				}

			}
		}

		if (entrou) {

			for (ItemEstruturado itemEstruturado : itens) {

				for (PedidoVendaLinha linha : this.pedidoVenda.getLinhas()) {

					if (itemEstruturado.getItem().getId().equals(linha.getItem().getId())) {

						linha.setQuantidadeLiberada(BigDecimal.ZERO);

					}
				}
			}

		}

		return true;

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
			
			nota = new NotaFiscalSaidaRestful().inserirLote(nota, Constantes.URL_RESTFUL_BRAZUCA_LOCAL);

			if (TSUtil.isEmpty(nota.getMensagemErro())) {

				this.pedidos.remove(this.pedidoVenda);

				new PedidoVendaDAO().excluir(this.pedidoVenda);

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
