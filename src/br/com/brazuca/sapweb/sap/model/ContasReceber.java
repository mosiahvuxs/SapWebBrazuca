package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ContasReceber implements Serializable {

	private Long id;
	private Long interfaceId;
	private ModalidadePagamentoBoleto modalidadePagamentoBoleto;
	private ModalidadePagamentoTransferencia modalidadePagamentoTransferencia;
	private ContasReceberLinha linha;
	private List<ContasReceberLinha> linhas;
	private Status status;
	private Date dataExportacao;
	private String mensagemErro;
	
	private ParceiroNegocio cliente;
	
	private Date dataVencimento;
	private Date dataVencimentoInicial;
	private Date dataVencimentoFinal;
	private Date dataLancamentoInicial;
	private Date dataLancamentoFinal;
	private BigDecimal valor;
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private Boolean flagEmail;
	private Long idInicial;
	private Long idFinal;
	private List<String> mensagensValidacao;
	private Date dataPagamento;
	private Date dataCriacao;
	private Empresa empresa;
    private Date dataDocumento;
    private Date dataLancamento;
    private String tipoModalidade;
    private Date dataImportacao;
    private Recebimento recebimento;
    
	public ContasReceber() {

	}

	public ContasReceber(Long id) {

		this.id = id;
	}

	public ContasReceber(ContasReceberLinha linha) {
		this.linha = linha;
	}

	public ContasReceber(Status status) {

		this.status = status;
		
	}

	public ContasReceber(Empresa empresa) {

		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModalidadePagamentoBoleto getModalidadePagamentoBoleto() {
		return modalidadePagamentoBoleto;
	}

	public void setModalidadePagamentoBoleto(ModalidadePagamentoBoleto modalidadePagamentoBoleto) {
		this.modalidadePagamentoBoleto = modalidadePagamentoBoleto;
	}

	public ModalidadePagamentoTransferencia getModalidadePagamentoTransferencia() {
		return modalidadePagamentoTransferencia;
	}

	public void setModalidadePagamentoTransferencia(ModalidadePagamentoTransferencia modalidadePagamentoTransferencia) {
		this.modalidadePagamentoTransferencia = modalidadePagamentoTransferencia;
	}

	public Date getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Date dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public Date getDataLancamentoInicial() {
		return dataLancamentoInicial;
	}

	public void setDataLancamentoInicial(Date dataLancamentoInicial) {
		this.dataLancamentoInicial = dataLancamentoInicial;
	}

	public Date getDataLancamentoFinal() {
		return dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(Date dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Boolean getFlagEmail() {
		return flagEmail;
	}

	public void setFlagEmail(Boolean flagEmail) {
		this.flagEmail = flagEmail;
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

	public List<String> getMensagensValidacao() {
		return mensagensValidacao;
	}

	public void setMensagensValidacao(List<String> mensagensValidacao) {
		this.mensagensValidacao = mensagensValidacao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getTipoModalidade() {
		return tipoModalidade;
	}

	public void setTipoModalidade(String tipoModalidade) {
		this.tipoModalidade = tipoModalidade;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public ParceiroNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocio cliente) {
		this.cliente = cliente;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public List<ContasReceberLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ContasReceberLinha> linhas) {
		this.linhas = linhas;
	}

	public ContasReceberLinha getLinha() {
		return linha;
	}

	public void setLinha(ContasReceberLinha linha) {
		this.linha = linha;
	}
	
	

}
