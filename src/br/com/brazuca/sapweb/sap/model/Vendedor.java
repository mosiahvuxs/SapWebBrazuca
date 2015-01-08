/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.brazuca.sapweb.adapter.TimestampAdapter;
import br.com.brazuca.sapweb.model.Empresa;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "vendedor")
public class Vendedor implements Serializable {

    private Long id;
    private String nome;
    private Integer tipoIdentificador; 
    private String identificador;
    private Empresa empresa;
    private GrupoComissao grupoComissao;
    private String mensagemErro;
    private VendedorEndereco endereco;
    private Timestamp dataAtualizacao;
    private String email;
    
    
    public Vendedor(){
    	
    }

    public Vendedor(Empresa empresa) {

    	this.empresa = empresa;
    	
	}

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
    
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public GrupoComissao getGrupoComissao() {
		return grupoComissao;
	}

	public void setGrupoComissao(GrupoComissao grupoComissao) {
		this.grupoComissao = grupoComissao;
	}

	public VendedorEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(VendedorEndereco endereco) {
		this.endereco = endereco;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Integer getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Integer tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	@XmlJavaTypeAdapter( TimestampAdapter.class)		
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
    
}
