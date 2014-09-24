package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class NotaFiscalSaidaDAO {

	public void inserir(NotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("notafiscalsaida_id_seq"));

		broker.setPropertySQL("notafiscalsaidadao.inserir",

		model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCliente().getNome(), model.getVendedor().getNome(), model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getId(), model.getVendedor().getId(), model.getCliente().getEnderecoDestinatario().getLogradouro(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getStatus().getId(), model.getPedidoVenda().getId());

		broker.execute();

		NotaFiscalSaidaLinhaDAO notaFiscalSaidaLinhaDAO = new NotaFiscalSaidaLinhaDAO();

		for (NotaFiscalSaidaLinha linha : model.getLinhas()) {

			linha.setNotaFiscalSaida(model);

			notaFiscalSaidaLinhaDAO.inserir(linha, broker);
		}

		new PedidoVendaDAO().excluir(model.getPedidoVenda(), broker);

		broker.endTransaction();
	}

	public void inserir(List<NotaFiscalSaida> notasFiscais) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		for (NotaFiscalSaida model : notasFiscais) {

			model.setId(broker.getSequenceNextValue("notafiscalsaida_id_seq"));

			broker.setPropertySQL("notafiscalsaidadao.inserir", model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCliente().getNome(), model.getVendedor().getNome(), model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getId(), model.getVendedor().getId(), model.getEnderecoEntregaFormatado(), model.getEnderecoCobrancaFormatado(), model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getPedidoVenda().getId());

			broker.execute();

			NotaFiscalSaidaLinhaDAO notaFiscalSaidaLinhaDAO = new NotaFiscalSaidaLinhaDAO();

			for (NotaFiscalSaidaLinha linha : model.getLinhas()) {

				linha.setNotaFiscalSaida(model);

				notaFiscalSaidaLinhaDAO.inserir(linha, broker);
			}
		}

		broker.endTransaction();
	}

	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaida> pesquisar(NotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT PEDIDO_VENDA_ID, ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, VALOR, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, CLIENTE_NOME, VENDEDOR_NOME, ID_EXTERNO, EMPRESA_ID, CLIENTE_ID, VENDEDOR_ID, ENDERECO_ENTREGA, ENDERECO_COBRANCA, CLIENTE_IDENTIFICADOR_FEDERAL, OBSERVACAO, TIPO_RESUMO, TIPO, TIPO_ENVIO FROM PUBLIC.NOTAFISCALSAIDA WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND PEDIDO_VENDA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND SEM_ACENTOS(CLIENTE_NOME) ILIKE SEM_ACENTOS(?)");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			sql.append(" AND CAST(DATA_VENCIMENTO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			sql.append(" AND CAST(DATA_VENCIMENTO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			sql.append(" AND CAST(DATA_DOCUMENTO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			sql.append(" AND CAST(DATA_DOCUMENTO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			sql.append(" AND CAST(DATA_LANCAMENTO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			sql.append(" AND CAST(DATA_LANCAMENTO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");

		}

		sql.append(" ORDER BY CLIENTE_NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getId())) {

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

		return broker.getCollectionBean(NotaFiscalSaida.class, "pedidoVenda.id", "id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "valor", "dataExportacao", "dataImportacao", "dataAtualizacao", "cliente.nome", "vendedor.nome", "idExterno", "empresa.id", "cliente.id", "vendedor.id", "enderecoEntregaFormatado", "enderecoCobrancaFormatado", "cliente.identificadorFederal", "observacao", "tipoResumo", "tipo", "tipoEnvio");
	}

	public void excluir(NotaFiscalSaida model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("notafiscalsaidadao.excluir", model.getId());

		broker.execute();

	}
}
