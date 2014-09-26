package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
public abstract class DocumentoLinhaAB implements Serializable {

    private Long id;
    private Item item;
    private Double quantidade;
    private BigDecimal valorUnitario;
    private CodigoImposto codigoImposto;
    private Integer numero;
    private BigDecimal valor;
    private CST cstICMS;
    private CST cstPIS;
    private CST cstIPI;
    private CST cstCOFINS;
    private CFOP cfop;
    private ContaContabil contaContabil;
    private Empresa empresa;
    private String codigoBarras;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public CodigoImposto getCodigoImposto() {
		return codigoImposto;
	}
	public void setCodigoImposto(CodigoImposto codigoImposto) {
		this.codigoImposto = codigoImposto;
	}
	public CST getCstICMS() {
		return cstICMS;
	}
	public void setCstICMS(CST cstICMS) {
		this.cstICMS = cstICMS;
	}
	public CST getCstPIS() {
		return cstPIS;
	}
	public void setCstPIS(CST cstPIS) {
		this.cstPIS = cstPIS;
	}
	public CST getCstIPI() {
		return cstIPI;
	}
	public void setCstIPI(CST cstIPI) {
		this.cstIPI = cstIPI;
	}
	public CST getCstCOFINS() {
		return cstCOFINS;
	}
	public void setCstCOFINS(CST cstCOFINS) {
		this.cstCOFINS = cstCOFINS;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public CFOP getCfop() {
		return cfop;
	}
	public void setCfop(CFOP cfop) {
		this.cfop = cfop;
	}
	public ContaContabil getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}




}
