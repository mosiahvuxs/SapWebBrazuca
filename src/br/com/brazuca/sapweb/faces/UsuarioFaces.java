package br.com.brazuca.sapweb.faces;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.brazuca.sapweb.dao.GrupoDAO;
import br.com.brazuca.sapweb.dao.UsuarioDAO;
import br.com.brazuca.sapweb.model.Grupo;
import br.com.brazuca.sapweb.model.Usuario;
import br.com.brazuca.sapweb.util.UsuarioUtil;
import br.com.brazuca.sapweb.util.Utilitarios;
import br.com.topsys.constant.TSConstant;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "usuarioFaces")
public class UsuarioFaces extends TSMainFaces {

	private Usuario crudModel;
	private Usuario crudPesquisaModel;
	private List<SelectItem> grupos;
	private List<Usuario> grid;
	private Integer tabIndex;
	private boolean flagAlterar;
	private String senha;

	public UsuarioFaces() {

		this.init();
	}

	public void init() {

		this.limpar();
		this.limparPesquisa();
		this.initCombo();

	}

	@Override
	protected void clearFields() {

		this.tabIndex = 0;

	}

	public String limpar() {

		this.flagAlterar = false;

		this.crudModel = new Usuario();

		this.getCrudModel().setGrupo(new Grupo());

		this.getCrudModel().setFlagAtivo(Boolean.TRUE);

		this.clearFields();

		return null;
	}

	public String limparPesquisa() {

		this.grid = Collections.emptyList();

		this.crudPesquisaModel = new Usuario();

		this.setCrudPesquisaModel(new Usuario());

		this.getCrudPesquisaModel().setGrupo(new Grupo());

		this.getCrudPesquisaModel().setFlagAtivo(Boolean.TRUE);

		return null;
	}

	private void initCombo() {

		this.grupos = super.initCombo(new GrupoDAO().pesquisar(new Grupo()), "id", "descricao");
	}

	private boolean validaSenhas() {

		if (!this.crudModel.getSenha().equals(this.crudModel.getConfirmaSenha())) {

			super.addErrorMessage("Senhas não conferem.");

			return false;
		}

		return true;
	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		if (!this.validaSenhas()) {

			return null;

		}

		this.crudModel.setSenha(TSCryptoUtil.gerarHash(this.crudModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

		new UsuarioDAO().inserir(this.getCrudModel());

		this.limpar();

		this.setDefaultMessage(Boolean.TRUE);

		return null;
	}

	@Override
	protected String find() {

		this.tabIndex = 1;

		this.grid = new UsuarioDAO().pesquisar(this.getCrudPesquisaModel());

		TSFacesUtil.gerarResultadoLista(this.grid);

		return null;
	}

	@Override
	protected String detail() {

		this.crudModel = new UsuarioDAO().obter(this.crudModel);

		this.setSenha(this.crudModel.getSenha());

		this.tabIndex = 0;

		this.flagAlterar = true;

		return null;

	}

	@Override
	protected String update() throws TSApplicationException {

		super.setClearFields(false);

		super.setDefaultMessage(false);

		if (TSUtil.isEmpty(Utilitarios.tratarString(this.getCrudModel().getSenha()))) {

			this.getCrudModel().setSenha(this.senha);

		} else {

			this.getCrudModel().setSenha(UsuarioUtil.getSenhaCriptografada(this.getCrudModel().getSenha()));

		}

		new UsuarioDAO().alterar(this.crudModel);

		this.limpar();

		this.setDefaultMessage(Boolean.TRUE);

		return null;
	}

	@Override
	protected String delete() throws TSApplicationException {

		new UsuarioDAO().excluir(this.crudModel);

		this.grid = new UsuarioDAO().pesquisar(this.crudPesquisaModel);

		this.tabIndex = 1;

		return null;

	}

	public Usuario getCrudModel() {
		return crudModel;
	}

	public void setCrudModel(Usuario crudModel) {
		this.crudModel = crudModel;
	}

	public Usuario getCrudPesquisaModel() {
		return crudPesquisaModel;
	}

	public void setCrudPesquisaModel(Usuario crudPesquisaModel) {
		this.crudPesquisaModel = crudPesquisaModel;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<SelectItem> grupos) {
		this.grupos = grupos;
	}

	public List<Usuario> getGrid() {
		return grid;
	}

	public void setGrid(List<Usuario> grid) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
