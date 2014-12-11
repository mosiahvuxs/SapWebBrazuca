package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.Grupo;
import br.com.brazuca.sapweb.model.Menu;
import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;

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
	public List<Menu> pesquisarExecutaveis(Menu model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO, FLAG_ATIVO, MANAGED_BEAN_RESET, ORDEM, URL, MENU_ID FROM MENUS M WHERE FLAG_ATIVO = TRUE AND M.MENU_ID IS NOT NULL ");
		
		if(!TSUtil.isEmpty(model.getMenuPai()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getMenuPai().getId()))){
			
			sql.append(" AND MENU_ID = ? ");
		}
		
		sql.append(" ORDER BY M.ORDEM, M.DESCRICAO ");
		
		broker.setSQL(sql.toString());
		
		if(!TSUtil.isEmpty(model.getMenuPai()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getMenuPai().getId()))){
			
			broker.set(model.getMenuPai().getId());
			
		}
		
		return broker.getCollectionBean(Menu.class, "id", "descricao", "flagAtivo", "managedBeanReset", "ordem", "url", "menuPai.id");
	}

}
