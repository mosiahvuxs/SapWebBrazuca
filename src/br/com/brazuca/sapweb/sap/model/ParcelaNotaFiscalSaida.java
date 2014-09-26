/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.brazuca.sapweb.model.Empresa;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParcelaNotaFiscalSaida implements Serializable {

    private Long id;
    private Date dataVencimentoInicial;
    private Date dataVencimentoFinal;
    private Date dataVencimento;
    private BigDecimal valor;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private BigDecimal valorAberto;    
    private Boolean flagPago;
    private Boolean flagBoleto;
    private ContasReceberLinha contasReceberLinha;
    private List<ContasReceberLinha> contasReceberLinhaList;
    private Integer numero;
    private Status status;
    private NotaFiscalSaida notaFiscalSaida;
	private Boolean flagEmail;
	private Empresa empresa;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getFlagBoleto() {
        return flagBoleto;
    }

    public void setFlagBoleto(Boolean flagBoleto) {
        this.flagBoleto = flagBoleto;
    }

    public Boolean getFlagPago() {
        return flagPago;
    }

    public void setFlagPago(Boolean flagPago) {
        this.flagPago = flagPago;
    }

    public Date getDataVencimentoFinal() {
        return dataVencimentoFinal;
    }

    public void setDataVencimentoFinal(Date dataVencimentoFinal) {
        this.dataVencimentoFinal = dataVencimentoFinal;
    }

    public Date getDataVencimentoInicial() {
        return dataVencimentoInicial;
    }

    public void setDataVencimentoInicial(Date dataVencimentoInicial) {
        this.dataVencimentoInicial = dataVencimentoInicial;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getIdFormatado() {
		return id.toString() + numero.toString();
	}

	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ContasReceberLinha getContasReceberLinha() {
		return contasReceberLinha;
	}

	public void setContasReceberLinha(ContasReceberLinha contasReceberLinha) {
		this.contasReceberLinha = contasReceberLinha;
	}

	public List<ContasReceberLinha> getContasReceberLinhaList() {
		return contasReceberLinhaList;
	}

	public void setContasReceberLinhaList(List<ContasReceberLinha> contasReceberLinhaList) {
		this.contasReceberLinhaList = contasReceberLinhaList;
	}

	public BigDecimal getValorAberto() {
		return valorAberto;
	}

	public void setValorAberto(BigDecimal valorAberto) {
		this.valorAberto = valorAberto;
	}
	
	
    
}
