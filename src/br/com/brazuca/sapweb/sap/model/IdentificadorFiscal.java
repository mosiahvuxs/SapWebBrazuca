package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class IdentificadorFiscal implements Serializable {

    //Tabela CRD7

    private String id; 													//equivalente a CardCode(PrimaryKey)
    private String enderecoId; 											//equivalente a Address(PrimaryKey)
    private String enderecoTipoId; 										//equivalente a AddrType(Primary)
    private ParceiroNegocio parceiroNegocio;  				//equivalente a CardCode(ForeignKey para OCRD)
    private ParceiroNegocioEndereco parceiroNegocioEndereco; 	//equivalente a Address(ForeignKey para CRD1)
    private String identificador;                                       //equivalente a Taxid0 , taxId4;
    private Integer tipoIdentificador;                                   //nao tem equivalencia;
    private String inscricaoEstadual;
    private String inscricaoEstadualSubstitutoTributaria;
    private String inscricaoMunicipal;
    private String inscricaoINSS;
    private Empresa empresa;

    public IdentificadorFiscal(){

    }

    public IdentificadorFiscal(String tipo, String valor){

        if(tipo.equals("enderecoId")){
            this.enderecoId = valor;
        }
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(String enderecoId) {
		this.enderecoId = enderecoId;
	}

	public String getEnderecoTipoId() {
		return enderecoTipoId;
	}

	public void setEnderecoTipoId(String enderecoTipoId) {
		this.enderecoTipoId = enderecoTipoId;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public ParceiroNegocioEndereco getParceiroNegocioEndereco() {
		return parceiroNegocioEndereco;
	}

	public void setParceiroNegocioEndereco(ParceiroNegocioEndereco parceiroNegocioEndereco) {
		this.parceiroNegocioEndereco = parceiroNegocioEndereco;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Integer tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoEstadualSubstitutoTributaria() {
		return inscricaoEstadualSubstitutoTributaria;
	}

	public void setInscricaoEstadualSubstitutoTributaria(String inscricaoEstadualSubstitutoTributaria) {
		this.inscricaoEstadualSubstitutoTributaria = inscricaoEstadualSubstitutoTributaria;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoINSS() {
		return inscricaoINSS;
	}

	public void setInscricaoINSS(String inscricaoINSS) {
		this.inscricaoINSS = inscricaoINSS;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



}
