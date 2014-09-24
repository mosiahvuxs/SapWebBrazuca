package br.com.brazuca.sapweb.business;

import java.util.ArrayList;
import java.util.List;

import br.com.brazuca.sapweb.dao.NotaFiscalSaidaDAO;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.brazuca.sapweb.sap.model.Status;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;

public class NotaFiscalSaidaBusiness {

	public void inserir(PedidoVenda model, List<PedidoVendaLinha> linhas) throws TSApplicationException {

		NotaFiscalSaida notaFiscal = new NotaFiscalSaida();

		notaFiscal.setPedidoVenda(model);
		notaFiscal.setDataLancamento(model.getDataLancamento());
		notaFiscal.setDataDocumento(model.getDataDocumento());
		notaFiscal.setDataVencimento(model.getDataVencimento());
		notaFiscal.setCondicaoPagamento(model.getCondicaoPagamento());
		notaFiscal.setValor(model.getValor());
		notaFiscal.setCliente(model.getCliente());
		notaFiscal.setVendedor(model.getVendedor());
		notaFiscal.setIdExterno(model.getIdExterno());
		notaFiscal.setEmpresa(model.getEmpresa());
		notaFiscal.setObservacao(model.getObservacao());
		notaFiscal.setTipoResumo(model.getTipoResumo());
		notaFiscal.setTipo(model.getTipo());
		notaFiscal.setTipoEnvio(model.getTipoEnvio());
		notaFiscal.setStatus(new Status(Constantes.ID_STATUS_NAO_PROCESSADO));

		notaFiscal.setLinhas(new ArrayList<NotaFiscalSaidaLinha>());

		for (PedidoVendaLinha linha : linhas) {

			NotaFiscalSaidaLinha saidaLinha = new NotaFiscalSaidaLinha();

			saidaLinha.setItem(linha.getItem());
			saidaLinha.setQuantidade(linha.getQuantidadeLiberada());
			saidaLinha.setValorUnitario(linha.getValorUnitario());
			saidaLinha.setValor(linha.getValor());
			saidaLinha.setCodigoImposto(linha.getCodigoImposto());
			saidaLinha.setCodigoBarras(linha.getCodigoBarras());
			saidaLinha.setPedidoVendaLinha(linha);
			saidaLinha.getPedidoVendaLinha().setPedidoVenda(model);

			notaFiscal.getLinhas().add(saidaLinha);

		}

		new NotaFiscalSaidaDAO().inserir(notaFiscal);
	}
}
