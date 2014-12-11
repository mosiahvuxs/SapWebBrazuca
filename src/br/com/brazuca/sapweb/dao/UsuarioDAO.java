package br.com.brazuca.sapweb.dao;

import java.util.List;

import br.com.brazuca.sapweb.model.Usuario;
import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class UsuarioDAO {

	public Usuario obter(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obter", model.getId());

		return (Usuario) broker.getObjectBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha", "grupoWeb.id", "grupoAndroid.id", "grupoIphone.id");
	}

	public Usuario obterPorLoginSenha(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterPorLoginSenha", model.getLogin(), model.getSenha());

		return (Usuario) broker.getObjectBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha", "grupoWeb.id","grupoAndroid.id", "grupoIphone.id");
	}

	public void excluir(Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.alterar", model.getEmail(), model.getFlagAtivo(),

		model.getLogin(), model.getNome(), model.getSenha(), model.getGrupoWeb().getId(), model.getGrupoAndroid().getId(), model.getGrupoIphone().getId(),model.getId());

		broker.execute();

	}

	public Usuario inserir(Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("usuarios_id_seq"));

		broker.setPropertySQL("usuariodao.inserir", model.getId(), model.getEmail(), model.getFlagAtivo(),

		model.getLogin(), model.getNome(), model.getSenha(), model.getGrupoWeb().getId(), model.getGrupoAndroid().getId(), model.getGrupoIphone().getId());

		broker.execute();

		return model;

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisar(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT U.ID, U.EMAIL, U.FLAG_ATIVO, U.LOGIN, U.NOME, U.SENHA, U.GRUPO_WEB_ID,U.GRUPO_ANDROID_ID, U.GRUPO_IPHONE_ID, G.DESCRICAO FROM USUARIOS U, GRUPOS G WHERE U.GRUPO_WEB_ID = G.ID");

		if (!TSUtil.isEmpty(model.getLogin())) {

			sql.append(" AND SEM_ACENTOS(U.LOGIN) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getEmail())) {

			sql.append(" AND SEM_ACENTOS(U.EMAIL) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getNome())) {

			sql.append(" AND SEM_ACENTOS(U.NOME) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getGrupoWeb()) && !TSUtil.isEmpty(model.getGrupoWeb().getId())) {

			sql.append(" AND U.GRUPO_WEB_ID = ?");
		}
		
		if (!TSUtil.isEmpty(model.getFlagAndroid())) {

			sql.append(" AND U.GRUPO_ANDROID_ID IS NOT NULL ");
		}	
		
		if (!TSUtil.isEmpty(model.getFlagWeb())) {

			sql.append(" AND U.GRUPO_WEB_ID IS NOT NULL ");
		}
		
		if (!TSUtil.isEmpty(model.getFlagIphone())) {

			sql.append(" AND U.GRUPO_IPHONE_ID IS NOT NULL ");
		}

		sql.append(" AND U.FLAG_ATIVO = ?");

		sql.append(" ORDER BY U.NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getLogin())) {

			broker.set("%" + model.getLogin() + "%");

		}

		if (!TSUtil.isEmpty(model.getEmail())) {

			broker.set("%" + model.getEmail() + "%");

		}

		if (!TSUtil.isEmpty(model.getNome())) {

			broker.set("%" + model.getNome() + "%");

		}

		if (!TSUtil.isEmpty(model.getGrupoWeb()) && !TSUtil.isEmpty(model.getGrupoWeb().getId())) {

			broker.set(model.getGrupoWeb().getId());

		}
		
		if (!TSUtil.isEmpty(model.getGrupoAndroid()) && !TSUtil.isEmpty(model.getGrupoAndroid().getTipoGrupo()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getGrupoAndroid().getTipoGrupo().getId()))) {

			broker.set(model.getGrupoAndroid().getTipoGrupo().getId());
			
		}			

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha", "grupoWeb.id", "grupoAndroid.id", "grupoIphone.id", "grupoWeb.descricao");
	}

}
