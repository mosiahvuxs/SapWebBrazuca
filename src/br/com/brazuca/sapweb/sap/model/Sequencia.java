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
public class Sequencia implements Serializable {

    private Long id;
    private String descricao;
    private Empresa empresa;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
}
