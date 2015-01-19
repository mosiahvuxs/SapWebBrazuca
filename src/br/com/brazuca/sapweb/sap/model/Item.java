package br.com.brazuca.sapweb.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.brazuca.sapweb.model.Empresa;

@SuppressWarnings("serial")
@XmlRootElement
public class Item implements Serializable {

    private String id;
    private String descricao;
    private Origem origem;
    private String detalhe;
    private Empresa empresa;
    private Estoque estoque;
    private List<Estoque> estoques; 
    private BigDecimal preco;
    private EstoqueItem estoqueItem;
    private String catalogo;
    
    public Item(){
    	
    }
    
    public Item(Empresa empresa, Estoque estoque){
    	
    	this.empresa = empresa;
    	
    	this.estoque = estoque;
    	
    }

    public Item(Empresa empresa) {
    	this.empresa = empresa;
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public EstoqueItem getEstoqueItem() {
		return estoqueItem;
	}

	public void setEstoqueItem(EstoqueItem estoqueItem) {
		this.estoqueItem = estoqueItem;
	}

	public String getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}
    
    
}
