package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class HistoricoNotaFiscalSaidaLinhaDAO {

	public void inserir(HistoricoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("historiconotafiscalsaidalinhadao.inserir",

		model.getHistoricoNotaFiscalSaida().getId(), model.getNotaFiscalSaidaLinha().getItem().getId(), model.getNotaFiscalSaidaLinha().getQuantidade(), model.getNotaFiscalSaidaLinha().getValorUnitario(), model.getNotaFiscalSaidaLinha().getValor(),

		model.getNotaFiscalSaidaLinha().getCodigoImposto().getId(), model.getNotaFiscalSaidaLinha().getCodigoBarras(), model.getNotaFiscalSaidaLinha().getPedidoVendaLinha().getNumero(), model.getNotaFiscalSaidaLinha().getPedidoVendaLinha().getPedidoVenda().getId(), model.getNotaFiscalSaidaLinha().getPedidoVendaLinha().getItem().getDescricao());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<HistoricoNotaFiscalSaidaLinha> pesquisar(HistoricoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT H.CLIENTE_NOME, HL.ID, HL.HISTORICO_NOTAFISCALSAIDA_ID, H.DATA_DOCUMENTO, H.DATA_LANCAMENTO, H.PEDIDO_VENDA_ID, H.DATA_EXPORTACAO, HL.ITEM_ID, HL.QUANTIDADE, HL.VALOR_UNITARIO, HL.VALOR, HL.CODIGO_IMPOSTO_ID, HL.CODIGO_BARRAS, HL.PEDIDO_VENDA_LINHA_NUMERO, HL.DESCRICAO FROM PUBLIC.HISTORICO_NOTAFISCALSAIDA_LINHAS HL, PUBLIC.HISTORICO_NOTAFISCALSAIDA H WHERE HL.HISTORICO_NOTAFISCALSAIDA_ID = H.ID");

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND HL.HISTORICO_NOTAFISCALSAIDA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getDataInicial())) {

			sql.append(" AND CAST(H.DATA_EXPORTACAO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataFinal())) {

			sql.append(" AND CAST(H.DATA_EXPORTACAO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");

		}

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

		return broker.getCollectionBean(HistoricoNotaFiscalSaidaLinha.class, 
				
		"historicoNotaFiscalSaida.notaFiscalSaida.cliente.nome", "id", "historicoNotaFiscalSaida.id", "dataDocumento", "dataLancamento", "notaFiscalSaidaLinha.pedidoVendaLinha.pedidoVenda.id", "dataExportacao",
		"notaFiscalSaidaLinha.item.id", "notaFiscalSaidaLinha.quantidade", "notaFiscalSaidaLinha.valorUnitario", "notaFiscalSaidaLinha.valor", "notaFiscalSaidaLinha.codigoImposto.id", "notaFiscalSaidaLinha.codigoBarras", "notaFiscalSaidaLinha.pedidoVendaLinha.numero", "notaFiscalSaidaLinha.item.descricao");
	}

}
