package br.com.brazuca.sapweb.sap.model;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class HistoricoNotaFiscalSaidaLinha extends DocumentoLinhaAB {

	private Long id;
	private NotaFiscalSaidaLinha notaFiscalSaidaLinha;
	private PedidoVendaLinha pedidoVendaLinha;

	public HistoricoNotaFiscalSaidaLinha() {

	}

	public HistoricoNotaFiscalSaidaLinha(NotaFiscalSaidaLinha notaFiscalSaidaLinha) {

		this.notaFiscalSaidaLinha = notaFiscalSaidaLinha;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

	public NotaFiscalSaidaLinha getNotaFiscalSaidaLinha() {
		return notaFiscalSaidaLinha;
	}

	public void setNotaFiscalSaidaLinha(NotaFiscalSaidaLinha notaFiscalSaidaLinha) {
		this.notaFiscalSaidaLinha = notaFiscalSaidaLinha;
	}

}
