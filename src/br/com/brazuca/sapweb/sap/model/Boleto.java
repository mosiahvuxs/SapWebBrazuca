/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class Boleto implements Serializable {

    private Long id;
    private Long numeroBoleto;
    private ModalidadePagamentoBoleto modalidadePagamentoBoleto;
    private Date dataVencimento;
    private Banco banco;
    private String nossoNumero;
    private String digitoNossoNumero;
    private BigDecimal valorDocumento;
    private ParceiroNegocio cliente;
    private Date dataDocumento;
    private String numeroDocumento;
    private String especieDocumento;
    private String aceite;
    private Date dataProcessamento;
    private Integer carteira;
    private String referencia;
    private String barCodeRep;
    private String barCodeNum;
    private Empresa empresa;
    private StatusBoleto status;

    public Boleto() {
    }

    public Boleto(ModalidadePagamentoBoleto modalidade) {
        modalidadePagamentoBoleto = modalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(Long numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public ModalidadePagamentoBoleto getModalidadePagamentoBoleto() {
        return modalidadePagamentoBoleto;
    }

    public void setModalidadePagamentoBoleto(ModalidadePagamentoBoleto modalidadePagamentoBoleto) {
        this.modalidadePagamentoBoleto = modalidadePagamentoBoleto;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public Integer getCarteira() {
        return carteira;
    }

    public void setCarteira(Integer carteira) {
        this.carteira = carteira;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getBarCodeNum() {
        return barCodeNum;
    }

    public void setBarCodeNum(String barCodeNum) {
        this.barCodeNum = barCodeNum;
    }

    public String getBarCodeRep() {
        return barCodeRep;
    }

    public void setBarCodeRep(String barCodeRep) {
        this.barCodeRep = barCodeRep;
    }


    public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getDigitoNossoNumero() {
        return digitoNossoNumero;
    }

    public void setDigitoNossoNumero(String digitoNossoNumero) {
        this.digitoNossoNumero = digitoNossoNumero;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ParceiroNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocio cliente) {
		this.cliente = cliente;
	}

	public StatusBoleto getStatus() {
		return status;
	}

	public void setStatus(StatusBoleto status) {
		this.status = status;
	} 
}
