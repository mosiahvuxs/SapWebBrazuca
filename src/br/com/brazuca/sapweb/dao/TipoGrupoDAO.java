package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.TipoGrupo;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class TipoGrupoDAO {

	@SuppressWarnings("unchecked")
	public List<TipoGrupo> pesquisar(TipoGrupo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("tipogrupodao.pesquisar");

		return broker.getCollectionBean(TipoGrupo.class, "id", "descricao");
	}

}
