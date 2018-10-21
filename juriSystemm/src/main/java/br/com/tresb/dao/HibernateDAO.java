package br.com.tresb.dao;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe que possui acesso ao session e ao hibernateTemplate utilizados para
 * acesso ao bd. Esses dois atributos(session e hibernateTemplate) são injetados
 * pelo spring ao inicializar a aplicação, através da configuração feita no
 * arquivo persistence-config.xml.
 * 
 * @author Hylston Natann
 * @version 1.0
 * 
 */
public class HibernateDAO extends HibernateDaoSupport {

	/**
	 * Disponibiliza o uso de criteria
	 * 
	 * @param clazz
	 * @return
	 */
	protected final Criteria getCriteria(Class<?> clazz) {

		return super.getSession().createCriteria(clazz);
	}
}