package br.com.brazuca.sapweb.dao;

import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoNotaFiscalSaidaDAO {

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
