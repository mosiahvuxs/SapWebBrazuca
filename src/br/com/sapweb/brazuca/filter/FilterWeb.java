package br.com.sapweb.brazuca.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sapweb.brazuca.util.Constantes;
import br.com.topsys.util.TSUtil;

@WebFilter(filterName = "/initFilter", urlPatterns = "/pages/*")
public class FilterWeb implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest r = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		Object sessao = request.getServletContext().getAttribute("initSessao");

		if (sessao != null) {

			r.getSession().removeAttribute(Constantes.USUARIO_CONECTADO);
			r.getSession().removeAttribute(Constantes.EMPRESA);

			request.getServletContext().setAttribute("initSessao", null);
		}

		String uri = r.getRequestURI();

		if (uri != null) {

			uri = uri.substring(uri.lastIndexOf("/"), uri.length());
		}

		if (!uri.contains("/login.xhtml")) {

			if (TSUtil.isEmpty(r.getSession().getAttribute(Constantes.USUARIO_CONECTADO))) {

				resp.sendRedirect(r.getContextPath() + "/pages/login.xhtml");

			}

		} else if (uri.contains("/login.xhtml") && !TSUtil.isEmpty(r.getSession().getAttribute(Constantes.USUARIO_CONECTADO))) {

			r.getSession().removeAttribute(Constantes.USUARIO_CONECTADO);

		}

		if (!response.isCommitted()) {

			chain.doFilter(request, response);

		}

	}

}
