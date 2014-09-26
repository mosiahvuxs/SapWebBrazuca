package br.com.brazuca.sapweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.brazuca.sapweb.dao.HistoricoNotaFiscalSaidaLinhaDAO;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaida;
import br.com.brazuca.sapweb.model.HistoricoNotaFiscalSaidaLinha;
import br.com.brazuca.sapweb.report.JasperUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@ViewScoped
@ManagedBean(name = "relatorioEnvioNotaFiscalFaces")
public class RelatorioEnvioNotaFiscalFaces {

	private HistoricoNotaFiscalSaida historicoNotaFiscalSaida;
	private List<HistoricoNotaFiscalSaidaLinha> historicosLinhas;

	public RelatorioEnvioNotaFiscalFaces() {

		this.limpar();
	}

	public void limpar() {

		this.historicoNotaFiscalSaida = new HistoricoNotaFiscalSaida();
		this.historicoNotaFiscalSaida.setDataInicial(new Date());
		this.historicoNotaFiscalSaida.setDataFinal(new Date());

		this.historicosLinhas = new ArrayList<HistoricoNotaFiscalSaidaLinha>();
	}

	public String gerarRelatorio() {

		this.historicosLinhas = new HistoricoNotaFiscalSaidaLinhaDAO().pesquisar(this.historicoNotaFiscalSaida);

		if (!TSUtil.isEmpty(this.historicosLinhas)) {

			try {
				
				HashMap<String, Object> map = new HashMap<String, Object>();

				new JasperUtil().gerarRelatorio("envioPedidoVendaSap.jasper", map, new JRBeanCollectionDataSource(this.historicosLinhas));

			} catch (JRException e) {

				e.printStackTrace();
			}

		} else {

			TSFacesUtil.addInfoMessage("Não foi possível gerar o relatório com os filtros informados.");
		}

		return null;
	}

	public HistoricoNotaFiscalSaida getHistoricoNotaFiscalSaida() {
		return historicoNotaFiscalSaida;
	}

	public void setHistoricoNotaFiscalSaida(HistoricoNotaFiscalSaida historicoNotaFiscalSaida) {
		this.historicoNotaFiscalSaida = historicoNotaFiscalSaida;
	}

	public List<HistoricoNotaFiscalSaidaLinha> getHistoricosLinhas() {
		return historicosLinhas;
	}

	public void setHistoricosLinhas(List<HistoricoNotaFiscalSaidaLinha> historicosLinhas) {
		this.historicosLinhas = historicosLinhas;
	}
}
