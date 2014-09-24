package br.com.brazuca.sapweb.dao;

import java.sql.Timestamp;

import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HitoricoNotaFiscalSaidaDAO {

	public void inserir(NotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		Long historicoId = broker.getSequenceNextValue("historico_notafiscalsaida_id_seq");

		broker.setPropertySQL("historiconotafiscalsaidadao.inserir",

		historicoId, model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getValor(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), model.getCliente().getNome(), model.getVendedor().getNome(), model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getId(), model.getVendedor().getId(), model.getCliente().getEnderecoDestinatario().getLogradouro(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio(), model.getStatus().getId());

		broker.execute();

		HistoricoNotaFiscalSaidaLinhaDAO historicoNotaFiscalSaidaLinhaDAO = new HistoricoNotaFiscalSaidaLinhaDAO();

		for (NotaFiscalSaidaLinha linha : model.getLinhas()) {

			linha.setNotaFiscalSaida(model);
			
			linha.getNotaFiscalSaida().setId(historicoId);

			historicoNotaFiscalSaidaLinhaDAO.inserir(linha, broker);
		}

		new NotaFiscalSaidaDAO().excluir(model, broker);

		broker.endTransaction();
	}

}
