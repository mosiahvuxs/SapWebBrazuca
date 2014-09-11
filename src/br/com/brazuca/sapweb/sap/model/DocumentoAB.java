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
    private String mensagemErro;
    private Status status;
    private Timestamp dataImportacao;
    private Timestamp dataExportacao;
    private Date dataLancamento; 
    private Date dataCriacao;
    private Timestamp dataAtualizacao;
    private String criadoPor;
    private String atualizadoPor;
    private Vendedor vendedor;
    private Origem origem;
    private Date dataVencimento;
    private CondicaoPagamento condicaoPagamento;
    private Sequencia sequencia;
    private ParcelaNotaFiscalSaida parcelaNotaFiscalSaida;
    private List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList;
    private BigDecimal percentualDesconto; 
    private String observacao;

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

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

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentoAB other = (DocumentoAB) obj;
        if ((this.idExterno == null) ? (other.idExterno != null) : !this.idExterno.equals(other.idExterno)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idExterno != null ? this.idExterno.hashCode() : 0);
        return hash;
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

	@XmlJavaTypeAdapter( TimestampAdapter.class)	
	public Timestamp getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Timestamp dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	@XmlJavaTypeAdapter( TimestampAdapter.class)	
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@XmlJavaTypeAdapter( TimestampAdapter.class)		
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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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
       
}
