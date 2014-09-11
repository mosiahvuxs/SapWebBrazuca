package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ContasPagar implements Serializable{
	
    private Long id;
    private ModalidadePagamentoBoleto modalidadePagamentoBoleto;
    private ModalidadePagamentoTransferencia modalidadePagamentoTransferencia;
    private ParcelaNotaFiscalSaida parcelaNotaFiscalSaida;
    private List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList;
    private Status status;
    private Date dataExportacao;
    private String mensagemErro;
    private ParceiroNegocio fornecedor;
    private Date dataVencimento;
    private Date dataVencimentoInicial;
    private Date dataVencimentoFinal;
    private BigDecimal valor;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private Boolean flagEmail;
    private Long idInicial;
    private Long idFinal;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ModalidadePagamentoBoleto getModalidadePagamentoBoleto() {
		return modalidadePagamentoBoleto;
	}
	public void setModalidadePagamentoBoleto(ModalidadePagamentoBoleto modalidadePagamentoBoleto) {
		this.modalidadePagamentoBoleto = modalidadePagamentoBoleto;
	}
	public ModalidadePagamentoTransferencia getModalidadePagamentoTransferencia() {
		return modalidadePagamentoTransferencia;
	}
	public void setModalidadePagamentoTransferencia(ModalidadePagamentoTransferencia modalidadePagamentoTransferencia) {
		this.modalidadePagamentoTransferencia = modalidadePagamentoTransferencia;
	}
	public ParcelaNotaFiscalSaida getParcelaNotaFiscalSaida() {
		return parcelaNotaFiscalSaida;
	}
	public void setParcelaNotaFiscalSaidaModel(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
		this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
	}
	public List<ParcelaNotaFiscalSaida> getParcelaNotaFiscalSaidaList() {
		return parcelaNotaFiscalSaidaList;
	}
	public void setParcelaNotaFiscalSaidaModelList(List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList) {
		this.parcelaNotaFiscalSaidaList = parcelaNotaFiscalSaidaList;
	}
	public Date getDataExportacao() {
		return dataExportacao;
	}
	public void setDataExportacao(Date dataExportacao) {
		this.dataExportacao = dataExportacao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}
	public BigDecimal getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}
	public Boolean getFlagEmail() {
		return flagEmail;
	}
	public void setFlagEmail(Boolean flagEmail) {
		this.flagEmail = flagEmail;
	}
	public Long getIdInicial() {
		return idInicial;
	}
	public void setIdInicial(Long idInicial) {
		this.idInicial = idInicial;
	}
	public Long getIdFinal() {
		return idFinal;
	}
	public void setIdFinal(Long idFinal) {
		this.idFinal = idFinal;
	}
	public ParceiroNegocio getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ParceiroNegocio fornecedor) {
		this.fornecedor = fornecedor;
	}
	public void setParcelaNotaFiscalSaida(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
		this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
	}
	public void setParcelaNotaFiscalSaidaList(List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList) {
		this.parcelaNotaFiscalSaidaList = parcelaNotaFiscalSaidaList;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	

}
