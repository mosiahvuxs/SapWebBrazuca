/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class ParceiroNegocio extends PessoaAB implements Serializable {

    //tabela OCRD
    private ParceiroNegocioEndereco endereco;
    private ParceiroNegocioEndereco enderecoDestinatario;
    private List<String> emailsAdicionais;
    private String emailAdicional;
    private ParceiroNegocioContaBanco banco;
	private Empresa empresa; 
	private Boolean flagAtivo;
    private Contato contato;
    private String observacao;
    private ContasReceber contasReceber;
    private String identificadorFederal;
    
    public ParceiroNegocio() {
		// TODO Auto-generated constructor stub
	}
    
    public ParceiroNegocio(ParceiroNegocioContaBanco banco) {
    	
    	this.banco = banco;
    	
	}

	public ParceiroNegocioEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(ParceiroNegocioEndereco endereco) {
		this.endereco = endereco;
	}

	public List<String> getEmailsAdicionais() {
		return emailsAdicionais;
	}

	public void setEmailsAdicionais(List<String> emailsAdicionais) {
		this.emailsAdicionais = emailsAdicionais;
	}

	public String getEmailAdicional() {
		return emailAdicional;
	}

	public void setEmailAdicional(String emailAdicional) {
		this.emailAdicional = emailAdicional;
	}

	public ParceiroNegocioContaBanco getBanco() {
		return banco;
	}

	public void setBanco(ParceiroNegocioContaBanco banco) {
		this.banco = banco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

	public String getIdentificadorFederal() {
		return identificadorFederal;
	}

	public void setIdentificadorFederal(String identificadorFederal) {
		this.identificadorFederal = identificadorFederal;
	}

	public ParceiroNegocioEndereco getEnderecoDestinatario() {
		return enderecoDestinatario;
	}

	public void setEnderecoDestinatario(ParceiroNegocioEndereco enderecoDestinatario) {
		this.enderecoDestinatario = enderecoDestinatario;
	}  
	
    
}
