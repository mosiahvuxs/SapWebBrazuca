package br.com.sapweb.brazuca.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.sapweb.brazuca.dao.EmpresaDAO;
import br.com.sapweb.brazuca.dao.MenuDAO;
import br.com.sapweb.brazuca.dao.PermissaoDAO;
import br.com.sapweb.brazuca.model.Empresa;
import br.com.sapweb.brazuca.model.Menu;
import br.com.sapweb.brazuca.model.Permissao;
import br.com.sapweb.brazuca.model.Usuario;
import br.com.sapweb.brazuca.util.Constantes;
import br.com.sapweb.brazuca.util.UsuarioUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name = "autenticacaoFaces")
public class AutenticacaoFaces extends TSMainFaces {

	private String nomeTela;
	private String tela;
	private Usuario usuario;
	private List<Menu> menus;
	private List<Permissao> permissoes;
	private Permissao PermissaoSelecionada;
	private Integer tabAtiva;
	private Empresa empresa;
	private List<SelectItem> empresas;

	public AutenticacaoFaces() {

		this.clearFields();
		this.setNomeTela("Área de Trabalho");

	}

	protected void clearFields() {
		
		this.tabAtiva = 0;
		
		this.usuario = new Usuario();
		this.empresa = new Empresa();
		this.PermissaoSelecionada = new Permissao();
		
		this.menus = new ArrayList<Menu>();
		
		this.empresas = this.initCombo(new EmpresaDAO().pesquisar(new Empresa(Boolean.TRUE)), "id", "descricao");

		if (!TSUtil.isEmpty(this.empresas) && this.empresas.size() == 1) {

			this.empresa.setId(new Long(this.empresas.get(0).getValue().toString()));
		}
		
		

	}

	public String redirecionar() {

		if (!TSUtil.isEmpty(this.PermissaoSelecionada.getMenu().getManagedBeanReset())) {
			TSFacesUtil.removeManagedBeanInSession(this.PermissaoSelecionada.getMenu().getManagedBeanReset());
		}

		this.setTela(this.PermissaoSelecionada.getMenu().getUrl());
		this.setNomeTela("Area de Trabalho > " + PermissaoSelecionada.getMenu().getMenuPai().getDescricao() + " > " + PermissaoSelecionada.getMenu().getDescricao());
		this.setTabAtiva(Integer.valueOf(this.menus.indexOf(this.PermissaoSelecionada.getMenu().getMenuPai())));

		return SUCESSO;
	}

	private void carregarMenu() {

		menus = new MenuDAO().pesquisarCabecalhos(UsuarioUtil.obterUsuarioConectado().getGrupo());

		permissoes = new PermissaoDAO().pesquisar(UsuarioUtil.obterUsuarioConectado().getGrupo());

	}

	public String login() {

		usuario = UsuarioUtil.usuarioAutenticado(usuario);

		if (TSUtil.isEmpty(usuario)) {

			this.clearFields();

			super.addErrorMessage("Login/Senha sem credencial para acesso.");

			return null;
		}

		TSFacesUtil.addObjectInSession(Constantes.USUARIO_CONECTADO, usuario);

		this.empresa = new EmpresaDAO().obter(this.empresa);

		TSFacesUtil.addObjectInSession(Constantes.EMPRESA, this.empresa);

		carregarMenu();

		return "entrar";
	}

	public String logout() {

		TSFacesUtil.removeObjectInSession(Constantes.USUARIO_CONECTADO);
		TSFacesUtil.removeObjectInSession(Constantes.EMPRESA);

		TSFacesUtil.getRequest().getSession().invalidate();

		return "sair";
	}

	public String getNomeTela() {
		return nomeTela;
	}

	public void setNomeTela(String nomeTela) {
		this.nomeTela = nomeTela;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Integer getTabAtiva() {
		return tabAtiva;
	}

	public void setTabAtiva(Integer tabAtiva) {
		this.tabAtiva = tabAtiva;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public Permissao getPermissaoSelecionada() {
		return PermissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		PermissaoSelecionada = permissaoSelecionada;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<SelectItem> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<SelectItem> empresas) {
		this.empresas = empresas;
	}

}
