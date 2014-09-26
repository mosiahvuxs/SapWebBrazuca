/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import br.com.brazuca.sapweb.model.Empresa;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParceiroNegocioEndereco extends EnderecoAB implements Serializable {

    private ParceiroNegocio parceiroNegocio;
    private String tipoEndereco;   
    
    public ParceiroNegocioEndereco(){
    	
    }

    public ParceiroNegocioEndereco(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public ParceiroNegocioEndereco(ParceiroNegocio parceiroNegocio, String atributo, String valor) {

		if("tipoEndereco".equals(atributo)){
			
			this.tipoEndereco = valor;
		}
		
		this.parceiroNegocio = parceiroNegocio;
	}

	public ParceiroNegocioEndereco(ParceiroNegocio parceiroNegocio, String atributo, String valor, Empresa empresa) {
		
		if("tipoEndereco".equals(atributo)){
			
			this.tipoEndereco = valor;
		}
		
		this.parceiroNegocio = parceiroNegocio;
		
		this.setEmpresa(empresa);
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
    
  
}
