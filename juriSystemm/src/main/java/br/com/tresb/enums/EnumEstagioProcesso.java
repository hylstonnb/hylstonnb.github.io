package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumEstagioProcesso {

	DOCUMENTACAO("Documenta��o"),
	ELABORACAO_PETICAO_INICIAL("Elabora��o Peti��o Inicial"),
	PETICAO_INICIAL_PRONTA("Peti��o Inicial Pronta"),
	PROTOCOLO_INICIAL("Protocolo Inicial"),
	DOCUMENTO_PENDENTE("Documento Pendente"),
	AUDIENCIA_CONCILIACAO("Audiencia de Concilia��o"),
	IMPUGNACAO("Impugna��o"),
	ACORDO_FIRMADO("Acordo Firmado"),
	INTERLOCUTORIA("Interlocut�ria"),
	PEDIDO_IMPROCEDENTE("Pedido Improcedente"),
	PGTO_PREVISTO("Pagamento Previsto"),
	RECEBIDO("Recebido"),
	EM_DEBITO("Em D�bito"),
	CONCLUIDO("Conclu�do");
	
	private String status;

	private EnumEstagioProcesso(String status) {

		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public static SelectItem[] getSelectItens(int numItens) {

		SelectItem[] itens = new SelectItem[numItens];

		int i = 0;

		for (EnumEstagioProcesso statusProcesso : EnumEstagioProcesso.values()) {

			itens[i++] = new SelectItem(statusProcesso, statusProcesso.getStatus());

			if (i == numItens) {

				break;
			}
		}

		return itens;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumEstagioProcesso.values().length + 1];

		itens[0] = new SelectItem("", "Selecione");

		int i = 1;

		for (EnumEstagioProcesso status : EnumEstagioProcesso.values()) {

			itens[i++] = new SelectItem(status, status.getStatus());
		}

		return itens;
	}
}
