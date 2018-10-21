package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumPerfil {

	ADMINISTRADOR("Administrador"),
	NIVEL2("Nível 2"),
	NIVEL3("Nível 3");

	private String value;

	private EnumPerfil(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}

	public static SelectItem[] getSelectItems() {

		SelectItem[] itens = new SelectItem[EnumPerfil.values().length];

		int i = 0;

		for (EnumPerfil perfil : EnumPerfil.values()) {

			itens[i++] = new SelectItem(perfil, perfil.getValue());
		}

		return itens;
	}
}
