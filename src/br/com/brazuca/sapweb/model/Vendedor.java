package br.com.brazuca.sapweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vendedor implements Serializable {

	private Long id;
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
