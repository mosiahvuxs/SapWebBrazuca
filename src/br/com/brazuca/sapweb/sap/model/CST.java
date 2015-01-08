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
public class CST implements Serializable {

	// tabela OTSC

	private Long id; // referente a id

	private String codigo; // referente a code

	private String descricao; // referente a situation

	private Categoria categoria;

	private Empresa empresa;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
