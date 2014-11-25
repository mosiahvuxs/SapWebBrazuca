package br.com.brazuca.sapweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TipoGrupo implements Serializable{
	
	private Long id;
	private String descricao;
	
	public TipoGrupo(){
		
	}
	
	public TipoGrupo(Long id) {
		this.id = id;
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
	
	

}
