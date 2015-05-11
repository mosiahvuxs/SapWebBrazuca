package br.com.brazuca.sapweb.dao;

import br.com.brazuca.sapweb.model.HistoricoPedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoPedidoVendaDAO {

	public HistoricoPedidoVenda inserirInterface(HistoricoPedidoVenda model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_pedidovenda_id_seq"));
		
		broker.setPropertySQL("historicopedidovendadao.inserir", model.getInterfaceId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(),
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
				 model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getTipo(), model.getPercentualDesconto(),  model.getEnderecoEntregaFormatado(),model.getEnderecoCobrancaFormatado());
		
		broker.execute();
		
		for (PedidoVendaLinha linha : model.getLinhas()) {
			
			linha.setPedidoVenda(new PedidoVenda());
			
			linha.getPedidoVenda().setInterfaceId(model.getInterfaceId());
			
			new HistoricoPedidoVendaLinhaDAO().inserirComBroker(linha, broker);
			
		}
		
		broker.endTransaction();
		
		return model;
		
	}

}
