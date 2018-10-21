package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumEstagioLigacao {

	DOCUMENTACAO("Documentação"),
	PROCEDIMENTO("Procediemnto");

	private String status;

	private EnumEstagioLigacao(String status) {

		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumEstagioLigacao.values().length];

		int i = 0;

		for (EnumEstagioLigacao status : EnumEstagioLigacao.values()) {

			itens[i++] = new SelectItem(status, status.getStatus());
		}

		return itens;
	}

	public static SelectItem[] getSelectItensComSelect() {

		SelectItem[] itens = new SelectItem[EnumEstagioLigacao.values().length + 1];

		itens[0] = new SelectItem("", "Selecione");

		for (int i = 0; i < itens.length - 1; i++) {

			itens[i + 1] = new SelectItem(EnumEstagioLigacao.values()[i], EnumEstagioLigacao.values()[i].getStatus());
		}

		return itens;
	}
}
