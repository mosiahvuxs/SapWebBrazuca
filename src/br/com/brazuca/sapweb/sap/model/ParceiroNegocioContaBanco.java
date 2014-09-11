/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class ParceiroNegocioContaBanco implements Serializable {

	// Tabela OCRB
	
	private Banco banco;
	private ParceiroNegocio parceiroNegocio;
	private Integer tipoConta; // equivalente a U_TIPO_CONTA

	public ParceiroNegocioContaBanco() {

	}

	public ParceiroNegocioContaBanco(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}



}
