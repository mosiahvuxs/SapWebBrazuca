package br.com.brazuca.sapweb.sap.model;

import java.math.BigDecimal;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class PedidoVendaLinha extends DocumentoLinhaAB {

	private Long interfaceId;
	private PedidoVenda pedidoVenda;
	private BigDecimal quantidadeLiberada;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public BigDecimal getQuantidadeLiberada() {

		if (TSUtil.isEmpty(quantidadeLiberada)) {

			this.quantidadeLiberada = BigDecimal.ZERO;
		}

		return quantidadeLiberada;
	}

	public void setQuantidadeLiberada(BigDecimal quantidadeLiberada) {
		this.quantidadeLiberada = quantidadeLiberada;
	}

}
