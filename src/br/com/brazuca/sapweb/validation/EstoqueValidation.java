package br.com.brazuca.sapweb.validation;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.sap.model.Estoque;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.util.TSUtil;

public class EstoqueValidation {

	public void validarPesquisa(Estoque model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.ESTOQUE + "\n");

		} else {
			
			if(TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))){
				
				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO);
				
			}else{
				
				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
				
			}
			
		}
		
		if(!TSUtil.isEmpty(retorno.toString())){
			
			model.setMensagemErro(retorno.toString());
			
		}
		
	}

}
