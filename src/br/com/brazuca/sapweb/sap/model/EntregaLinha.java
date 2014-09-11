package br.com.brazuca.sapweb.sap.model;

@SuppressWarnings("serial")
public class EntregaLinha extends DocumentoLinhaAB{
	
	private Long interfaceId;
	private Entrega entrega;
	
	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	

}
