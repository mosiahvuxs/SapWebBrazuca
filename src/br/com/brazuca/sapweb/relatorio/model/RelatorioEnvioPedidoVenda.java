package br.com.brazuca.sapweb.relatorio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class RelatorioEnvioPedidoVenda implements Serializable {

	private Long id;
	private Timestamp dataDocumento;
	private Timestamp dataLancamento;
	private Long pedidoVendaId;
	private BigDecimal valor;
	private String itemId;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private String codigoImpostoId;
	private String codigoBarras;
	private String clienteNome;
	private String descricao;
	private Timestamp dataExportacao;
	private Integer numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Timestamp dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Timestamp getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Timestamp dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Long getPedidoVendaId() {
		return pedidoVendaId;
	}

	public void setPedidoVendaId(Long pedidoVendaId) {
		this.pedidoVendaId = pedidoVendaId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getCodigoImpostoId() {
		return codigoImpostoId;
	}

	public void setCodigoImpostoId(String codigoImpostoId) {
		this.codigoImpostoId = codigoImpostoId;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Timestamp dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
