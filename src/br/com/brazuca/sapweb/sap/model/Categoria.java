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
public class Categoria implements Serializable{

    // tabela ONFT

    private Long id;                // referente a absid

    private String codigo;          // referente a code

    private String status;          // referente a locked
    
    private Empresa empresa;

    public Categoria(){

    }

    public Categoria(String tipo, String valor){

        if (tipo.equals("codigo")){

            this.codigo = valor;
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

    

}
