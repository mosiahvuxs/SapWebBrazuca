package br.com.sapweb.brazuca.dao;

import java.util.List;

import br.com.sapweb.brazuca.model.Grupo;
import br.com.sapweb.brazuca.model.Menu;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class MenuDAO {

	@SuppressWarnings("unchecked")
	public List<Menu> pesquisarCabecalhos(Grupo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO, FLAG_ATIVO, MANAGED_BEAN_RESET, ORDEM, URL, MENU_ID FROM MENUS M WHERE MENU_ID IS NULL AND FLAG_ATIVO = TRUE AND EXISTS (SELECT 1 FROM MENUS M2, PERMISSOES P WHERE M2.MENU_ID = M.ID AND M2.ID = P.MENU_ID AND P.GRUPO_ID = ?) ORDER BY M.ORDEM, M.DESCRICAO");

		broker.setSQL(sql.toString());

		broker.set(model.getId());

		return broker.getCollectionBean(Menu.class, "id", "descricao", "flagAtivo", "managedBeanReset", "ordem", "url", "menuPai.id");
	}

	@SuppressWarnings("unchecked")
	public List<Menu> pesquisarExecutaveis() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO, FLAG_ATIVO, MANAGED_BEAN_RESET, ORDEM, URL, MENU_ID FROM MENUS M WHERE FLAG_ATIVO = TRUE AND M.MENU_ID IS NOT NULL ORDER BY M.ORDEM, M.DESCRICAO");

		broker.setSQL(sql.toString());

		return broker.getCollectionBean(Menu.class, "id", "descricao", "flagAtivo", "managedBeanReset", "ordem", "url", "menuPai.id");
	}

}
