package br.com.brazuca.sapweb.validation;

import br.com.brazuca.sapweb.model.Usuario;
import br.com.topsys.util.TSUtil;

public class UsuarioValidation {

	public void validarPesquisa(Usuario model) {

		if (TSUtil.isEmpty(model.getFlagWeb()) && TSUtil.isEmpty(model.getFlagAndroid()) && TSUtil.isEmpty(model.getFlagIphone())) {

			model.setMensagemErro("Favor setar um usuario.flagAndroid | usuario.flagWeb | usuario.flagIphone = true");

		}
	
	}

}
