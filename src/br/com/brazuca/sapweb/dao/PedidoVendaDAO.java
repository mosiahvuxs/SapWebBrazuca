package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

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

}
