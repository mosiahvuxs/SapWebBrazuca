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
public class ParcelaPedidoVenda extends ParcelaAB implements Serializable {

    private PedidoVenda pedidoVenda;

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}
    
    

}
