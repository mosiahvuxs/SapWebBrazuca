/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioDAO {
	
	@SuppressWarnings("unchecked")
	public List<ParceiroNegocio> pesquisar(ParceiroNegocio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("parceironegociosapdaoI.pesquisar", model.getEmpresa().getId());
		
		return broker.getCollectionBean(ParceiroNegocio.class, "id", "nome", "nomeFantasia", "telefoneResidencial", "telefoneCelular", "email", "endereco.id",
				                                               "endereco.logradouro", "endereco.numero", "endereco.bairro", "endereco.cidade", "endereco.estado.id", 
				                                               "endereco.estado.descricao","endereco.pais.id", "endereco.pais.descricao","endereco.complemento", "identificadorFederal", "empresa.id", "condicaoPagamento.id");
		
	}

	public ParceiroNegocio inserir(ParceiroNegocio model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("parceironegociosapdaoI.inserir", model.getId(), model.getNome(), model.getNomeFantasia(), model.getTelefoneResidencial(), model.getTelefoneCelular(), model.getEmail(),
																model.getEndereco().getId(), model.getEndereco().getLogradouro(), model.getEndereco().getNumero(), model.getEndereco().getBairro(), 
																model.getEndereco().getCidade(), model.getEndereco().getEstado().getId(), model.getEndereco().getEstado().getDescricao(),
																model.getEndereco().getPais().getId(), model.getEndereco().getPais().getDescricao(), model.getEndereco().getComplemento(),
																model.getIdentificadorFederal(), model.getEmpresa().getId(), model.getCondicaoPagamento().getId());
		
		broker.execute();

		return model;
		
	}

	public void excluir(ParceiroNegocio model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("parceironegociosapdaoI.excluir", model.getEmpresa().getId());
		
		broker.execute();
		
	}

}
