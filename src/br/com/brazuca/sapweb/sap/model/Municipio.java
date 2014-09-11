/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.brazuca.sapweb.sap.model;

/**
 *
 * @author mroland
 */
public class Municipio {

    private Long id;

    private String codigo;

    private String codigoIBGE;

    private EnderecoAB enderecoAB;
    
    private Empresa empresa;

    public Municipio(){

    }

    public Municipio(EnderecoAB enderecoAB){

        this.enderecoAB = enderecoAB;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(String codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public EnderecoAB getEnderecoAB() {
		return enderecoAB;
	}

	public void setEnderecoAB(EnderecoAB enderecoAB) {
		this.enderecoAB = enderecoAB;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


}
