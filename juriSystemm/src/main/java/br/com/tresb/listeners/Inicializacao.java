package br.com.tresb.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * Classe responsável por notificar quando a aplicação é iniciada e finalizada.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class Inicializacao implements ServletContextListener {

	private static Logger logger = Logger.getLogger(Inicializacao.class);

	public void contextDestroyed(ServletContextEvent arg0) {

		logger.info("################### 3B Soft finalizado! #############################");
	}

	public void contextInitialized(ServletContextEvent arg0) {

		logger.info("###################### 3B Soft iniciado! ########################");
	}

}
