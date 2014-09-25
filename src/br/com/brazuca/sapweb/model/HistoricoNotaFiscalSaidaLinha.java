package br.com.brazuca.sapweb.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.sap.model.DocumentoAB;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;

@SuppressWarnings("serial")
public class HistoricoNotaFiscalSaidaLinha extends DocumentoAB implements Serializable {

	private Long id;
	private HistoricoNotaFiscalSaida historicoNotaFiscalSaida;
	private NotaFiscalSaidaLinha notaFiscalSaidaLinha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HistoricoNotaFiscalSaida getHistoricoNotaFiscalSaida() {
		return historicoNotaFiscalSaida;
	}

	public void setHistoricoNotaFiscalSaida(HistoricoNotaFiscalSaida historicoNotaFiscalSaida) {
		this.historicoNotaFiscalSaida = historicoNotaFiscalSaida;
	}

	public NotaFiscalSaidaLinha getNotaFiscalSaidaLinha() {
		return notaFiscalSaidaLinha;
	}

	public void setNotaFiscalSaidaLinha(NotaFiscalSaidaLinha notaFiscalSaidaLinha) {
		this.notaFiscalSaidaLinha = notaFiscalSaidaLinha;
	}

}
