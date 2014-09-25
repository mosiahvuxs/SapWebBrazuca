package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class HistoricoNotaFiscalSaida implements Serializable {

	private Long id;
	private NotaFiscalSaida notaFiscalSaida;
	private List<HistoricoNotaFiscalSaidaLinha> linhas;
	private Date dataInicial;
	private Date dataFinal;

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
