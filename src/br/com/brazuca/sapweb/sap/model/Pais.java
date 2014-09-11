package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pais implements Serializable{
	
	private String id;
	
	private String descricao;
	
	private Estado estado;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
