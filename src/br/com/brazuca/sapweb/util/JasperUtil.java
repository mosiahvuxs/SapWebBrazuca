package br.com.brazuca.sapweb.util;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.topsys.exception.TSSystemException;
import br.com.topsys.web.util.TSFacesUtil;

public class JasperUtil {

	public void gerarRelatorio(String jasper, Map<String, Object> parametros, String jndi) throws ClassNotFoundException, JRException, SQLException {

		Connection con = Utilitarios.getConnection(jndi);

		jasper = TSFacesUtil.getServletContext().getRealPath("WEB-INF" + File.separator + "relatorios" + File.separator + jasper);

		JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);

		if (impressao.getPages().size() == 0) {

			TSFacesUtil.addErrorMessage("Nenhum relatório encontrado na pesquisa !!! ");
		} else {

			ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			response.setContentType("APPLICATION/PDF");

			response.setHeader("Content-Disposition", "attachment; filename=listagemmedicao.pdf");

			try {

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());
			} catch (Exception e) {
				throw new TSSystemException(e);
			} finally {

				Utilitarios.closeConnection(con);
			}

			TSFacesUtil.getFacesContext().responseComplete();

		}

	}
}