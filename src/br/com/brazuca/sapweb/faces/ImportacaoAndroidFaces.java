package br.com.brazuca.sapweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brazuca.sapweb.dao.CondicaoPagamentoDAO;
import br.com.brazuca.sapweb.dao.EstoqueDAO;
import br.com.brazuca.sapweb.dao.ItemDAO;
import br.com.brazuca.sapweb.dao.ParceiroNegocioDAO;
import br.com.brazuca.sapweb.dao.VendedorDAO;
import br.com.brazuca.sapweb.model.Empresa;
import br.com.brazuca.sapweb.restful.ClienteRestful;
import br.com.brazuca.sapweb.restful.CondicaoPagamentoRestful;
import br.com.brazuca.sapweb.restful.EstoqueRestful;
import br.com.brazuca.sapweb.restful.VendedorRestful;
import br.com.brazuca.sapweb.sap.model.CondicaoPagamento;
import br.com.brazuca.sapweb.sap.model.Estoque;
import br.com.brazuca.sapweb.sap.model.Item;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.sap.model.Vendedor;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "importacaoAndroidFaces")
public class ImportacaoAndroidFaces extends TSMainFaces {

	private Boolean flagCliente,flagEstoque,flagVendedor,flagCondicaoPagamento;
	private Empresa empresa;

	@PostConstruct
	public void init() {

		this.initObjetosNaSecao();
		
		this.clearFields();

	}	
	
	private void initObjetosNaSecao() {

		this.setEmpresa((Empresa) TSFacesUtil.getObjectInSession(Constantes.EMPRESA));

	}	

	@Override
	protected void clearFields() {

		this.flagCliente = Boolean.TRUE;
		
		this.flagEstoque = Boolean.TRUE;
		
		this.flagVendedor = Boolean.TRUE;
		
		this.flagCondicaoPagamento = Boolean.TRUE;
	
	}

	public void limpar() {

		this.clearFields();
	}

	@Override
	protected String insert() throws TSApplicationException {
			
		if(this.flagCliente){
			
			ParceiroNegocio p = new ParceiroNegocio(this.empresa);
			
			p.setTipo("C");
			
			new ParceiroNegocioDAO().excluir(p);
			
			for (ParceiroNegocio cliente : new ClienteRestful().pesquisar(p)) {
				
				cliente.setEmpresa(this.empresa);
				
				new ParceiroNegocioDAO().inserir(cliente);
				
			}
			
		}

		if(this.flagVendedor){
			
			new VendedorDAO().excluir(new Vendedor(this.empresa));
			
			for (Vendedor vendedor : new VendedorRestful().pesquisar(new Vendedor(this.empresa))) {
				
				vendedor.setEmpresa(this.empresa);
				
				new VendedorDAO().inserir(vendedor);
				
			}			
			
		}
		
		if(this.flagEstoque){
			
			new EstoqueDAO().excluir(new Estoque(this.empresa));
			
			new ItemDAO().excluir(new Item(this.empresa));
			
			for (Estoque estoque : new EstoqueRestful().pesquisar(new Estoque(this.empresa))) {
				
				estoque.setEmpresa(this.empresa);
				
				new EstoqueDAO().inserir(estoque);
				
				for (Item item : estoque.getItens()) {
					
					item.setEstoque(new Estoque(estoque.getId()));
					
					item.setEmpresa(this.empresa);
					
					new ItemDAO().inserir(item);
					
				}
				
			}
			
		}
		
		if(this.flagCondicaoPagamento){
			
			new CondicaoPagamentoDAO().excluir(new CondicaoPagamento(this.empresa));
			
			for (CondicaoPagamento item : new CondicaoPagamentoRestful().pesquisar(new CondicaoPagamento(this.empresa))) {
				
				item.setEmpresa(this.empresa);
				
				new CondicaoPagamentoDAO().inserir(item);
				
			}			
			
		}		
		
		return null;
		
	}

	public Boolean getFlagCliente() {
		return flagCliente;
	}

	public void setFlagCliente(Boolean flagCliente) {
		this.flagCliente = flagCliente;
	}

	public Boolean getFlagEstoque() {
		return flagEstoque;
	}

	public void setFlagEstoque(Boolean flagEstoque) {
		this.flagEstoque = flagEstoque;
	}

	public Boolean getFlagVendedor() {
		return flagVendedor;
	}

	public void setFlagVendedor(Boolean flagVendedor) {
		this.flagVendedor = flagVendedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getFlagCondicaoPagamento() {
		return flagCondicaoPagamento;
	}

	public void setFlagCondicaoPagamento(Boolean flagCondicaoPagamento) {
		this.flagCondicaoPagamento = flagCondicaoPagamento;
	}

	
}