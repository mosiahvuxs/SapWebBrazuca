package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.util.List;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class Grupo implements Serializable {

	private Long id;

	private String descricao;
	
	private TipoGrupo tipoGrupo;

	private List<Permissao> permissoes;
	
	public Grupo() {
		
	}

	public Grupo(Long id) {
		this.id = id;
	}
	
	public Grupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
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

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public TipoGrupo getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	
	

}
