package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class HistoricoNotaFiscalSaidaDAO {

	public void inserir(HistoricoNotaFiscalSaida model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = null;

		if (TSUtil.isEmpty(jndi)) {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		} else {

			broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		}

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

	@SuppressWarnings("unchecked")
	public List<HistoricoNotaFiscalSaida> pesquisar(HistoricoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT PEDIDO_VENDA_ID, ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, VALOR, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, CLIENTE_NOME, VENDEDOR_NOME, ID_EXTERNO, EMPRESA_ID, CLIENTE_ID, VENDEDOR_ID, ENDERECO_ENTREGA, ENDERECO_COBRANCA, CLIENTE_IDENTIFICADOR_FEDERAL, OBSERVACAO, TIPO_RESUMO, TIPO, TIPO_ENVIO FROM PUBLIC.HISTORICO_NOTAFISCALSAIDA WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND PEDIDO_VENDA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getDataInicial())) {

			sql.append(" AND CAST(DATA_EXPORTACAO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");
		}

		if (!TSUtil.isEmpty(model.getDataFinal())) {

			sql.append(" AND CAST(DATA_EXPORTACAO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");
		}

		sql.append(" ORDER BY CLIENTE_NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());
		}

		if (!TSUtil.isEmpty(model.getDataInicial())) {

			broker.set(TSParseUtil.dateToString(model.getDataInicial(), TSDateUtil.DD_MM_YYYY));
		}

		if (!TSUtil.isEmpty(model.getDataFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataFinal(), TSDateUtil.DD_MM_YYYY));

		}

		return broker.getCollectionBean(HistoricoNotaFiscalSaida.class, "notaFiscalSaida.pedidoVenda.id", "id", "notaFiscalSaida.dataLancamento", "notaFiscalSaida.dataDocumento", "notaFiscalSaida.dataVencimento", "notaFiscalSaida.condicaoPagamento.id", "notaFiscalSaida.valor", "notaFiscalSaida.dataExportacao", "notaFiscalSaida.dataImportacao", "notaFiscalSaida.dataAtualizacao", "notaFiscalSaida.cliente.nome", "notaFiscalSaida.vendedor.nome", "notaFiscalSaida.idExterno", "notaFiscalSaida.empresa.id", "notaFiscalSaida.cliente.id", "notaFiscalSaida.vendedor.id", "notaFiscalSaida.enderecoEntregaFormatado", "notaFiscalSaida.enderecoCobrancaFormatado", "notaFiscalSaida.cliente.identificadorFederal", "notaFiscalSaida.observacao", "notaFiscalSaida.tipoResumo", "notaFiscalSaida.tipo", "notaFiscalSaida.tipoEnvio");
	}

}
