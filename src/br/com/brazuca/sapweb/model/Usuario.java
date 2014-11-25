package br.com.brazuca.sapweb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement
public class Usuario implements Serializable {

	private Long id;

	private String nome;

	private String login;

	private String senha;

	private String email;

	private String confirmaSenha;

	private Boolean flagAtivo;

	private Grupo grupoWeb, grupoAndroid, grupoIphone;

	public Usuario() {

	}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {

		return senha;

	}

	public void setSenha(String senha) {

		this.senha = senha;

	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getSituacao() {
		return Utilitarios.getSituacao(flagAtivo);
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Grupo getGrupoWeb() {
		return grupoWeb;
	}

	public void setGrupoWeb(Grupo grupoWeb) {
		this.grupoWeb = grupoWeb;
	}

	public Grupo getGrupoAndroid() {
		return grupoAndroid;
	}

	public void setGrupoAndroid(Grupo grupoAndroid) {
		this.grupoAndroid = grupoAndroid;
	}

	public Grupo getGrupoIphone() {
		return grupoIphone;
	}

	public void setGrupoIphone(Grupo grupoIphone) {
		this.grupoIphone = grupoIphone;
	}
	
	

}
