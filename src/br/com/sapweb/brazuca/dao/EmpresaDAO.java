package br.com.sapweb.brazuca.dao;

import java.util.List;

import br.com.sapweb.brazuca.model.Empresa;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class EmpresaDAO {

	@SuppressWarnings("unchecked")
	public List<Empresa> pesquisar(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO FROM EMPRESAS WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(DESCRICAO) ILIKE ?");
		}

		sql.append(" AND FLAG_ATIVO = ?");

		sql.append(" ORDER BY DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Empresa.class, "id", "descricao");
	}

	public Empresa obter(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("empresadao.obter", model.getId());

		return (Empresa) broker.getObjectBean(Empresa.class, "id", "descricao", "flagAtivo", "jndi");
	}

	public void excluir(Empresa model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("empresadao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Empresa model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("empresadao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public Empresa inserir(Empresa model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("empresas_id_seq"));

		broker.setPropertySQL("empresadao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}

}
