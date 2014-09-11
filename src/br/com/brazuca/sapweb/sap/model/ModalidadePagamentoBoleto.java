package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ModalidadePagamentoBoleto extends ModalidadePagamento implements Serializable {

	private Date dataVencimento;
	private FormaPagamento formaPagamento;
	private Boleto boleto;

	public ModalidadePagamentoBoleto() {
	}

	public ModalidadePagamentoBoleto(ContasReceber contasReceber) {

		this.setContasReceber(contasReceber);
	}

	public ModalidadePagamentoBoleto(FormaPagamento formaPagamento) {

		this.setFormaPagamento(formaPagamento);
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

}
