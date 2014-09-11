/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mroland
 */
@SuppressWarnings("serial")
public class NotaFiscalSaidaEletronica implements Serializable {

	private Long id;
	private Date dataCriacao;
	private String chaveNfe;
	private NotaFiscalSaida notaFiscalSaida;
	private Long empresaOobjID;

	public NotaFiscalSaidaEletronica() {

	}

	public NotaFiscalSaidaEletronica(NotaFiscalSaida notaFiscalSaida) {

		this.notaFiscalSaida = notaFiscalSaida;
	}

	public String getChaveNfe() {
		return chaveNfe;
	}

	public void setChaveNfe(String chaveNfe) {
		this.chaveNfe = chaveNfe;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public Long getEmpresaOobjID() {
		return empresaOobjID;
	}

	public void setEmpresaOobjID(Long empresaOobjID) {
		this.empresaOobjID = empresaOobjID;
	}

}
