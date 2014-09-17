package br.com.brazuca.sapweb.sap.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class PedidoVendaDAO {

	@SuppressWarnings("unchecked")
	public List<PedidoVenda> pesquisar(PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder("SELECT PVENDA.DOCENTRY AS ID, PVENDA.DOCDATE AS DATA_LANCAMENTO, PVENDA.DOCDUEDATE AS DATA_ENTREGA, PVENDA.TAXDATE AS DATA_DOCUMENTO, CLIENTE.CARDCODE AS CLIENTE_ID, CLIENTE.CARDNAME AS CLIENTE_NOME, CLIENTE.LICTRADNUM AS CLIENTE_IDENTIFICADOR_EXTERIOR, PVENDA.ADDRESS AS ENDERECO_COBRANCA_FORMATADO, PVENDA.ADDRESS2 AS ENDERECO_DESTINATARIO_FORMATADO, VENDEDOR.SLPCODE AS VENDEDOR_ID, VENDEDOR.SLPNAME AS VENDEDOR_NOME, PVENDA.DOCTOTALSY AS VALOR_TOTAL, PVENDA.COMMENTS AS OBSERVACAO, PVENDA.TRNSPCODE AS TIPO_DE_ENVIO, PVENDA.NUMATCARD AS ID_EXTERNO, PVENDA.GROUPNUM AS CONDICAO_PAGAMENTO, PVENDA.SUMMRYTYPE AS TIPO_RESUMO, PVENDA.DOCTYPE AS TIPO FROM ORDR AS PVENDA WITH(NOLOCK) INNER JOIN OCRD AS CLIENTE WITH(NOLOCK) ON (CLIENTE.CARDCODE = PVENDA.CARDCODE) INNER JOIN OSLP AS VENDEDOR WITH(NOLOCK) ON (VENDEDOR.SLPCODE = PVENDA.SLPCODE) WHERE 1 = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			sql.append(" AND PVENDA.DOCENTRY = ?");
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND CLIENTE.CARDNAME LIKE ?");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			sql.append(" AND PVENDA.DOCDUEDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			sql.append(" AND PVENDA.DOCDUEDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			sql.append(" AND PVENDA.TAXDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			sql.append(" AND PVENDA.TAXDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			sql.append(" AND PVENDA.DOCDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			sql.append(" AND PVENDA.DOCDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		sql.append(" ORDER BY PVENDA.TAXDATE");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			broker.set(model.getId());
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			broker.set("%" + model.getCliente().getNome() + "%");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			broker.set(TSParseUtil.dateToString(model.getDataVencimento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataVencimentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			broker.set(TSParseUtil.dateToString(model.getDataDocumento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataDocumentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			broker.set(TSParseUtil.dateToString(model.getDataLancamento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataLancamentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		return broker.getCollectionBean(PedidoVenda.class, "id", "dataLancamento", "dataVencimento", "dataDocumento", "cliente.id", "cliente.nome", "cliente.identificadorFederal", "cliente.endereco.logradouro", "cliente.enderecoDestinatario.logradouro", "vendedor.id", "vendedor.nome", "valor", "observacao", "tipoEnvio", "idExterno", "condicaoPagamento.id", "tipoResumo", "tipo");
	}

	public Long getQuantidadeLinhas(PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ORDR AS PVENDA WITH(NOLOCK) INNER JOIN OCRD AS CLIENTE WITH(NOLOCK) ON (CLIENTE.CARDCODE = PVENDA.CARDCODE) INNER JOIN OSLP AS VENDEDOR WITH(NOLOCK) ON (VENDEDOR.SLPCODE = PVENDA.SLPCODE) WHERE 1 = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			sql.append(" AND PVENDA.DOCENTRY = ?");
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND CLIENTE.CARDNAME LIKE ?");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			sql.append(" AND PVENDA.DOCDUEDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			sql.append(" AND PVENDA.DOCDUEDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			sql.append(" AND PVENDA.TAXDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			sql.append(" AND PVENDA.TAXDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			sql.append(" AND PVENDA.DOCDATE >= CONVERT(DATETIME, ?, 103) ");

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			sql.append(" AND PVENDA.DOCDATE <= CONVERT(DATETIME, ?, 103) ");

		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			broker.set(model.getId());
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			broker.set("%" + model.getCliente().getNome() + "%");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			broker.set(TSParseUtil.dateToString(model.getDataVencimento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataVencimentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			broker.set(TSParseUtil.dateToString(model.getDataDocumento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataDocumentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			broker.set(TSParseUtil.dateToString(model.getDataLancamento(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataLancamentoFinal(), TSDateUtil.DD_MM_YYYY));

		}

		return (Long) broker.getObject();

	}

}
