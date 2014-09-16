package br.com.brazuca.sapweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemEstruturado implements Serializable {

	private String estruturaId;
	private Item item;

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

}
