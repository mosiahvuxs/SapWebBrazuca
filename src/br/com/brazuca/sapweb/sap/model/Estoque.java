package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
public class Estoque implements Serializable{
	
	//Tabela OWHS
	
	private String id;
	private String descricao;
	private Item item;
	private Empresa empresa;
	private Integer quantidadeDisponivel;
	private Integer quantidadeLiberada;	
	
	public Estoque(){
		
	}
	
	public Estoque(Item item, Empresa empresa) {
		
		this.item = item;
		
		this.empresa = empresa;
		
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public Integer getQuantidadeLiberada() {
		return quantidadeLiberada;
	}

	public void setQuantidadeLiberada(Integer quantidadeLiberada) {
		this.quantidadeLiberada = quantidadeLiberada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
