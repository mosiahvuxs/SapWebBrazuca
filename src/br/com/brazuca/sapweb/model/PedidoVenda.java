package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.brazuca.sapweb.sap.model.Vendedor;

@SuppressWarnings("serial")
public class PedidoVenda implements Serializable {

	private Long id;
	private Long codigo;
	private Date dataLancamento;
	private Date dataLancamentoFinal;
	private Date dataVencimento;
	private Date dataVencimentoFinal;
	private Date dataDocumento;
	private Date dataDocumentoFinal;
	private BigDecimal valorTotal;
	private String observacao;
	private Long tipoEnvio;
	private String idExterno;
	private Long condicaoPagamento;
	private String tipoResumo;
	private String tipo;

	private Cliente cliente;
	private Endereco endereco;
	private Vendedor vendedor;

	private List<PedidoVendaLinha> linhasPedidoVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getTipoResumo() {
		return tipoResumo;
	}

	public void setTipoResumo(String tipoResumo) {
		this.tipoResumo = tipoResumo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Long getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Long tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public Long getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(Long condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<PedidoVendaLinha> getLinhasPedidoVenda() {
		return linhasPedidoVenda;
	}

	public void setLinhasPedidoVenda(List<PedidoVendaLinha> linhasPedidoVenda) {
		this.linhasPedidoVenda = linhasPedidoVenda;
	}

	public Date getDataLancamentoFinal() {
		return dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(Date dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public Date getDataDocumentoFinal() {
		return dataDocumentoFinal;
	}

	public void setDataDocumentoFinal(Date dataDocumentoFinal) {
		this.dataDocumentoFinal = dataDocumentoFinal;
	}

}
