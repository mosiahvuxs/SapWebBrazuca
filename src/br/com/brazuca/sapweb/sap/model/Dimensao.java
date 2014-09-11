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
public class Dimensao implements Serializable {

    //Tabela ODIM
    private Long id;
    private String descricao;
    private RegraDistribuicao regraDistribuicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public RegraDistribuicao getRegraDistribuicao() {
		return regraDistribuicao;
	}

	public void setRegraDistribuicao(RegraDistribuicao regraDistribuicao) {
		this.regraDistribuicao = regraDistribuicao;
	}
    
    
}
