package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoNotaFiscalSaidaDAO {

	public void inserir(HistoricoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("historico_notafiscalsaida_id_seq"));

		broker.setPropertySQL("historiconotafiscalsaidadao.inserir",

		model.getId(), model.getNotaFiscalSaida().getDataLancamento(), model.getNotaFiscalSaida().getDataDocumento(), model.getNotaFiscalSaida().getDataVencimento(), model.getNotaFiscalSaida().getCondicaoPagamento().getId(), model.getNotaFiscalSaida().getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getNotaFiscalSaida().getCliente().getNome(), model.getNotaFiscalSaida().getVendedor().getNome(), model.getNotaFiscalSaida().getIdExterno(), model.getNotaFiscalSaida().getEmpresa().getId(), model.getNotaFiscalSaida().getCliente().getId(), model.getNotaFiscalSaida().getVendedor().getId(), model.getNotaFiscalSaida().getEnderecoEntregaFormatado(), model.getNotaFiscalSaida().getEnderecoCobrancaFormatado(), model.getNotaFiscalSaida().getCliente().getIdentificadorFederal(), model.getNotaFiscalSaida().getObservacao(), model.getNotaFiscalSaida().getTipoResumo(), model.getNotaFiscalSaida().getTipo(), model.getNotaFiscalSaida().getTipoEnvio(),
				model.getNotaFiscalSaida().getStatus().getId(), model.getNotaFiscalSaida().getPedidoVenda().getId());

		broker.execute();

		HistoricoNotaFiscalSaidaLinhaDAO historicoNotaFiscalSaidaLinhaDAO = new HistoricoNotaFiscalSaidaLinhaDAO();

		for (NotaFiscalSaidaLinha linha : model.getNotaFiscalSaida().getLinhas()) {

			HistoricoNotaFiscalSaidaLinha historicoNotaFiscalSaidaLinha = new HistoricoNotaFiscalSaidaLinha();
			historicoNotaFiscalSaidaLinha.setHistoricoNotaFiscalSaida(model);
			historicoNotaFiscalSaidaLinha.setNotaFiscalSaidaLinha(linha);

			historicoNotaFiscalSaidaLinhaDAO.inserir(historicoNotaFiscalSaidaLinha, broker);
		}

		new NotaFiscalSaidaDAO().excluir(model.getNotaFiscalSaida(), broker);

		broker.endTransaction();
	}

	public void inserir(List<HistoricoNotaFiscalSaida> notasFiscais) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		for (HistoricoNotaFiscalSaida model : notasFiscais) {

			model.setId(broker.getSequenceNextValue("historico_notafiscalsaida_id_seq"));

			broker.setPropertySQL("historiconotafiscalsaidadao.inserir",

			model.getId(), model.getNotaFiscalSaida().getDataLancamento(), model.getNotaFiscalSaida().getDataDocumento(), model.getNotaFiscalSaida().getDataVencimento(), model.getNotaFiscalSaida().getCondicaoPagamento().getId(), model.getNotaFiscalSaida().getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getNotaFiscalSaida().getCliente().getNome(), model.getNotaFiscalSaida().getVendedor().getNome(), model.getNotaFiscalSaida().getIdExterno(), model.getNotaFiscalSaida().getEmpresa().getId(), model.getNotaFiscalSaida().getCliente().getId(), model.getNotaFiscalSaida().getVendedor().getId(), model.getNotaFiscalSaida().getEnderecoEntregaFormatado(), model.getNotaFiscalSaida().getEnderecoCobrancaFormatado(), model.getNotaFiscalSaida().getCliente().getIdentificadorFederal(), model.getNotaFiscalSaida().getObservacao(), model.getNotaFiscalSaida().getTipoResumo(), model.getNotaFiscalSaida().getTipo(),
					model.getNotaFiscalSaida().getTipoEnvio(), model.getNotaFiscalSaida().getStatus().getId(), model.getNotaFiscalSaida().getPedidoVenda().getId());

			broker.execute();

			HistoricoNotaFiscalSaidaLinhaDAO historicoNotaFiscalSaidaLinhaDAO = new HistoricoNotaFiscalSaidaLinhaDAO();

			for (NotaFiscalSaidaLinha linha : model.getNotaFiscalSaida().getLinhas()) {

				HistoricoNotaFiscalSaidaLinha historicoNotaFiscalSaidaLinha = new HistoricoNotaFiscalSaidaLinha();
				historicoNotaFiscalSaidaLinha.setHistoricoNotaFiscalSaida(model);
				historicoNotaFiscalSaidaLinha.setNotaFiscalSaidaLinha(linha);

				historicoNotaFiscalSaidaLinhaDAO.inserir(historicoNotaFiscalSaidaLinha, broker);
			}

			new NotaFiscalSaidaDAO().excluir(model.getNotaFiscalSaida(), broker);
		}

		broker.endTransaction();
	}

}
