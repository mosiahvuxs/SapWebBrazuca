package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
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
	
	@SuppressWarnings("unchecked")
	public List<PedidoVenda> pesquisarInterface(PedidoVenda model, String jndi) {
		
        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, VALOR, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, ID_EXTERNO, EMPRESA_ID, CLIENTE_TIPO, CLIENTE_ID, VENDEDOR_ID, ENDERECO_ENTREGA, ENDERECO_COBRANCA, CLIENTE_IDENTIFICADOR_FEDERAL, OBSERVACAO, TIPO_RESUMO, TIPO, TIPO_ENVIO FROM PEDIDOVENDA WHERE STATUS_ID !=2 AND EMPRESA_ID = ? ");
        
		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			sql.append(" AND SEM_ACENTOS(CLIENTE_NOME) ILIKE SEM_ACENTOS(?)");
			
		}        
        
		if (!TSUtil.isEmpty(model.getVendedor()) && !TSUtil.isEmpty(model.getVendedor().getNome())) {

			sql.append(" AND SEM_ACENTOS(VENDEDOR_NOME) ILIKE SEM_ACENTOS(?)");
			
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

		broker.setSQL(sql.toString(), model.getEmpresa().getId());

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getNome())) {

			broker.set("%" + model.getCliente().getNome() + "%");
			
		}
		
		if (!TSUtil.isEmpty(model.getVendedor()) && !TSUtil.isEmpty(model.getVendedor().getNome())) {

			broker.set("%" + model.getVendedor().getNome() + "%");
			
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
		
        return broker.getCollectionBean(PedidoVenda.class, "interfaceId", "dataLancamento", "dataDocumento", "dataVencimento", 
        													   "condicaoPagamento.id", "valor","dataExportacao", 
        													   "dataImportacao", "dataAtualizacao", "sequencia.id", "status.id",
        													   "mensagemErro", "cliente.identificadorFiscal.tipoIdentificador",
        													   "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia",
        													   "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email",
        													   "cliente.observacao", "cliente.endereco.logradouro","cliente.endereco.numero",
        													   "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade",
        													   "cliente.endereco.pais.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id",
        													   "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual",
        													   "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal",
        													   "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id", "vendedor.tipoIdentificador",
        													   "vendedor.identificador", "vendedor.nome", "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "idExterno", "empresa.id", "cliente.tipo", "cliente.id",
        													   "vendedor.id", "enderecoEntregaFormatado", "enderecoCobrancaFormatado", "cliente.identificadorFederal", "observacao", "tipoResumo", "tipo", "tipoEnvio");
	}

	public void alterarInterface(PedidoVenda model, String jndi) throws TSApplicationException{

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.setPropertySQL("pedidovendadaoI.alterarInterface", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());
		
		broker.execute();
		
	}

	public PedidoVenda inserirInterface(PedidoVenda model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("pedidovenda_id_seq"));
		
		broker.setPropertySQL("pedidovendadaoI.inserirInterface", model.getInterfaceId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(),
																	 model.getCondicaoPagamento().getId(), model.getValor(), model.getDataExportacao(), model.getDataImportacao(),
																	 model.getDataAtualizacao(), model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(),
																	 model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(),
																	 model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(),
																	 model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(),
																	 model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(),
																	 model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getPais().getEstado().getId(), model.getCliente().getEndereco().getCep(),
																	 model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(),
																	 model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(),
																	 model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(),
																	 model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getTipoIdentificador(),
																	 model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(),
																	 model.getIdExterno(), model.getEmpresa().getId(), model.getCliente().getTipo(), model.getCliente().getId(), model.getVendedor().getId(), model.getEnderecoEntregaFormatado(),
																	 model.getEnderecoCobrancaFormatado(),model.getCliente().getIdentificadorFederal(), model.getObservacao(), model.getTipoResumo(), model.getTipo(), model.getTipoEnvio());
		
		broker.execute();
		
		for (PedidoVendaLinha linha : model.getLinhas()) {
			
			linha.setPedidoVenda(new PedidoVenda());
			
			linha.getPedidoVenda().setInterfaceId(model.getInterfaceId());
			
			new PedidoVendaLinhaDAO().inserirComBroker(linha, broker);
			
		}
		
		broker.endTransaction();
		
		return model;

	}

	public void excluirInterface(PedidoVenda model, String jndi) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.setPropertySQL("pedidovendadaoI.excluirInterface", model.getInterfaceId());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<PedidoVenda> pesquisarPorAtrasadaInterface(PedidoVenda model, String jndi) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
        
        broker.setPropertySQL("pedidovendadaoI.pesquisarPorAtrasadaInterface", model.getStatus().getId());
        
        return broker.getCollectionBean(PedidoVenda.class, "interfaceId");
	}

	
	public PedidoVenda obterIdExternoInterface(PedidoVenda model, String jndi) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(jndi);
		
		broker.setPropertySQL("pedidovendadaoI.obterIdExternoInterface", model.getIdExterno(), model.getEmpresa().getId());
		
		return (PedidoVenda) broker.getObjectBean(PedidoVenda.class, "interfaceId");
	}	
	

}