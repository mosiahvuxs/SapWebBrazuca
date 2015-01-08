package br.com.brazuca.sapweb.faces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.dao.HistoricoPedidoVendaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.dao.PedidoVendaLinhaDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.model.HistoricoPedidoVenda;
import br.com.brazuca.sapweb.restful.PedidoVendaRestful;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.Status;
import br.com.brazuca.sapweb.sap.model.Vendedor;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "exportacaoPedidoVendaFaces")
public class ExportacaoPedidoVendaFaces extends TSMainFaces {

	private PedidoVenda pedidoVenda;
	private PedidoVenda pedidoVendaInterface;
	private List<PedidoVenda> lista;
	private List<PedidoVenda> listaInterfaceMatriz;
	private boolean todos;
	private Empresa empresa;

	public ExportacaoPedidoVendaFaces() {
		
		this.initObjetoNaSecao();

		this.limpar();
	}

	private void initObjetoNaSecao() {

		this.empresa = (Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA);
		
	}

	public void limpar() {

		this.pedidoVenda = new PedidoVenda();

		this.pedidoVenda.setCliente(new ParceiroNegocio());
		
		this.pedidoVenda.setVendedor(new Vendedor());
		
		this.pedidoVenda.setEmpresa(this.empresa);

		if (TSUtil.isEmpty(this.lista)) {

			this.lista = new ArrayList<PedidoVenda>();
		}

		this.todos = false;

		this.pedidoVendaInterface = new PedidoVenda();

		this.listaInterfaceMatriz = new ArrayList<PedidoVenda>();

	}

	@Override
	protected String find() {
		
		this.lista = new ArrayList<PedidoVenda>();
		
		for (PedidoVenda item : new PedidoVendaDAO().pesquisarInterface(this.pedidoVenda,Constantes.JNDI_SAP_SERVICO_LOCAL)) {
			
			item.setLinhas(new PedidoVendaLinhaDAO().pesquisarInterface(item,Constantes.JNDI_SAP_SERVICO_LOCAL));

			item.setEmpresa(new EmpresaDAO().obter(item.getEmpresa()));
			
			this.lista.add(item);
			
		}

		TSFacesUtil.gerarResultadoLista(this.lista);

		return null;
	}

	public void setarTodos() {

		for (PedidoVenda item : this.lista) {

			item.setSelecionado(this.todos);
		}

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);
		
		super.setDefaultMessage(false);
		
		List<PedidoVenda> listaExcluir = new ArrayList<PedidoVenda>();

		if (!TSUtil.isEmpty(this.lista)) {

			for (PedidoVenda pedido : lista) {

				pedido.setDataCriacao(new Timestamp(System.currentTimeMillis()));

				PedidoVenda model = new PedidoVendaRestful().inserirLote(pedido, Constantes.URL_RESTFUL_BRAZUCA_MATRIZ);

				if (TSUtil.isEmpty(model.getMensagemErro())) {

					listaExcluir.add(pedido);

					pedido.setStatus(new Status(Constantes.ID_STATUS_PROCESSADO));

					new HistoricoPedidoVendaDAO().inserirInterface(this.popularHistorico(pedido), Constantes.JNDI_SAP_SERVICO_LOCAL);
					
					new PedidoVendaDAO().excluirInterface(pedido, Constantes.JNDI_SAP_SERVICO_LOCAL);
					
					TSFacesUtil.addInfoMessage("Pedido com número " + pedido.getIdExterno() + " exportado com sucesso.");

				} else {

					TSFacesUtil.addErrorMessage("Não foi possível exportar o Pedido número : " + pedido.getIdExterno() + ". " + model.getMensagemErro());
				}
			}
			
			this.lista.removeAll(listaExcluir);

		} else {

			TSFacesUtil.addErrorMessage("Selecione algum Pedido de Venda para realizar a Exportação.");
		}

		this.limpar();

		return null;
	}

	private HistoricoPedidoVenda popularHistorico(PedidoVenda model) {
		
		HistoricoPedidoVenda h = new HistoricoPedidoVenda();
		
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

		return h;
	}

	public String pesquisarInterfaceMatriz() {

		this.listaInterfaceMatriz = new PedidoVendaDAO().pesquisarInterface(new PedidoVenda(this.empresa), Constantes.JNDI_SAP_SERVICO_MATRIZ);

		TSFacesUtil.gerarResultadoLista(this.listaInterfaceMatriz);

		return null;
		
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public PedidoVenda getPedidoVendaInterface() {
		return pedidoVendaInterface;
	}

	public void setPedidoVendaInterface(PedidoVenda pedidoVendaInterface) {
		this.pedidoVendaInterface = pedidoVendaInterface;
	}

	public List<PedidoVenda> getLista() {
		return lista;
	}

	public void setLista(List<PedidoVenda> lista) {
		this.lista = lista;
	}

	public List<PedidoVenda> getListaInterfaceMatriz() {
		return listaInterfaceMatriz;
	}

	public void setListaInterfaceMatriz(List<PedidoVenda> listaInterfaceMatriz) {
		this.listaInterfaceMatriz = listaInterfaceMatriz;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


}