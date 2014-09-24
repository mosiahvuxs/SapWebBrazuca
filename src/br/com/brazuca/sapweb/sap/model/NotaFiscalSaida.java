package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class NotaFiscalSaida extends DocumentoAB implements Serializable {

	private Long interfaceId;
    private Long serial;
    private Long serialInicial;
    private Long serialFinal;
    private Long idInicial;
    private Long idFinal;    
    private Date dataEmissao;
    private Date dataEmissaoInicial;
    private Date dataEmissaoFinal;
    private Boolean flagBoleto;
    private BigDecimal valor;
    private Empresa empresa;
    private String arquivoUpload;
    private List<NotaFiscalSaidaLinha> linhas; 
    private ParceiroNegocio cliente;  
    private String enderecoCobrancaFormatado;
    private String enderecoEntregaFormatado;
    private String tipoResumo;
    private String tipo;
    private Long tipoEnvio;
    private boolean selecionado;
    
    public NotaFiscalSaida() {
    }

    public NotaFiscalSaida(Long id) {
        this.setId(id);
    }

	public NotaFiscalSaida(Empresa empresa) {

		this.setEmpresa(empresa);
		
	}

	public NotaFiscalSaida(Status status) {

		this.setStatus(status);
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public Long getSerialInicial() {
		return serialInicial;
	}

	public void setSerialInicial(Long serialInicial) {
		this.serialInicial = serialInicial;
	}

	public Long getSerialFinal() {
		return serialFinal;
	}

	public void setSerialFinal(Long serialFinal) {
		this.serialFinal = serialFinal;
	}

	public Long getIdInicial() {
		return idInicial;
	}

	public void setIdInicial(Long idInicial) {
		this.idInicial = idInicial;
	}

	public Long getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(Long idFinal) {
		this.idFinal = idFinal;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public Boolean getFlagBoleto() {
		return flagBoleto;
	}

	public void setFlagBoleto(Boolean flagBoleto) {
		this.flagBoleto = flagBoleto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getArquivoUpload() {
		return arquivoUpload;
	}

	public void setArquivoUpload(String arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public List<NotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<NotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public ParceiroNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocio cliente) {
		this.cliente = cliente;
	}

	public String getEnderecoCobrancaFormatado() {
		return enderecoCobrancaFormatado;
	}

	public void setEnderecoCobrancaFormatado(String enderecoCobrancaFormatado) {
		this.enderecoCobrancaFormatado = enderecoCobrancaFormatado;
	}

	public String getEnderecoEntregaFormatado() {
		return enderecoEntregaFormatado;
	}

	public void setEnderecoEntregaFormatado(String enderecoEntregaFormatado) {
		this.enderecoEntregaFormatado = enderecoEntregaFormatado;
	}

	public String getTipoResumo() {
		return tipoResumo;
	}

	public void setTipoResumo(String tipoResumo) {
		this.tipoResumo = tipoResumo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Long tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

   
}
