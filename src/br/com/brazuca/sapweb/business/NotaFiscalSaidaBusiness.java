package br.com.brazuca.sapweb.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.brazuca.sapweb.sap.model.CFOP;
import br.com.brazuca.sapweb.sap.model.CST;
import br.com.brazuca.sapweb.sap.model.Classificacao;
import br.com.brazuca.sapweb.sap.model.ContaContabil;
import br.com.brazuca.sapweb.sap.model.Estado;
import br.com.brazuca.sapweb.sap.model.GrupoComissao;
import br.com.brazuca.sapweb.sap.model.IdentificadorFiscal;
import br.com.brazuca.sapweb.sap.model.Municipio;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaida;
import br.com.brazuca.sapweb.sap.model.NotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.sap.model.Pais;
import br.com.brazuca.sapweb.sap.model.ParceiroNegocioEndereco;
import br.com.brazuca.sapweb.sap.model.PedidoVenda;
import br.com.brazuca.sapweb.sap.model.PedidoVendaLinha;
import br.com.brazuca.sapweb.sap.model.Sequencia;
import br.com.brazuca.sapweb.sap.model.Status;
import br.com.brazuca.sapweb.sap.model.Utilizacao;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;

public class NotaFiscalSaidaBusiness {

	public NotaFiscalSaida inserir(PedidoVenda model, List<PedidoVendaLinha> linhas) throws TSApplicationException {

		NotaFiscalSaida notaFiscal = new NotaFiscalSaida();

		notaFiscal.setPedidoVenda(new PedidoVenda(model.getId()));
		notaFiscal.setDataLancamento(model.getDataLancamento());
		notaFiscal.setDataDocumento(model.getDataDocumento());
		notaFiscal.setDataVencimento(model.getDataVencimento());
		notaFiscal.setDataExportacao(new Timestamp(System.currentTimeMillis()));
		notaFiscal.setDataCriacao(new Timestamp(System.currentTimeMillis()));
		notaFiscal.setCondicaoPagamento(model.getCondicaoPagamento());
		notaFiscal.setValor(model.getValor());
		notaFiscal.setCliente(model.getCliente());
		notaFiscal.getCliente().setIdentificadorFiscal(new IdentificadorFiscal());
		notaFiscal.getCliente().setEndereco(new ParceiroNegocioEndereco());
		notaFiscal.getCliente().getEndereco().setEstado(new Estado());
		notaFiscal.getCliente().getEndereco().setPais(new Pais());
		notaFiscal.getCliente().getEndereco().setMunicipio(new Municipio());
		notaFiscal.getCliente().setClassificacao(new Classificacao());
		notaFiscal.setVendedor(model.getVendedor());
		notaFiscal.getVendedor().setGrupoComissao(new GrupoComissao());
		notaFiscal.setEmpresa(model.getEmpresa());
		notaFiscal.setObservacao(model.getObservacao());
		notaFiscal.setTipoResumo(model.getTipoResumo());
		notaFiscal.setTipo(model.getTipo());
		notaFiscal.setTipoEnvio(model.getTipoEnvio());
		notaFiscal.setStatus(new Status(Constantes.ID_STATUS_NAO_PROCESSADO));
		notaFiscal.setEnderecoCobrancaFormatado(model.getEnderecoCobrancaFormatado());
		notaFiscal.setEnderecoEntregaFormatado(model.getEnderecoEntregaFormatado());
		notaFiscal.setSequencia(new Sequencia());
		
		notaFiscal.setFlagValidar(Boolean.FALSE);
		
		notaFiscal.setLinhas(new ArrayList<NotaFiscalSaidaLinha>());

		for (PedidoVendaLinha linha : linhas) {

			NotaFiscalSaidaLinha saidaLinha = new NotaFiscalSaidaLinha();

			saidaLinha.setItem(linha.getItem());
			saidaLinha.setQuantidade(linha.getQuantidadeLiberada().doubleValue());
			saidaLinha.setValorUnitario(linha.getValorUnitario());
			saidaLinha.setValor(linha.getValor());
			saidaLinha.setCodigoImposto(linha.getCodigoImposto());
			saidaLinha.setCodigoBarras(linha.getCodigoBarras());
			saidaLinha.setPedidoVendaLinha(new PedidoVendaLinha());
			
			saidaLinha.getPedidoVendaLinha().setNumero(linha.getNumero());
			
			saidaLinha.getPedidoVendaLinha().setPedidoVenda(new PedidoVenda(model.getId()));
			
			saidaLinha.setCfop(new CFOP());
			
			saidaLinha.setCstCOFINS(new CST());
			
			saidaLinha.setCstICMS(new CST());
			
			saidaLinha.setCstIPI(new CST());
			
			saidaLinha.setCstPIS(new CST());
			
			saidaLinha.setUtilizacao(new Utilizacao());
			
			saidaLinha.setContaContabil(new ContaContabil());

			notaFiscal.getLinhas().add(saidaLinha);

		}
	
		return notaFiscal;
	}
}
