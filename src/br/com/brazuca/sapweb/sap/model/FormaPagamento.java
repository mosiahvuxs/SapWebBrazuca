/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FormaPagamento implements Serializable{

    //Tabela OPYM
    private String id;          // referente a paymethcod
    private String descricao;   // referente a descript
    private String tipo;        // referente a type
    private Banco banco;
    private String modalidade;
    private Empresa empresa;

    public FormaPagamento() {
    }

    public FormaPagamento(String tipo, String valor) {

        if (tipo.equals("tipo")) {

            this.tipo = valor;

        }

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
    
}
