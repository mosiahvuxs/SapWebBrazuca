package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Item implements Serializable {

	private String id;
	private String codigoBarras;
	private BigDecimal quantidade;
	private BigDecimal valor;
	private BigDecimal valorUnitario;
	private String codigoImposto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getCodigoImposto() {
		return codigoImposto;
	}

	public void setCodigoImposto(String codigoImposto) {
		this.codigoImposto = codigoImposto;
	}
}
