package br.com.tresb.enums;

import javax.faces.model.SelectItem;

/**
 * Responsavel por armazenar os possiveis status de um registro.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public enum EnumStatus {

	ATIVO("Ativo"),
	INATIVO("Inativo"),
	EXCLUIDO("Excluído");

	private String value;

	private EnumStatus(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}
	
	public static SelectItem[] getSelectItems() {

		SelectItem[] itens = new SelectItem[EnumStatus.values().length - 1];

		int i = 0;

		for (EnumStatus status : EnumStatus.values()) {

			if (!status.equals(EnumStatus.EXCLUIDO)) {

				itens[i++] = new SelectItem(status, status.getValue());
			}
		}

		return itens;
	}

}
