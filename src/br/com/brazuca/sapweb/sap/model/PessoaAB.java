package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class PessoaAB implements Serializable{

    private String id;						//equivalente ao cardCode;
    private IdentificadorFiscal identificadorFiscal;	//equivalente a CardCode(ForeignKey para CRD7)
    private String nome;					//equivalente ao cardName
    private String nomeFantasia;
    private String telefoneResidencial;
    private String telefoneCelular;
    private String fax;
    private String email;
    private Date dataAtualizacao;
    private Classificacao classificacao;
    private String mensagemErro;
    private String tipo;
    private String observacao;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IdentificadorFiscal getIdentificadorFiscal() {
		return identificadorFiscal;
	}
	public void setIdentificadorFiscal(IdentificadorFiscal identificadorFiscal) {
		this.identificadorFiscal = identificadorFiscal;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public Classificacao getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	} 
    
}
