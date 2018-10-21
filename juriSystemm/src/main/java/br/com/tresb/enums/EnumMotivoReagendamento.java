package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumMotivoReagendamento {

	NOVO_CONTATO("Novo contato"),
	NAO_ATENDEU("Não atendeu"),
	OUTROS("Outros");

	private String motivo;

	private EnumMotivoReagendamento(String motivo) {

		this.motivo = motivo;
	}

	public String getMotivo() {

		return motivo;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumMotivoReagendamento.values().length];

		int i = 0;

		for (EnumMotivoReagendamento motivo : EnumMotivoReagendamento.values()) {

			itens[i++] = new SelectItem(motivo, motivo.getMotivo());
		}

		return itens;
	}

}
