package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Conta implements Serializable {

	private Long id;
	private String numeroAgencia;
	private String digitoAgencia;
	private String numeroConta;
	private String digitoConta;
	private String codigoConvenio;
	private String nsa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getNsa() {
		return nsa;
	}

	public void setNsa(String nsa) {
		this.nsa = nsa;
	}

}
