package br.com.brazuca.sapweb.sap.model;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinha extends DocumentoLinhaAB{
	
	private Long interfaceId;
	private NotaFiscalSaida notaFiscalSaida;
	private PedidoVenda pedidoVenda;
	
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
	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}
	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}
	
	

}
