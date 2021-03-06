/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.brazuca.sapweb.adapter.TimestampAdapter;

/**
 * 
 * @author mroland
 */
@SuppressWarnings("serial")
public abstract class DocumentoAB implements Serializable {

	private Long id;
	private String idExterno;
	private Date dataDocumento;
	private Date dataDocumentoFinal;
	private String mensagemErro;
	private Status status;
	private Timestamp dataImportacao;
	private Timestamp dataExportacao;
	private Date dataLancamento;
	private Date dataLancamentoFinal;
	private Timestamp dataCriacao;
	private Timestamp dataAtualizacao;
	private String criadoPor;
	private String atualizadoPor;
	private Vendedor vendedor;
	private Origem origem;
	private Date dataVencimento;
	private Date dataVencimentoFinal;
	private CondicaoPagamento condicaoPagamento;
	private Sequencia sequencia;
	private ParcelaNotaFiscalSaida parcelaNotaFiscalSaida;
	private List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList;
	private BigDecimal percentualDesconto;
	private String observacao;
	private String tipoResumo;
	private String tipo;
	private Long tipoEnvio;
	private String enderecoEntregaFormatado, enderecoCobrancaFormatado;
	private Boolean flagValidar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getAtualizadoPor() {
		return atualizadoPor;
	}

	public void setAtualizadoPor(String atualizadoPor) {
		this.atualizadoPor = atualizadoPor;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Timestamp dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Timestamp dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Sequencia getSequencia() {
		return sequencia;
	}

	public void setSequencia(Sequencia sequencia) {
		this.sequencia = sequencia;
	}

	public ParcelaNotaFiscalSaida getParcelaNotaFiscalSaida() {
		return parcelaNotaFiscalSaida;
	}

	public void setParcelaNotaFiscalSaida(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
		this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
	}

	public List<ParcelaNotaFiscalSaida> getParcelaNotaFiscalSaidaList() {
		return parcelaNotaFiscalSaidaList;
	}

	public void setParcelaNotaFiscalSaidaList(List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList) {
		this.parcelaNotaFiscalSaidaList = parcelaNotaFiscalSaidaList;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipoResumo() {
		return tipoResumo;
	}

	public void setTipoResumo(String tipoResumo) {
		this.tipoResumo = tipoResumo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Long tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoAB other = (DocumentoAB) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataDocumentoFinal() {
		return dataDocumentoFinal;
	}

	public void setDataDocumentoFinal(Date dataDocumentoFinal) {
		this.dataDocumentoFinal = dataDocumentoFinal;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataLancamentoFinal() {
		return dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(Date dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getEnderecoEntregaFormatado() {
		return enderecoEntregaFormatado;
	}

	public void setEnderecoEntregaFormatado(String enderecoEntregaFormatado) {
		this.enderecoEntregaFormatado = enderecoEntregaFormatado;
	}

	public String getEnderecoCobrancaFormatado() {
		return enderecoCobrancaFormatado;
	}

	public void setEnderecoCobrancaFormatado(String enderecoCobrancaFormatado) {
		this.enderecoCobrancaFormatado = enderecoCobrancaFormatado;
	}

	public Boolean getFlagValidar() {
		return flagValidar;
	}

	public void setFlagValidar(Boolean flagValidar) {
		this.flagValidar = flagValidar;
	}

}
