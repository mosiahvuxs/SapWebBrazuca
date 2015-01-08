package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
public class Utilizacao implements Serializable{
	
	//Tabela OUSG
	
	private Long id;
	private String descricao;
	private Empresa empresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

}
