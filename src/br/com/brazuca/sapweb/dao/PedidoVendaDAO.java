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

		this.excluir(model, broker);

		broker.beginTransaction();

		Long id = broker.getSequenceNextValue("pedido_vendas_id_seq");

		broker.setSQL("pedidovendadao.inserir", id, model.getId(), model.getDataLancamento(), model.getDataVencimento(), model.getDataDocumento(), model.getCliente().getId(), model.getCliente().getNome(), model.getCliente().getIdentificadorFederal(), model.getEnderecoCobrancaFormatado(), model.getEnderecoEntregaFormatado(), model.getVendedor().getId(), model.getVendedor().getNome(), model.getValor(), model.getObservacao(), model.getTipoEnvio(), model.getIdExterno(), model.getCondicaoPagamento().getId(), model.getTipoResumo(), model.getTipo());

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

			this.excluir(model, broker);

			Long id = broker.getSequenceNextValue("pedido_vendas_id_seq");

			broker.setPropertySQL("pedidovendadao.inserir",

			id, model.getId(), model.getDataLancamento(), model.getDataVencimento(), model.getDataDocumento(), model.getCliente().getId(), model.getCliente().getNome(), model.getCliente().getIdentificadorFederal(), model.getEnderecoCobrancaFormatado(), model.getEnderecoEntregaFormatado(), model.getVendedor().getId(), model.getVendedor().getNome(), model.getValor(), model.getObservacao(), model.getTipoEnvio(), model.getIdExterno(), model.getCondicaoPagamento().getId(), model.getTipoResumo(), model.getTipo(), model.getEmpresa().getId());

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
	public List<PedidoVenda> pesquisar(PedidoVenda model, String filtro) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT PV.EMPRESA_ID, PV.ID, PV.CODIGO_SAP, PV.DATA_LANCAMENTO, PV.DATA_VENCIMENTO, PV.DATA_DOCUMENTO, PV.CLIENTE_ID, PV.CLIENTE_NOME, PV.CLIENTE_IDENTIFICADOR_EXTERIOR, PV.ENDERECO_COBRANCA, PV.ENDERECO_DESTINO, PV.VENDEDOR_ID, PV.VENDEDOR_NOME, PV.VALOR_TOTAL, PV.OBSERVACAO, PV.TIPO_ENVIO, PV.ID_EXTERNO, PV.CONDICAO_PAGAMENTO, PV.TIPO_RESUMO, PV.TIPO, PV.DATA_IMPORTACAO, PV.EMPRESA_ID, PV.CODIGO_SAP FROM PUBLIC.PEDIDO_VENDAS PV WHERE 1 = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarLong(model.getId()))) {

			sql.append(" AND PV.CODIGO_SAP = ?");
		}

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND SEM_ACENTOS(PV.CLIENTE_NOME) ILIKE SEM_ACENTOS(?)");
		}

		if (!TSUtil.isEmpty(model.getDataVencimento())) {

			sql.append(" AND PV.DATA_VENCIMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataVencimentoFinal())) {

			sql.append(" AND PV.DATA_VENCIMENTO <= ?");

		}

		if (!TSUtil.isEmpty(model.getDataDocumento())) {

			sql.append(" AND PV.DATA_DOCUMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataDocumentoFinal())) {

			sql.append(" AND PV.DATA_DOCUMENTO <= ?");

		}

		if (!TSUtil.isEmpty(model.getDataLancamento())) {

			sql.append(" AND PV.DATA_LANCAMENTO >= ?");

		}

		if (!TSUtil.isEmpty(model.getDataLancamentoFinal())) {

			sql.append(" AND PV.DATA_LANCAMENTO <= ?");

		}

		if (!TSUtil.isEmpty(filtro)) {

			sql.append(filtro);
		}

		sql.append(" ORDER BY PV.CLIENTE_NOME");

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

		return broker.getCollectionBean(PedidoVenda.class, "empresa.id", "serial", "id", "dataLancamento", "dataVencimento", "dataDocumento", "cliente.id", "cliente.nome", "cliente.identificadorFederal", "enderecoCobrancaFormatado", "enderecoEntregaFormatado", "vendedor.id", "vendedor.nome", "valor", "observacao", "tipoEnvio", "idExterno", "condicaoPagamento.id", "tipoResumo", "tipo");
	}

	public void excluir(PedidoVenda model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("pedidovendadao.excluir", model.getId());

		broker.execute();

	}

}
