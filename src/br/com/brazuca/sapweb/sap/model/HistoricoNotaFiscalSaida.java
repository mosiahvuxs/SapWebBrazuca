package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement
public class HistoricoNotaFiscalSaida implements Serializable {

	private Long id;
	private NotaFiscalSaida notaFiscalSaida;
	private List<HistoricoNotaFiscalSaidaLinha> linhas;

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

	public List<HistoricoNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<HistoricoNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

}
