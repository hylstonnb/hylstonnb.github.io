package br.com.tresb.resources;

import java.util.ResourceBundle;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Classe responsável por estabelecer conexão com o banco de dados. As
 * configurações serão obtidas do arquivo connection.properties.
 * 
 * @author Hylston Natann
 * @version 1.0
 */
public class Connect extends DriverManagerDataSource {

	private ResourceBundle connection;

	public Connect() {
		connection = ResourceBundle.getBundle("connection");
		this.setDriverClassName(connection.getString("sgdb.driver_class"));
		this.setUrl(connection.getString("sgdb.url"));
		this.setUsername(connection.getString("sgdb.username"));
		this.setPassword(connection.getString("sgdb.password"));
	}
}