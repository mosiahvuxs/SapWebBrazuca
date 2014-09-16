package br.com.brazuca.sapweb.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class Cliente implements Serializable {

	private Long id;
	private String nome;
	private String identificadorClienteExterior;

	public Long getId() {
		return TSUtil.tratarLong(id);
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

	public String getIdentificadorClienteExterior() {
		return identificadorClienteExterior;
	}

	public void setIdentificadorClienteExterior(String identificadorClienteExterior) {
		this.identificadorClienteExterior = identificadorClienteExterior;
	}

}
