package br.com.brazuca.sapweb.sap.model;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinha extends DocumentoLinhaAB {

	private Long interfaceId;
	private NotaFiscalSaida notaFiscalSaida;
	private PedidoVendaLinha pedidoVendaLinha;
	private BigDecimal quantidadeLiberada;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

	public BigDecimal getQuantidadeLiberada() {
		return quantidadeLiberada;
	}

	public void setQuantidadeLiberada(BigDecimal quantidadeLiberada) {
		this.quantidadeLiberada = quantidadeLiberada;
	}

}
