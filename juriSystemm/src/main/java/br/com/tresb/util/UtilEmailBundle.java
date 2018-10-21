package br.com.tresb.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Responsavel por ler o arquivo email.properties e disponibilizar as
 * informacoes basicas para o envio de e-mails, contidas nesse arquivo.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class UtilEmailBundle {

	static Logger logger = Logger.getLogger(UtilEmailBundle.class);

	public static final ResourceBundle EMAIL_BUNDLE = ResourceBundle.getBundle("email");

	private static final String KEY_NOME_EMPRESA = "nome.empresa";

	private static final String KEY_SMTP_SERVER = "smtp.server";

	private static final String KEY_EMAIL_USUARIO = "email.usuario";

	private static final String kEY_EMAIL_SENHA = "email.senha";

	private static final String kEY_SMTP_PORTA = "smtp.porta";

	public static final String NOME_EMPRESA = getProperty(KEY_NOME_EMPRESA);

	public static final String SMTP_SERVER = getProperty(KEY_SMTP_SERVER);

	public static final String EMAIL_USUARIO = getProperty(KEY_EMAIL_USUARIO);

	public static final String EMAIL_SENHA = getProperty(kEY_EMAIL_SENHA);

	public static final Integer SMTP_PORTA = Integer.parseInt(getProperty(kEY_SMTP_PORTA));

	private static String getProperty(String prop) {

		try {

			return EMAIL_BUNDLE.getString(prop);

		} catch (Exception e) {

			String m = "prop n�o encontrada, " + prop;
			logger.error(m, e);
		}

		return null;
	}

}
