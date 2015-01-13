package br.com.brazuca.sapweb.model;

import java.util.Date;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;

@SuppressWarnings("serial")
public class HistoricoNotaFiscalSaida extends NotaFiscalSaida {
	
	private Date dataInicial,dataFinal;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


}
