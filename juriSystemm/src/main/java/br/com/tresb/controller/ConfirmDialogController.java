package br.com.tresb.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.resources.FacesResources;

/**
 * Responsavel pela manipulacao do confirmDialog, permite que a acao do botao de
 * confirmacao do dialog seja definida dinamicamente.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
@Controller
@ManagedBean(name = "confirmDialogController")
@Scope("session")
public class ConfirmDialogController {

	private CommandButton confirmButton;

	public static final String HEADER_CONFIRM_DIALOG = "Aviso";

	public static final String MENSAGEM_CONFIRM_DIALOG = "Deseja realmente excluir o registro?";

	private String confirmMessage;

	private String header;

	public void setAction(String action) {

		this.getConfirmButton().setActionExpression(FacesResources.createMethodExpression(action, null));
	}

	public void clear() {

		this.getConfirmButton().setUpdate(null);
	}

	public CommandButton getConfirmButton() {

		if (this.confirmButton == null) {

			this.confirmButton = new CommandButton();
		}

		return confirmButton;
	}

	public void setConfirmButton(CommandButton confirmButton) {

		this.confirmButton = confirmButton;
	}

	public String getConfirmMessage() {

		this.setConfirmMessage(MENSAGEM_CONFIRM_DIALOG);

		return confirmMessage;
	}

	public void setConfirmMessage(String confirmMessage) {

		this.confirmMessage = confirmMessage;
	}

	public String getHeader() {

		this.setHeader(HEADER_CONFIRM_DIALOG);

		return header;
	}

	public void setHeader(String header) {

		this.header = header;
	}
}
