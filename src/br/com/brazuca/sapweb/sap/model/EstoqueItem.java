package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EstoqueItem implements Serializable {

	// Tabela OITW

	private Long id;
	private Item item;
	private Estoque estoque;
	private Integer quantidadeDisponivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

}
