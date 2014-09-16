package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.brazuca.sapweb.sap.model.Vendedor;

@SuppressWarnings("serial")
public class PedidoVenda implements Serializable{
	
	private Long id;
	private Date dataLancamento;
	private Date dataEntrega;
	private Date dataDocumento;
	private BigDecimal valorTotal;
	private String observacao;
	private Integer tipoEnvio;
	private String idExterno;
	private Integer condicaoPagamento;
	private String tipoResumo;
	private String tipo;
	
	private Cliente cliente;
	private Endereco endereco;
	private Vendedor vendedor;
	
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
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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
	public Integer getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(Integer tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getIdExterno() {
		return idExterno;
	}
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	public Integer getCondicaoPagamento() {
		return condicaoPagamento;
	}
	public void setCondicaoPagamento(Integer condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
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

}
