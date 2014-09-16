package br.com.brazuca.sapweb.sap.model;

@SuppressWarnings("serial")
public class PedidoVendaLinha extends DocumentoLinhaAB {

	private Long interfaceId;
	private PedidoVenda pedidoVenda;

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

}
