package br.com.brazuca.sapweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Endereco implements Serializable {

	private String enderecoCobrancaFormatado;
	private String enderecoDestinatarioFormatado;

	public String getEnderecoCobrancaFormatado() {
		return enderecoCobrancaFormatado;
	}

	public void setEnderecoCobrancaFormatado(String enderecoCobrancaFormatado) {
		this.enderecoCobrancaFormatado = enderecoCobrancaFormatado;
	}

	public String getEnderecoDestinatarioFormatado() {
		return enderecoDestinatarioFormatado;
	}

	public void setEnderecoDestinatarioFormatado(String enderecoDestinatarioFormatado) {
		this.enderecoDestinatarioFormatado = enderecoDestinatarioFormatado;
	}

}
