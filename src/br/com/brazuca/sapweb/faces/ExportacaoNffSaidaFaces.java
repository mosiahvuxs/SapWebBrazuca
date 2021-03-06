package br.com.brazuca.sapweb.faces;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.dao.HistoricoNotaFiscalSaidaDAO;
import br.com.brazuca.sapweb.dao.NotaFiscalSaidaDAO;
import br.com.brazuca.sapweb.dao.NotaFiscalSaidaLinhaDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.restful.NotaFiscalSaidaRestful;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.Status;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "exportacaoNffSaidaFaces")
public class ExportacaoNffSaidaFaces extends TSMainFaces {

	private NotaFiscalSaida notaFiscalSaida;
	private NotaFiscalSaida notaFiscalSaidaInterface;
	private List<NotaFiscalSaida> notas;
	private List<NotaFiscalSaida> notasInterface;
	private boolean todos;
	private Empresa empresa;

	public ExportacaoNffSaidaFaces() {
		
		this.initObjetosNaSecao();

		this.limpar();
	}
	
	private void initObjetosNaSecao() {

		this.setEmpresa((Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA));

	}	

	public void limpar() {

		this.notaFiscalSaida = new NotaFiscalSaida();
		this.notaFiscalSaida.setCliente(new ParceiroNegocio());

		if (TSUtil.isEmpty(this.notas)) {

			this.notas = new ArrayList<NotaFiscalSaida>();
		}

		this.todos = false;
		this.notaFiscalSaidaInterface = new NotaFiscalSaida();
		this.notasInterface = new ArrayList<NotaFiscalSaida>();

	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.notaFiscalSaida.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.notaFiscalSaida.getCliente().getNome())) && TSUtil.isEmpty(this.notaFiscalSaida.getDataDocumento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataDocumentoFinal()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataLancamento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataLancamentoFinal()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataVencimento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	@Override
	protected String find() {

		if (this.validaCamposPesquisa()) {

			this.notas = new NotaFiscalSaidaDAO().pesquisarInterface(this.notaFiscalSaida, Constantes.JNDI_SAP_SERVICO_LOCAL);

			TSFacesUtil.gerarResultadoLista(this.notas);

		}

		return null;
	}

	public void setarTodos() {

		for (NotaFiscalSaida item : this.notas) {

			item.setSelecionado(this.todos);
		}

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);
		super.setDefaultMessage(false);

		List<NotaFiscalSaida> notasFiscais = new ArrayList<NotaFiscalSaida>();

		for (NotaFiscalSaida nota : this.notas) {

			if (!TSUtil.isEmpty(nota.isSelecionado()) && nota.isSelecionado()) {
				
				nota.setEmpresa(new EmpresaDAO().obter(this.empresa));

				nota.setLinhas(new NotaFiscalSaidaLinhaDAO().pesquisarInterface(nota, Constantes.JNDI_SAP_SERVICO_LOCAL));

				if (!TSUtil.isEmpty(nota.getLinhas())) {

					nota.setValor(BigDecimal.ZERO);

					for (NotaFiscalSaidaLinha linha : nota.getLinhas()) {

						nota.setValor(nota.getValor().add(linha.getValor()));
					}

					notasFiscais.add(nota);

				}
			}
		}

		if (!TSUtil.isEmpty(notasFiscais)) {

			for (NotaFiscalSaida notaFiscal : notasFiscais) {

				notaFiscal.setDataCriacao(new Timestamp(System.currentTimeMillis()));

				NotaFiscalSaida model = new NotaFiscalSaidaRestful().inserirLote(notaFiscal, Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

				if (TSUtil.isEmpty(model.getMensagemErro())) {

					this.notas.remove(notaFiscal);

					notaFiscal.setStatus(new Status(Constantes.ID_STATUS_PROCESSADO));

					new HistoricoNotaFiscalSaidaDAO().inserirInterface(this.popularHistorico(notaFiscal), Constantes.JNDI_SAP_SERVICO_LOCAL);
					
					//new HistoricoNotaFiscalSaidaDAO().inserirInterface(this.popularHistorico(notaFiscal), Constantes.JNDI_SAP_SERVICO_MATRIZ);					
					
					new NotaFiscalSaidaDAO().excluirInterface(notaFiscal, Constantes.JNDI_SAP_SERVICO_LOCAL);
					
					TSFacesUtil.addInfoMessage("Pedido com número " + notaFiscal.getPedidoVenda().getId() + " exportado com sucesso.");

				} else {

					TSFacesUtil.addErrorMessage("Não foi possível exportar o Pedido número : " + notaFiscal.getPedidoVenda().getId() + ". " + model.getMensagemErro());
				}
			}

		} else {

			TSFacesUtil.addErrorMessage("Selecione algum Pedido de Venda para realizar a Exportação.");
		}

		this.limpar();

		return null;
	}

	private HistoricoNotaFiscalSaida popularHistorico(NotaFiscalSaida model) {

		HistoricoNotaFiscalSaida h = new HistoricoNotaFiscalSaida();
		
		h.setCliente(model.getCliente());
		
		h.setCondicaoPagamento(model.getCondicaoPagamento());
		
		h.setDataDocumento(model.getDataDocumento());
		
		h.setDataEmissao(model.getDataEmissao());
		
		h.setDataDocumento(model.getDataDocumento());
		
		h.setDataExportacao(model.getDataExportacao());
		
		h.setDataLancamento(model.getDataLancamento());
		
		h.setDataImportacao(model.getDataImportacao());
		
		h.setEmpresa(model.getEmpresa());
		
		h.setEnderecoCobrancaFormatado(model.getEnderecoCobrancaFormatado());
		
		h.setEnderecoEntregaFormatado(model.getEnderecoEntregaFormatado());
		
		h.setId(model.getId());
		
		h.setIdExterno(model.getIdExterno());
		
		h.setLinhas(model.getLinhas());
		
		h.setObservacao(model.getObservacao());
		
		h.setOrigem(model.getOrigem());
		
		h.setParcelaNotaFiscalSaida(model.getParcelaNotaFiscalSaida());
		
		h.setParcelaNotaFiscalSaidaList(model.getParcelaNotaFiscalSaidaList());
		
		h.setSequencia(model.getSequencia());
		
		h.setPercentualDesconto(model.getPercentualDesconto());
		
		h.setSerial(model.getSerial());
		
		h.setStatus(model.getStatus());
		
		h.setTipo(model.getTipo());
		
		h.setTipoEnvio(model.getTipoEnvio());
		
		h.setTipoResumo(model.getTipoResumo());
		
		h.setValor(model.getValor());
		
		h.setVendedor(model.getVendedor());
		
		h.setPedidoVenda(model.getPedidoVenda());

		return h;

	}

	public String pesquisarInterfaceMatriz() {

		this.notasInterface = new NotaFiscalSaidaDAO().pesquisarInterface(new NotaFiscalSaida(), Constantes.JNDI_SAP_SERVICO_MATRIZ);

		TSFacesUtil.gerarResultadoLista(this.notasInterface);

		return null;
	}

	@Override
	protected String delete() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		new NotaFiscalSaidaDAO().excluirInterface(this.notaFiscalSaidaInterface, Constantes.JNDI_SAP_SERVICO_MATRIZ);

		new HistoricoNotaFiscalSaidaDAO().excluirInterface(this.notaFiscalSaidaInterface, Constantes.JNDI_SAP_SERVICO_MATRIZ);

		new HistoricoNotaFiscalSaidaDAO().excluirInterface(this.notaFiscalSaidaInterface, Constantes.JNDI_SAP_SERVICO_LOCAL);

		this.notasInterface.remove(this.notaFiscalSaidaInterface);

		TSFacesUtil.addInfoMessage("Registro excluído com sucesso.");

		return null;
	}

	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public List<NotaFiscalSaida> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaFiscalSaida> notas) {
		this.notas = notas;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public List<NotaFiscalSaida> getNotasInterface() {
		return notasInterface;
	}

	public void setNotasInterface(List<NotaFiscalSaida> notasInterface) {
		this.notasInterface = notasInterface;
	}

	public NotaFiscalSaida getNotaFiscalSaidaInterface() {
		return notaFiscalSaidaInterface;
	}

	public void setNotaFiscalSaidaInterface(NotaFiscalSaida notaFiscalSaidaInterface) {
		this.notaFiscalSaidaInterface = notaFiscalSaidaInterface;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}