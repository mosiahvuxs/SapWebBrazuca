package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class NotaFiscalSaidaDAO {
/*
	public void inserir(NotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("notafiscalsaida_id_seq"));

		broker.setPropertySQL("notafiscalsaidadao.inserir",

		model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCliente().getNome(), model.getVendedor().getNome(), model.getId().toString(), model.getEmpresa().getId(), model.getCliente().getId(), model.getVendedor().getId(), model.getEnderecoEntregaFormatado(), model.getEnderecoCobrancaFormatado(), model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getStatus().getId(), model.getPedidoVenda().getId());

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
*/
	@SuppressWarnings("unchecked")
	public List<NotaFiscalSaida> pesquisarInterface(NotaFiscalSaida model, String jndi) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		StringBuilder sql = new StringBuilder("SELECT ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, VALOR, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, ID_EXTERNO, EMPRESA_ID, CLIENTE_TIPO, CLIENTE_ID, VENDEDOR_ID, ENDERECO_ENTREGA, ENDERECO_COBRANCA, CLIENTE_IDENTIFICADOR_FEDERAL, OBSERVACAO, TIPO_RESUMO, TIPO, TIPO_ENVIO, PEDIDO_VENDA_ID FROM NOTAFISCALSAIDA WHERE 1 = 1");

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

        return broker.getCollectionBean(NotaFiscalSaida.class, "interfaceId", "dataLancamento", "dataDocumento", "dataVencimento", 
				   "condicaoPagamento.id", "valor","dataExportacao", 
				   "dataImportacao", "dataAtualizacao", "sequencia.id", "status.id",
				   "mensagemErro", "cliente.identificadorFiscal.tipoIdentificador",
				   "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia",
				   "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email",
				   "cliente.observacao", "cliente.endereco.logradouro","cliente.endereco.numero",
				   "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade",
				   "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id",
				   "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual",
				   "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal",
				   "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id", "vendedor.tipoIdentificador",
				   "vendedor.identificador", "vendedor.nome", "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "idExterno", "empresa.id", "cliente.tipo", "cliente.id",
				   "vendedor.id", "enderecoEntregaFormatado", "enderecoCobrancaFormatado", "cliente.identificadorFederal", "observacao", "tipoResumo", "tipo", "tipoEnvio", "pedidoVenda.id");

	}

	public void excluir(NotaFiscalSaida model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("notafiscalsaidadao.excluir", model.getId());

		broker.execute();

	}

	public void excluirInterface(NotaFiscalSaida model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		broker.setPropertySQL("notafiscalsaidadao.excluir", model.getInterfaceId());

		broker.execute();

	}
}
