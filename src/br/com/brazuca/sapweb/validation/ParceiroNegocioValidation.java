package br.com.brazuca.sapweb.validation;

import br.com.brazuca.sapweb.dao.EmpresaDAO;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocio;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.util.TSUtil;

public class ParceiroNegocioValidation {

	public void validarPesquisa(ParceiroNegocio model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO + "\n");

		} else {
			
			if(TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))){
				
				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO);
				
			}else{
				
				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
				
			}
			
			if(TSUtil.isEmpty(model.getTipo())){
				
				retorno.append(Constantes.OBJETO_PARCEIRO_NEGOCIO_TIPO);
				
			}
			
		}
		
		if(!TSUtil.isEmpty(retorno.toString())){
			
			model.setMensagemErro(retorno.toString());
			
		}
				
	}
		
}
