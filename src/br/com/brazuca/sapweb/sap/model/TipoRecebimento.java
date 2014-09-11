package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TipoRecebimento implements Serializable {

	private Long id;
	private String descricao;
	private Boolean flagAtivo;

	public TipoRecebimento() {

	}

	public TipoRecebimento(Boolean flagAtivo) {

		this.flagAtivo = flagAtivo;
	}

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

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
