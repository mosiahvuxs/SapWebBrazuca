package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement
public class Grupo implements Serializable {

	private Long id;

	private String descricao;
	
	private TipoGrupo tipoGrupo;

	private List<Permissao> permissoes;
	
	private Permissao permissao;
	
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

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	

}
