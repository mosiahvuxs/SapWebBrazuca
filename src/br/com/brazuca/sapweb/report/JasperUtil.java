package br.com.brazuca.sapweb.report;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.brazuca.sapweb.util.Constantes;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

public class JasperUtil {

	public void gerarRelatorio(String jasper, Map<String, Object> parametros, JRBeanCollectionDataSource beans) throws JRException {

		jasper = TSFacesUtil.getServletContext().getRealPath("WEB-INF" + File.separator + "relatorios" + File.separator + jasper);

		parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

		JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, beans);

		if (!TSUtil.isEmpty(impressao) && !TSUtil.isEmpty(impressao.getPages()) && impressao.getPages().size() > 0) {

			ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			response.setContentType(Constantes.MIME_TYPE_PDF);

			response.setHeader("Content-Disposition", "attachment; filename=\"relatorioEnvioSap" + TSParseUtil.dateToString(new Date(), TSDateUtil.DD_MM_YYYY_HH_MM) + ".pdf\"");

			try {

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

				TSFacesUtil.getFacesContext().responseComplete();

			} catch (JRException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			} catch (IOException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			}

		} else {

			TSFacesUtil.addInfoMessage("O relatório não contém dados.");
		}

	}
}
