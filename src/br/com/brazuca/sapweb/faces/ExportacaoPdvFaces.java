package br.com.brazuca.sapweb.faces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.HistoricoNotaFiscalSaidaDAO;
import br.com.brazuca.sapweb.dao.NotaFiscalSaidaDAO;
import br.com.brazuca.sapweb.dao.NotaFiscalSaidaLinhaDAO;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.restful.NotaFiscalSaidaRestful;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.Status;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "exportacaoPdvFaces")
public class ExportacaoPdvFaces extends TSMainFaces {

	private NotaFiscalSaida notaFiscalSaida;
	private List<NotaFiscalSaida> notas;
	private boolean todos;

	public ExportacaoPdvFaces() {

		this.limpar();
	}

	public void limpar() {

		this.notaFiscalSaida = new NotaFiscalSaida();
		this.notaFiscalSaida.setCliente(new ParceiroNegocio());

		this.notas = new ArrayList<NotaFiscalSaida>();

		this.todos = false;

	}

	private boolean validaCamposPesquisa() {

		if (TSUtil.isEmpty(TSUtil.tratarLong(this.notaFiscalSaida.getId())) && TSUtil.isEmpty(TSUtil.tratarString(this.notaFiscalSaida.getCliente().getNome())) && TSUtil.isEmpty(this.notaFiscalSaida.getDataDocumento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataDocumentoFinal()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataLancamento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataLancamentoFinal()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataVencimento()) && TSUtil.isEmpty(this.notaFiscalSaida.getDataVencimentoFinal())) {

			super.addErrorMessage("É necessário preencher algum dos campos para realizar a Pesquisa.");

			return false;
		}

		return true;
	}

	public String pesquisar() {

		if (this.validaCamposPesquisa()) {

			this.notas = new NotaFiscalSaidaDAO().pesquisar(this.notaFiscalSaida);

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
		NotaFiscalSaidaLinhaDAO notaFiscalSaidaLinhaDAO = new NotaFiscalSaidaLinhaDAO();

		for (NotaFiscalSaida nota : this.notas) {

			if (!TSUtil.isEmpty(nota.isSelecionado()) && nota.isSelecionado()) {

				nota.setLinhas(notaFiscalSaidaLinhaDAO.pesquisar(nota));

				if (!TSUtil.isEmpty(nota.getLinhas())) {

					notasFiscais.add(nota);

				}
			}
		}

		if (!TSUtil.isEmpty(notasFiscais)) {

			HistoricoNotaFiscalSaidaDAO historicoNotaFiscalSaidaDAO = new HistoricoNotaFiscalSaidaDAO();

			for (NotaFiscalSaida notaFiscal : notasFiscais) {

				notaFiscal.setDataCriacao(new Timestamp(System.currentTimeMillis()));

				NotaFiscalSaida model = new NotaFiscalSaidaRestful().inserirLote(notaFiscal);

				if (TSUtil.isEmpty(model.getMensagemErro())) {

					notaFiscal.setStatus(new Status(Constantes.ID_STATUS_PROCESSADO));

					historicoNotaFiscalSaidaDAO.inserir(new HistoricoNotaFiscalSaida(notaFiscal));

					TSFacesUtil.addInfoMessage("Pedido com número : " + notaFiscal.getPedidoVenda().getId() + " exportado com sucesso.");

				} else {

					TSFacesUtil.addErrorMessage("Não foi possível exportar o Pedido número : " + notaFiscal.getPedidoVenda().getId() + ". " + model.getMensagemErro());
				}
			}

		} else {

			TSFacesUtil.addErrorMessage("Selecione algum Pedido de Venda para realizar a Exportação.");
		}

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

}