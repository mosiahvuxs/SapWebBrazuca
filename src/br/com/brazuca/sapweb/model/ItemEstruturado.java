package br.com.brazuca.sapweb.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ItemEstruturado implements Serializable {
	
	private Long id;
	private String estruturaId;
	private Item item;
	private Empresa empresa;
	private Date dataImportacao;

	public String getEstruturaId() {
		return estruturaId;
	}

	public void setEstruturaId(String estruturaId) {
		this.estruturaId = estruturaId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEstruturado other = (ItemEstruturado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
