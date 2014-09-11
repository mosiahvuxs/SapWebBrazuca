package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ContabilidadeLinha implements Serializable{
	
	private Long id;
	
	private Contabilidade contabilidade;
	
	private Integer numero;
	
	private BigDecimal valorCredito;
	
	private BigDecimal valorDebito;
	
	private Date dataVencimento;
	
	private Date dataLancamento;
	
	private String codigoProjeto;
	
	private String referencia1;
	
	private String referencia2;
	
	private ContaContabil contaContabil;
	
	private String cuAnoCarnaval;
	
	private CentroCusto centroCusto;
	
	private ParceiroNegocio parceiroNegocio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contabilidade getContabilidade() {
		return contabilidade;
	}

	public void setContabilidade(Contabilidade contabilidade) {
		this.contabilidade = contabilidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getCodigoProjeto() {
		return codigoProjeto;
	}

	public void setCodigoProjeto(String codigoProjeto) {
		this.codigoProjeto = codigoProjeto;
	}

	public String getReferencia1() {
		return referencia1;
	}

	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}

	public String getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getCuAnoCarnaval() {
		return cuAnoCarnaval;
	}

	public void setCuAnoCarnaval(String cuAnoCarnaval) {
		this.cuAnoCarnaval = cuAnoCarnaval;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	
	

	
	
}
