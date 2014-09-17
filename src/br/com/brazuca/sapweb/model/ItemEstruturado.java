package br.com.brazuca.sapweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemEstruturado implements Serializable {
	
	private Long id;
	private String estruturaId;
	private Item item;
	private Empresa empresa;

	public String getEstruturaId() {
		return estruturaId;
	}

	public void setEstruturaId(String estruturaId) {
		this.estruturaId = estruturaId;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
