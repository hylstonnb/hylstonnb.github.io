package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumSexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String tipo;

	private EnumSexo(String tipo) {

		this.tipo = tipo;
	}

	public String getTipo() {

		return tipo;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumSexo.values().length + 1];

		itens[0] = new SelectItem("", "Selecione");

		int i = 1;

		for (EnumSexo sexo : EnumSexo.values()) {

			itens[i++] = new SelectItem(sexo, sexo.getTipo());
		}

		return itens;
	}

}
