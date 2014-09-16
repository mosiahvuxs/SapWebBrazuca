package br.com.brazuca.sapweb.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@SuppressWarnings("rawtypes")
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();

        HashMap sessoesAtivas = new HashMap();
        
        context.setAttribute("sessoesAtivas", sessoesAtivas);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

}
