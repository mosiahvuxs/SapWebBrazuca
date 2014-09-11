/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ModalidadePagamentoTransferencia extends ModalidadePagamento implements Serializable {

    private Date dataTransferencia;
    private ContaContabil contaContabil;

    public ModalidadePagamentoTransferencia() {
    }

    public ModalidadePagamentoTransferencia(ContasReceber contasReceber) {
        this.setContasReceber(contasReceber);
    }

    public Date getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Date dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public ContaContabil getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(ContaContabil contaContabil) {
        this.contaContabil = contaContabil;
    }
}
