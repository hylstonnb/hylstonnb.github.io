package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumStatusAgendamento {

	PENDENTE("Pendente"),
	EFETUADA("Efetuada");

	private String status;

	private EnumStatusAgendamento(String status) {

		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumStatusAgendamento.values().length];

		int i = 0;

		for (EnumStatusAgendamento status : EnumStatusAgendamento.values()) {

			itens[i++] = new SelectItem(status, status.getStatus());
		}

		return itens;
	}
}
