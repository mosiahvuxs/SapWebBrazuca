package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
public class ContasReceberLinha implements Serializable{
	
	private Long id;
	private Long interfaceId;
	private ParcelaNotaFiscalSaida parcelaNotaFiscalSaida;
	private List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList;
	private ContasReceber contasReceber;
	private Empresa empresa;
	private BigDecimal valorAplicado;
	
	public ContasReceberLinha(){
		
	}
	
	public ContasReceberLinha(ParcelaNotaFiscalSaida parcela) {
		this.parcelaNotaFiscalSaida = parcela;
	}
	public ParcelaNotaFiscalSaida getParcelaNotaFiscalSaida() {
		return parcelaNotaFiscalSaida;
	}
	public void setParcelaNotaFiscalSaida(ParcelaNotaFiscalSaida parcelaNotaFiscalSaida) {
		this.parcelaNotaFiscalSaida = parcelaNotaFiscalSaida;
	}
	public List<ParcelaNotaFiscalSaida> getParcelaNotaFiscalSaidaList() {
		return parcelaNotaFiscalSaidaList;
	}
	public void setParcelaNotaFiscalSaidaList(List<ParcelaNotaFiscalSaida> parcelaNotaFiscalSaidaList) {
		this.parcelaNotaFiscalSaidaList = parcelaNotaFiscalSaidaList;
	}
	public ContasReceber getContasReceber() {
		return contasReceber;
	}
	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(BigDecimal valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	

}
