package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumTipoProcesso {

	DAMS("DAMS"),
	INVALIDEZ("Invalidez"),
	MORTE("Morte");

	private String tipo;

	private EnumTipoProcesso(String tipo) {

		this.tipo = tipo;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumTipoProcesso.values().length + 1];

		itens[0] = new SelectItem("", "Selecione");

		int i = 1;

		for (EnumTipoProcesso tipoProcesso : EnumTipoProcesso.values()) {

			itens[i++] = new SelectItem(tipoProcesso, tipoProcesso.getTipo());
		}

		return itens;
	}

	public String getTipo() {

		return tipo;
	}

}
