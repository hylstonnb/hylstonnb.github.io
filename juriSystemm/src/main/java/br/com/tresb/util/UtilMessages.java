package br.com.tresb.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe responsavel por enviar uma mensagem para a tela do usuario, utilizando
 * o facesContext do Jsf.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class UtilMessages {

	public static final String MESSAGES_ID = "form:growl";

	public static final String ACAO_REALIZADA_COM_SUCESSO = "Ação realizada com sucesso!";

	public static final String REGISTRO_JA_EXISTE = "Registro já existe!";

	public static void addMessageInfo(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void addMessageError(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public static void addMessageWarn(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	public static void addMessageFatal(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
	}

	public static void addMessageCamposObrigatorios(List<String> fields) {

		String mensagem = "Os seguintes campos são obrigatórios: ";

		for (String field : fields) {

			mensagem += field + ", ";
		}

		mensagem = mensagem.substring(0, mensagem.length() - 2);

		UtilMessages.addMessageError(mensagem);
	}
	//
	// public static void addMessageCampoObrigatorio(String keyField) {
	//
	// String key = "validacao.camposObrigatorios";
	//
	// String message = UtilMessagesBundle.getString(key, keyField);
	//
	// UtilMessages.addMessageWarn(message);
	// }

}
