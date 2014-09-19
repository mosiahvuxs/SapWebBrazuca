package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class PedidoVendaDAO {

	public void inserir(PedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		Long id = broker.getSequenceNextValue("pedido_vendas_id_seq");

		broker.setSQL("pedidovendadao.inserir", id, model.getId(), model.getDataLancamento(), model.getDataVencimento(), model.getDataDocumento(), model.getCliente().getId(), model.getCliente().getNome(), model.getCliente().getIdentificadorFederal(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEnderecoDestinatario().getLogradouro(), model.getVendedor().getId(), model.getVendedor().getNome(), model.getValor(), model.getObservacao(), model.getTipoEnvio(), model.getIdExterno(), model.getCondicaoPagamento().getId(), model.getTipoResumo(), model.getTipo());

		PedidoVendaLinhaDAO pedidoVendaLinhaDAO = new PedidoVendaLinhaDAO();

		for (PedidoVendaLinha linha : model.getLinhas()) {

			linha.setPedidoVenda(new PedidoVenda());

			linha.getPedidoVenda().setId(id);

			pedidoVendaLinhaDAO.inserir(linha, broker);

		}

		broker.endTransaction();

	}

	public void inserir(List<PedidoVenda> pedidos) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		PedidoVendaLinhaDAO pedidoVendaLinhaDAO = new PedidoVendaLinhaDAO();

		for (PedidoVenda model : pedidos) {

			Long id = broker.getSequenceNextValue("pedido_vendas_id_seq");

			broker.setPropertySQL("pedidovendadao.inserir",

			id, model.getId(), model.getDataLancamento(), model.getDataVencimento(), model.getDataDocumento(), model.getCliente().getId(), model.getCliente().getNome(), model.getCliente().getIdentificadorFederal(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEnderecoDestinatario().getLogradouro(), model.getVendedor().getId(), model.getVendedor().getNome(), model.getValor(), model.getObservacao(), model.getTipoEnvio(), model.getIdExterno(), model.getCondicaoPagamento().getId(), model.getTipoResumo(), model.getTipo(), model.getEmpresa().getId());

			broker.execute();

			for (PedidoVendaLinha linha : model.getLinhas()) {

				linha.setPedidoVenda(new PedidoVenda());

				linha.getPedidoVenda().setId(id);

				pedidoVendaLinhaDAO.inserir(linha, broker);

			}
		}

		broker.endTransaction();

	}

	@SuppressWarnings("unchecked")
	public List<br.com.brazuca.sapweb.model.PedidoVenda> pesquisar(br.com.brazuca.sapweb.model.PedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT ID, DATA_LANCAMENTO, DATA_VENCIMENTO, DATA_DOCUMENTO, CLIENTE_ID, CLIENTE_NOME, CLIENTE_IDENTIFICADOR_EXTERIOR, ENDERECO_COBRANCA, ENDERECO_DESTINO, VENDEDOR_ID, VENDEDOR_NOME, VALOR_TOTAL, OBSERVACAO, TIPO_ENVIO, ID_EXTERNO, CONDICAO_PAGAMENTO, TIPO_RESUMO, TIPO, DATA_IMPORTACAO, EMPRESA_ID, CODIGO_SAP FROM PUBLIC.PEDIDO_VENDAS WHERE 1 = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getCodigo()))) {

			sql.append(" AND CODIGO_SAP = ?");
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND SEM_ACENTOS(CLIENTE_NOME) ILIKE SEM_ACENTOS(?)");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			sql.append(" AND DATA_VENCIMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			sql.append(" AND DATA_VENCIMENTO <= ?");

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			sql.append(" AND DATA_DOCUMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			sql.append(" AND DATA_DOCUMENTO <= ?");

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			sql.append(" AND DATA_LANCAMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			sql.append(" AND DATA_LANCAMENTO <= ?");

		}

		sql.append(" ORDER BY CLIENTE_NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			broker.set(model.getId());
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			broker.set("%" + model.getCliente().getNome() + "%");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			broker.set(model.getDataVencimento());

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			broker.set(model.getDataVencimentoFinal());

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			broker.set(model.getDataDocumento());

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			broker.set(model.getDataDocumentoFinal());

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			broker.set(model.getDataLancamento());

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			broker.set(model.getDataLancamentoFinal());

		}

		return broker.getCollectionBean(PedidoVenda.class, "id", "dataLancamento", "dataVencimento", "dataDocumento", "cliente.id", "cliente.nome", "cliente.identificadorFederal", "cliente.endereco.logradouro", "cliente.enderecoDestinatario.logradouro", "vendedor.id", "vendedor.nome", "valor", "observacao", "tipoEnvio", "idExterno", "condicaoPagamento.id", "tipoResumo", "tipo");
	}

}
