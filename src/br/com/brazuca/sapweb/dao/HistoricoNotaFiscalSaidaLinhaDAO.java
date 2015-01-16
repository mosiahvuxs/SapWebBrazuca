package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class HistoricoNotaFiscalSaidaLinhaDAO {
/*
	public void inserir(NotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("historiconotafiscalsaidalinhadao.inserir",

		model.getId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(),

		model.getCodigoImposto().getId(), model.getCodigoBarras(), model.getPedidoVendaLinha().getNumero(), model.getPedidoVendaLinha().getPedidoVenda().getId(), 
		
		model.getItem().getDescricao());

		broker.execute();

	}
*/
	@SuppressWarnings("unchecked")
	public List<HistoricoNotaFiscalSaidaLinha> pesquisar(HistoricoNotaFiscalSaida model, String jndi) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);

		StringBuilder sql = new StringBuilder("SELECT H.CLIENTE_NOME, HL.ID, HL.HISTORICO_NOTAFISCALSAIDA_ID, H.DATA_DOCUMENTO, H.DATA_LANCAMENTO, H.PEDIDO_VENDA_ID, H.DATA_EXPORTACAO, HL.ITEM_ID, HL.QUANTIDADE, HL.VALOR_UNITARIO, HL.VALOR, HL.CODIGO_IMPOSTO_ID, HL.CODIGO_BARRAS, HL.PEDIDO_VENDA_LINHA_NUMERO, HL.ITEM_DESCRICAO FROM PUBLIC.HISTORICO_NOTAFISCALSAIDA_LINHAS HL, PUBLIC.HISTORICO_NOTAFISCALSAIDA H WHERE HL.HISTORICO_NOTAFISCALSAIDA_ID = H.ID");
		
		if(!TSUtil.isEmpty(model.getStatus()) && (!TSUtil.isEmpty(Utilitarios.tratarLong(model.getStatus().getId())))){
			
			sql.append(" AND H.STATUS_ID = ? ");
			
		}

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getPedidoVenda()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getPedidoVenda().getId()))) {

			sql.append(" AND HL.PEDIDO_VENDA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getDataInicial())) {

			sql.append(" AND CAST(H.DATA_EXPORTACAO AS DATE) >= TO_DATE(?, 'DD/MM/YYYY')");

		}

		if (!TSUtil.isEmpty(model.getDataFinal())) {

			sql.append(" AND CAST(H.DATA_EXPORTACAO AS DATE) <= TO_DATE(?, 'DD/MM/YYYY')");

		}

		broker.setSQL(sql.toString());
		
		if(!TSUtil.isEmpty(model.getStatus()) && (!TSUtil.isEmpty(Utilitarios.tratarLong(model.getStatus().getId())))){
			
			broker.set(model.getStatus().getId());
			
		}		

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getPedidoVenda()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getPedidoVenda().getId()))) {

			broker.set(model.getPedidoVenda().getId());
		}

		if (!TSUtil.isEmpty(model.getDataInicial())) {

			broker.set(TSParseUtil.dateToString(model.getDataInicial(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getDataFinal())) {

			broker.set(TSParseUtil.dateToString(model.getDataFinal(), TSDateUtil.DD_MM_YYYY));

		}

		return broker.getCollectionBean(HistoricoNotaFiscalSaidaLinha.class, 
				
		"notaFiscalSaida.cliente.nome", "id", "notaFiscalSaida.id", "notaFiscalSaida.dataDocumento", "notaFiscalSaida.dataLancamento", "notaFiscalSaida.pedidoVenda.id", "notaFiscalSaida.dataExportacao",
		"item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", 
		"codigoBarras", "numero", "item.descricao");
	}

	public void inserirComBroker(NotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_notafiscalsaida_linhas_id_seq"));

		broker.setPropertySQL("historiconotafiscalsaidalinhadao.inserirComBroker", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                  model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getId(), model.getCstICMS().getId(), model.getCstIPI().getId(), model.getCstPIS().getId(), model.getContaContabil().getId(), model.getCfop().getCodigo(), model.getCodigoBarras(), model.getUtilizacao().getId(),
				                  model.getPedidoVendaLinha().getNumero(), model.getPedidoVendaLinha().getPedidoVenda().getId(), model.getItem().getDescricao());
		
		broker.execute();
		
	}

}
