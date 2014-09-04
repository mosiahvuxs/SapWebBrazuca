package br.com.sapweb.brazuca.faces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sapweb.brazuca.dao.GrupoDAO;
import br.com.sapweb.brazuca.dao.MenuDAO;
import br.com.sapweb.brazuca.dao.PermissaoDAO;
import br.com.sapweb.brazuca.model.Grupo;
import br.com.sapweb.brazuca.model.Menu;
import br.com.sapweb.brazuca.model.Permissao;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.exception.TSSystemException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "grupoFaces")
public class GrupoFaces extends TSMainFaces {

	private Grupo crudModel;
	private Grupo crudPesquisaModel;
	private List<SelectItem> comboMenus;
	private Permissao permissaoSelecionada;
	private List<Grupo> grid;
	private Integer tabIndex;
	private boolean flagAlterar;

	public GrupoFaces() {

		this.limpar();

		this.limparPesquisa();

		this.initCombo();
	}

	private void initCombo() {

		this.comboMenus = super.initCombo(new MenuDAO().pesquisarExecutaveis(), "id", "descricao");
	}

	@Override
	protected void clearFields() {

		this.tabIndex = 0;

	}

	public String limpar() {

		this.flagAlterar = false;

		try {

			this.crudModel = new Grupo();

			this.clearFields();

		} catch (Exception e) {

			throw new TSSystemException(e);

		}

		return null;

	}

	public String limparPesquisa() {

		this.grid = Collections.emptyList();

		try {

			this.setGrid(new ArrayList<Grupo>());

			this.setCrudPesquisaModel(new Grupo());

		} catch (Exception e) {

			throw new TSSystemException(e);

		}

		return null;
	}

	public String addPermissao() {

		Permissao permissao = new Permissao();

		permissao.setGrupo(getCrudModel());
		permissao.setMenu(new Menu());
		permissao.setFlagInserir(Boolean.TRUE);
		permissao.setFlagAlterar(Boolean.TRUE);
		permissao.setFlagExcluir(Boolean.FALSE);

		if (TSUtil.isEmpty(getCrudModel().getPermissoes())) {
			getCrudModel().setPermissoes(new ArrayList<Permissao>());
		}

		if (!this.getCrudModel().getPermissoes().contains(permissao)) {

			this.getCrudModel().getPermissoes().add(permissao);

		} else {

			super.addErrorMessage("Essa permissão já foi adicionada");
		}

		return null;
	}

	public String delPermissao() {
		getCrudModel().getPermissoes().remove(this.permissaoSelecionada);
		
		return null;
	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		new GrupoDAO().inserir(this.crudModel);

		this.limpar();

		this.setDefaultMessage(Boolean.TRUE);

		return null;
	}

	@Override
	protected String find() {

		this.tabIndex = 1;

		this.grid = new GrupoDAO().pesquisar(this.crudPesquisaModel);

		TSFacesUtil.gerarResultadoLista(this.grid);

		return null;
	}

	@Override
	protected String detail() {

		this.crudModel = new GrupoDAO().obter(this.crudModel);

		this.crudModel.setPermissoes(new PermissaoDAO().pesquisar(this.crudModel));

		this.tabIndex = 0;

		this.flagAlterar = true;

		return null;
	}

	@Override
	protected String update() throws TSApplicationException {
		
		super.setClearFields(false);

		super.setDefaultMessage(false);

		new GrupoDAO().alterar(this.crudModel);

		this.limpar();

		this.setDefaultMessage(Boolean.TRUE);

		return null;
	}

	public List<SelectItem> getComboMenus() {
		return comboMenus;
	}

	public void setComboMenus(List<SelectItem> comboMenus) {
		this.comboMenus = comboMenus;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	public Grupo getCrudModel() {
		return crudModel;
	}

	public void setCrudModel(Grupo crudModel) {
		this.crudModel = crudModel;
	}

	public Grupo getCrudPesquisaModel() {
		return crudPesquisaModel;
	}

	public void setCrudPesquisaModel(Grupo crudPesquisaModel) {
		this.crudPesquisaModel = crudPesquisaModel;
	}

	public List<Grupo> getGrid() {
		return grid;
	} 

	public void setGrid(List<Grupo> grid) {
		this.grid = grid;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public boolean isFlagAlterar() {
		return flagAlterar;
	}

	public void setFlagAlterar(boolean flagAlterar) {
		this.flagAlterar = flagAlterar;
	}

}
