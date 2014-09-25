package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.brazuca.sapweb.adapter.TimestampAdapter;

@SuppressWarnings("serial")
public class Recebimento implements Serializable {

	private Long id;

	private ContasReceber contasReceber;

	private Boleto boleto;

	private Timestamp dataAtualizacao;

	private Timestamp dataExportacao;

	private Timestamp dataImportacao;

	private Empresa empresa;

	private String mensagemImportacao;

	private String statusBoletoId;

	private Status status;

	private BigDecimal valor;

	public Recebimento() {
		// TODO Auto-generated constructor stub
	}

	public Recebimento(Status status) {

		this.status = status;
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

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Timestamp dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Timestamp dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getMensagemImportacao() {
		return mensagemImportacao;
	}

	public void setMensagemImportacao(String mensagemImportacao) {
		this.mensagemImportacao = mensagemImportacao;
	}

	public String getStatusBoletoId() {
		return statusBoletoId;
	}

	public void setStatusBoletoId(String statusBoletoId) {
		this.statusBoletoId = statusBoletoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public Boleto getBoleto() {
		return boleto;
	}

}
