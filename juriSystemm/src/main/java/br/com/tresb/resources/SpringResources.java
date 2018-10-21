package br.com.tresb.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import br.com.tresb.util.UtilObjeto;

/**
 * Classe responsável por setar o applicationContext ao inicializar a aplicação
 * e fornecer acesso ao container do spring que gerencia os beans.
 * 
 * @author Hylston Natann
 * @version 1.0
 * 
 */
public class SpringResources implements ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(SpringResources.class);
	protected static ApplicationContext applicationContext;

	/**
	 * Método responsável por setar o applicationContext. Através da
	 * implementação da classe ApplicationContextAware, o método é invocado
	 * quando a aplicação está sendo inicilizada.
	 * 
	 */
	public void setApplicationContext(ApplicationContext appCtx) throws BeansException {

		cleanApplicationContext();
		applicationContext = appCtx;
		logger.info("Inicializando o application Context do Spring");
	}

	public static ApplicationContext getApplicationContext() {

		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanAlias) {
		return (T) getApplicationContext().getBean(beanAlias);
	}

	public static <T> T getBean(Class<T> clazz) {
		String beanAlias = UtilObjeto.beanAlias(clazz);
		return getBean(beanAlias);
	}

	public static String getRealPath() {
		String path = getApplicationContext().getClassLoader().getResource("").getPath();
//		return path.replace("/", "\\").substring(1, path.lastIndexOf("web") + 3);
		return path.replace("/", "\\").substring(1, path.lastIndexOf("WEB-INF"));
	}

	public static void cleanApplicationContext() {
		applicationContext = null;
	}
}