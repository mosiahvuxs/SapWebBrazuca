package br.com.brazuca.sapweb.dao;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoNotaFiscalSaidaDAO {
/*
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
*/
	public void excluirInterface(NotaFiscalSaida model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		broker.setPropertySQL("historiconotafiscalsaidadao.excluir", model.getPedidoVenda().getId());

		broker.execute();

	}

	public HistoricoNotaFiscalSaida inserirInterface(HistoricoNotaFiscalSaida model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_notafiscalsaida_id_seq"));
		
		broker.setPropertySQL("historiconotafiscalsaidadao.inserir", model.getInterfaceId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(),
				 													 model.getCondicaoPagamento().getId(), model.getValor(), model.getDataExportacao(), model.getDataImportacao(),
				 													 model.getDataAtualizacao(), model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(),
				 													 model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(),
				 													 model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(),
				 													 model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(),
				 													 model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(),
				 													 model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), model.getCliente().getEndereco().getCep(),
				 													 model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(),
				 													 model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(),
				 													 model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(),
				 													 model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getTipoIdentificador(),
				 													 model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(),
				 													 model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getTipo(), model.getCliente().getId(), model.getVendedor().getId(), model.getEnderecoEntregaFormatado(),
																	 model.getEnderecoCobrancaFormatado(),model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getPedidoVenda().getId());
		
		broker.execute();
		
		for (NotaFiscalSaidaLinha linha : model.getLinhas()) {
			
			linha.setNotaFiscalSaida(new NotaFiscalSaida());
			
			linha.getNotaFiscalSaida().setInterfaceId(model.getInterfaceId());
			
			new HistoricoNotaFiscalSaidaLinhaDAO().inserirComBroker(linha, broker);
			
		}
		
		broker.endTransaction();
		
		return model;
	}

}
