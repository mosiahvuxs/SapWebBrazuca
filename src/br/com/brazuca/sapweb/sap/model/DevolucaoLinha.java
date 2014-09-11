package br.com.brazuca.sapweb.sap.model;

@SuppressWarnings("serial")
public class DevolucaoLinha extends DocumentoLinhaAB{
	
	private Long interfaceId;
	private Devolucao devolucao;
	
	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public Devolucao getDevolucao() {
		return devolucao;
	}
	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	
	

}
