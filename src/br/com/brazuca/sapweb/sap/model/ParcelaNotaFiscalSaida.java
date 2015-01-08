/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParcelaNotaFiscalSaida extends ParcelaAB implements Serializable {

    private ContasReceberLinha contasReceberLinha;
    private List<ContasReceberLinha> contasReceberLinhaList;
    private NotaFiscalSaida notaFiscalSaida;
	public ContasReceberLinha getContasReceberLinha() {
		return contasReceberLinha;
	}
	public void setContasReceberLinha(ContasReceberLinha contasReceberLinha) {
		this.contasReceberLinha = contasReceberLinha;
	}
	public List<ContasReceberLinha> getContasReceberLinhaList() {
		return contasReceberLinhaList;
	}
	public void setContasReceberLinhaList(List<ContasReceberLinha> contasReceberLinhaList) {
		this.contasReceberLinhaList = contasReceberLinhaList;
	}
	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}
	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

}
