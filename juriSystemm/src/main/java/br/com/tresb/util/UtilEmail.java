package br.com.tresb.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

/**
 * Classe responsavel por efetuar o envio de e-mails.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class UtilEmail extends HtmlEmail {

	static Logger logger = Logger.getLogger(UtilEmail.class);

	public UtilEmail() throws EmailException {

		super();

		this.setSentDate(new Date());

		this.setHostName(UtilEmailBundle.SMTP_SERVER);

		Integer smtpPorta = UtilEmailBundle.SMTP_PORTA;

		if (smtpPorta != null) {

			this.setSmtpPort(smtpPorta);
		}

		configuracoesAdicionaisEmail();

		this.setAuthentication(UtilEmailBundle.EMAIL_USUARIO, UtilEmailBundle.EMAIL_SENHA);

		this.setFrom(UtilEmailBundle.EMAIL_USUARIO, UtilEmailBundle.NOME_EMPRESA);
	}

	private void configuracoesAdicionaisEmail() {

		if ("smtp.gmail.com".equals(this.getHostName())) {

			this.setSmtpPort(587);

			this.setSslSmtpPort("587");

			this.setTLS(true);
		}
	}

	public void addToEmailsConcatenados(String emailsTo, String nomeTo) throws EmailException {

		List<String> emailsLista = UtilObjeto.formarListaComString(emailsTo, ";");

		for (String email : emailsLista) {

			this.addTo(email, nomeTo);
		}
	}

	public void sendComThread() {

		final UtilEmail utilEmail = this;

		new Thread() {

			@Override
			public void run() {

				try {

					utilEmail.send();

				} catch (EmailException e) {

					logger.error("", e);
				}
			}

		}.start();
	}

	public static void enviarEmail(String emailTO, String subject, String html) throws EmailException {

		UtilEmail email = new UtilEmail();

		email.addTo(emailTO);

		email.setSubject(subject);

		email.setHtmlMsg(html);

		email.sendComThread();
	}
}