package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;

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

		model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCliente().getNome(), model.getVendedor().getNome(), model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getId(), model.getVendedor().getId(), model.getEnderecoEntregaFormatado(), model.getEnderecoCobrancaFormatado(), model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getStatus().getId(), model.getPedidoVenda().getId());

		broker.execute();

		HistoricoNotaFiscalSaidaLinhaDAO historicoNotaFiscalSaidaLinhaDAO = new HistoricoNotaFiscalSaidaLinhaDAO();

		for (NotaFiscalSaidaLinha linha : model.getLinhas()) {

			historicoNotaFiscalSaidaLinhaDAO.inserir((HistoricoNotaFiscalSaidaLinha) linha, broker);
		}

		new NotaFiscalSaidaDAO().excluir(model, broker);

		broker.endTransaction();
	}

}
