package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.business.ItemEstruturadoBusiness;
import br.com.brazuca.sapweb.dao.NotaFiscalSaidaLinhaDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.sap.dao.PedidoVendaDAO;
import br.com.brazuca.sapweb.sap.dao.PedidoVendaLinhaDAO;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "importacaoPdvFaces")
public class ImportacaoPdvFaces extends TSMainFaces {

	private PedidoVenda pedidoVenda;
	private List<PedidoVenda> pedidosVenda;
	private boolean todos;
	private boolean importarItensEstruturados;
	private Empresa empresa;

	@PostConstruct
	public void init() {

		this.initObjetosNaSecao();
		
		this.clearFields();

	}	
	
	private void initObjetosNaSecao() {

		this.setEmpresa((Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA));

	}	

	@Override
	protected void clearFields() {

		this.pedidoVenda = new PedidoVenda();
		this.pedidoVenda.setCliente(new ParceiroNegocio());
		this.pedidoVenda.setEmpresa(this.empresa);
		this.pedidosVenda = new ArrayList<PedidoVenda>();
		this.todos = false;
		this.importarItensEstruturados = false;
	}

	public void limpar() {

		this.clearFields();
	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.pedidoVenda.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.pedidoVenda.getCliente().getNome())) && TSUtil.isEmpty(this.pedidoVenda.getDataDocumento()) && TSUtil.isEmpty(this.pedidoVenda.getDataDocumentoFinal()) && TSUtil.isEmpty(this.pedidoVenda.getDataLancamento()) && TSUtil.isEmpty(this.pedidoVenda.getDataLancamentoFinal()) && TSUtil.isEmpty(this.pedidoVenda.getDataVencimento()) && TSUtil.isEmpty(this.pedidoVenda.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}
	
	@Override
	protected String find() {

		this.pedidosVenda = new ArrayList<PedidoVenda>();

		if (this.validaCamposPesquisa()) {

			List<PedidoVenda> pedidos = new PedidoVendaDAO().pesquisar(this.pedidoVenda);

			NotaFiscalSaidaLinhaDAO notaFiscalSaidaDAO = new NotaFiscalSaidaLinhaDAO();

			for (PedidoVenda pdv : pedidos) {

				if (TSUtil.isEmpty(notaFiscalSaidaDAO.pesquisarPorPedidoVenda(pdv, Constantes.JNDI_SAP_SERVICO_MATRIZ)) && TSUtil.isEmpty(notaFiscalSaidaDAO.pesquisarPorPedidoVenda(pdv, Constantes.JNDI_SAP_SERVICO_LOCAL))) {

					this.pedidosVenda.add(pdv);
				}
			}

			TSFacesUtil.gerarResultadoLista(this.pedidosVenda);
			
		}

		return null;

	}
		
	public void setarTodos() {

		for (PedidoVenda item : this.pedidosVenda) {

			item.setSelecionado(this.todos);
		}

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);
		super.setDefaultMessage(false);

		List<PedidoVenda> pedidosSelecionados = new ArrayList<PedidoVenda>();

		for (PedidoVenda item : this.pedidosVenda) {

			if (!TSUtil.isEmpty(item.isSelecionado()) && item.isSelecionado()) {

				item.setEmpresa(this.pedidoVenda.getEmpresa());

				item.setLinhas(new PedidoVendaLinhaDAO().pesquisar(item));

				if (!TSUtil.isEmpty(item.getLinhas())) {

					pedidosSelecionados.add(item);

				}
			}
		}

		if (!TSUtil.isEmpty(pedidosSelecionados)) {

			try {
				
				new br.com.brazuca.sapweb.dao.PedidoVendaDAO().inserir(pedidosSelecionados);

				if (this.importarItensEstruturados) {

					new ItemEstruturadoBusiness().importar(Utilitarios.getEmpresaConectada());
				}

				this.limpar();

				super.addInfoMessage("Importação realizada com sucesso.");

			} catch (TSApplicationException e) {

				super.addErrorMessage("Não foi possível realizar a operação. Entre em contato com a T.I");

				e.printStackTrace();
			}

		} else {

			super.addErrorMessage("Para realizar a operação selecione algum Pedido de Venda.");

		}

		return null;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public List<PedidoVenda> getPedidosVenda() {
		return pedidosVenda;
	}

	public void setPedidosVenda(List<PedidoVenda> pedidosVenda) {
		this.pedidosVenda = pedidosVenda;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public boolean isImportarItensEstruturados() {
		return importarItensEstruturados;
	}

	public void setImportarItensEstruturados(boolean importarItensEstruturados) {
		this.importarItensEstruturados = importarItensEstruturados;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
}