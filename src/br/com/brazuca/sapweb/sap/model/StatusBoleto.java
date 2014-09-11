package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StatusBoleto implements Serializable{
	
	private String id;
	
	private String descricao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
