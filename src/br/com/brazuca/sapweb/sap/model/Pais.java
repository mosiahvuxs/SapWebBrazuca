package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
public class Pais implements Serializable{
	
	//Tabela OCRY
	
	private String id;
	
	private String descricao;
	
	private Estado estado;
	
	private Empresa empresa;
	
	public Pais(){
		
	}

	public Pais(String id) {
		this.id = id;
	}

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
