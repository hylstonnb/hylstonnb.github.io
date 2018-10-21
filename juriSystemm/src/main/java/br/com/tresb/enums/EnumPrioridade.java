package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumPrioridade {

	MEDIA("Média"),
	ALTA("Alta");

	private String value;

	private EnumPrioridade(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumPrioridade.values().length];

		int i = 0;

		for (EnumPrioridade prioridade : EnumPrioridade.values()) {

			itens[i++] = new SelectItem(prioridade, prioridade.getValue());
		}

		return itens;
	}

}
