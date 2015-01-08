package br.com.brazuca.sapweb.util;

import br.com.topsys.web.util.TSFacesUtil;

public final class Constantes {

	private Constantes() {

	}

	public static final String AUTENTICACAO_FACES = "autenticacaoFaces";
	public static final String USUARIO_CONECTADO = "usuarioConectado";
	public static final String USUARIOS_CONECTADOS = "usuariosConectados";
	public static final String EMPRESA = "empresa";

	public static final String JNDI_SAP_SERVICO_LOCAL = "java:comp/env/jdbc/ServicoSapBrazucaDS";
	public static final String JNDI_SAP_SERVICO_MATRIZ = "java:comp/env/jdbc/ServicoSapBrazucaMatrizDS";	
	
	public static final String JNDI_SAP_WEB_BRAZUCA_POSTGRESQL_LOCAL = "java:comp/env/jdbc/SapWebBrazucaDS";
	public static final String JNDI_SAP_WEB_BRAZUCA_POSTGRESQL_MATRIZ = "java:comp/env/jdbc/SapWebBrazucaMatrizDS";
	
	
	public static final String URL_RESTFUL_BRAZUCA_LOCAL = TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("L") || TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("D") || TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("H") ? Constantes.URL_RESTFUL_BRAZUCA_DESENV_LOCAL : Constantes.URL_RESTFUL_BRAZUCA_PROD_LOCAL;
	public static final String URL_RESTFUL_BRAZUCA_DESENV_LOCAL = "http://murrinha:8085/ServicoSapBrazuca/";
	public static final String URL_RESTFUL_BRAZUCA_PROD_LOCAL = "http://localhost:8085/ServicoSapBrazuca/";

	public static final String URL_RESTFUL_BRAZUCA_MATRIZ = TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("L") || TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("D") || TSFacesUtil.getRequest().getServerName().substring(0, 1).equalsIgnoreCase("H") ? Constantes.URL_RESTFUL_BRAZUCA_DESENV_MATRIZ : Constantes.URL_RESTFUL_BRAZUCA_PROD_MATRIZ;
	public static final String URL_RESTFUL_BRAZUCA_DESENV_MATRIZ = "http://murrinha:8085/ServicoSapBrazucaMatriz/";
	public static final String URL_RESTFUL_BRAZUCA_PROD_MATRIZ = "http://172.16.10.7:8085/ServicoSapBrazucaMatriz/";

	
	public static final String PASTA_RELATORIO = "relatorios";
	public static final String PASTA_ARQUIVOS = "/arquivos/";

	public static final String MIME_TYPE_HTML = "text/html";
	public static final String MIME_TYPE_PDF = "application/pdf";

	public static final String SMTP_SERVER = "mailing.grupoatarde.com.br";

	public static final String FILTRO_QUERY_PDV = " AND EXISTS (SELECT 1 FROM PEDIDO_VENDAS_LINHAS PDVL WHERE PDVL.PEDIDO_VENDA_ID = PV.ID AND PDVL.QUANTIDADE_LIBERADA > 0)";
	public static final String FILTRO_QUERY_PDV_LINHA = " AND QUANTIDADE_LIBERADA > 0";
	public static final Long ID_STATUS_NAO_PROCESSADO = 0L;
	public static final Long ID_STATUS_PROCESSADO = 1L;

	public static final Long TIPO_GRUPO_WEB = 1L;	
	public static final Long TIPO_GRUPO_ANDROID = 2L;
	public static final Long TIPO_GRUPO_IPHONE = 3L;	

}
