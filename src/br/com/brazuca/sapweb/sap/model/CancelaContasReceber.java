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
public class CancelaContasReceber implements Serializable {

    private Long id;
    private ContasReceber contasReceber;
    private Date dataExportacao;
    private Date dataImportacao;
    private Status status;
    private String mensagemErro;
    private ParcelaNotaFiscalSaida parcelaNotaFiscalSaida;

    public CancelaContasReceber() {
    }

    public CancelaContasReceber(Status status){

        this.status = status;

    }

    public CancelaContasReceber(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
        this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

	public Date getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Date dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public ParcelaNotaFiscalSaida getParcelaNotaFiscalSaida() {
		return parcelaNotaFiscalSaida;
	}

	public void setParcelaNotaFiscalSaida(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
		this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
    
    

}
