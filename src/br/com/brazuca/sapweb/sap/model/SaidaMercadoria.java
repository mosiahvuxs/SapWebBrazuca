package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.brazuca.sapweb.adapter.TimestampAdapter;

@SuppressWarnings("serial")
@XmlRootElement
public class SaidaMercadoria implements Serializable{
	
	//Tabela OIGE
	
    private Long id;
	private Long interfaceId;	    
    private String idExterno;
    private Date dataDocumento;
    private Date dataLancamento;
    private String mensagemErro;
    private Status status;
    private Timestamp dataImportacao;
    private Timestamp dataExportacao;
    private Date dataCriacao;
    private Timestamp dataAtualizacao;
    private String criadoPor;
    private String atualizadoPor;
    private List<SaidaMercadoriaLinha> linhas;
    private Empresa empresa;
    
    public SaidaMercadoria(){
    	
    }
    
    
	public SaidaMercadoria(Empresa empresa) {
		this.setEmpresa(empresa);
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
	public String getIdExterno() {
		return idExterno;
	}
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	public Date getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@XmlJavaTypeAdapter( TimestampAdapter.class)
	public Timestamp getDataImportacao() {
		return dataImportacao;
	}
	public void setDataImportacao(Timestamp dataImportacao) {
		this.dataImportacao = dataImportacao;
	}
	
	@XmlJavaTypeAdapter( TimestampAdapter.class)
	public Timestamp getDataExportacao() {
		return dataExportacao;
	}
	public void setDataExportacao(Timestamp dataExportacao) {
		this.dataExportacao = dataExportacao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@XmlJavaTypeAdapter( TimestampAdapter.class)
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getCriadoPor() {
		return criadoPor;
	}
	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}
	public String getAtualizadoPor() {
		return atualizadoPor;
	}
	public void setAtualizadoPor(String atualizadoPor) {
		this.atualizadoPor = atualizadoPor;
	}
	public List<SaidaMercadoriaLinha> getLinhas() {
		return linhas;
	}
	public void setLinhas(List<SaidaMercadoriaLinha> linhas) {
		this.linhas = linhas;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
    


}
