/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ModalidadePagamento implements Serializable {

    private Long id;
    private Long interfaceId;
    private BigDecimal valor;
    private ContasReceber contasReceber;
    private String tipo;
    private String referencia;
    private String referenciaFormatada;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaFormatada() {
		
		if((!TSUtil.isEmpty(this.referencia)) && (this.referencia.length()>10)){
			
			this.referenciaFormatada = this.referencia.substring(0,9) + "...";
			
		}else{
			
			this.referenciaFormatada = this.referencia;
			
		}
		
		return referenciaFormatada;
	}

	public void setReferenciaFormatada(String referenciaFormatada) {
		this.referenciaFormatada = referenciaFormatada;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
    
    
    
}
