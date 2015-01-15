package br.com.brazuca.sapweb.sap.model;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinha extends DocumentoLinhaAB{
	
	private Long interfaceId;
	private NotaFiscalSaida notaFiscalSaida;
	//private PedidoVenda pedidoVenda;
	private PedidoVendaLinha pedidoVendaLinha;
	
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
	
	

}
