package br.com.brazuca.sapweb.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class HistoricoNotaFiscalSaida implements Serializable {

	private Long id;
	private NotaFiscalSaida notaFiscalSaida;

	public HistoricoNotaFiscalSaida() {

	}

	public HistoricoNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {

		this.notaFiscalSaida = notaFiscalSaida;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
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

}
