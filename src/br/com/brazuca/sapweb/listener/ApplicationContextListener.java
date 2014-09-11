package br.com.brazuca.sapweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		event.getServletContext().setAttribute("initSessao", 0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
