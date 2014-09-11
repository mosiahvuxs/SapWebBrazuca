/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class RegraDistribuicao implements Serializable {
    
    //Tabela OOCR

    private String id;
    private String descricao;
    private Dimensao dimensao;
    private Empresa empresa;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Dimensao getDimensao() {
		return dimensao;
	}
	public void setDimensao(Dimensao dimensao) {
		this.dimensao = dimensao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}




}
