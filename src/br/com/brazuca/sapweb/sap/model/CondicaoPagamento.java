package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
@XmlRootElement(name = "condicaoPagamento")
public class CondicaoPagamento implements Serializable {

	// Tabela OCTG

	private Long id;

	private String descricao;

	private Empresa empresa;
	
	private String mensagemErro; 
	
	public CondicaoPagamento(){
		
	}

	public CondicaoPagamento(Empresa empresa) {
		this.empresa = empresa;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}
