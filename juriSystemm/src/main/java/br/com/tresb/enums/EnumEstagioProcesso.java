package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumEstagioProcesso {

	DOCUMENTACAO("Documentação"),
	ELABORACAO_PETICAO_INICIAL("Elaboração Petição Inicial"),
	PETICAO_INICIAL_PRONTA("Petição Inicial Pronta"),
	PROTOCOLO_INICIAL("Protocolo Inicial"),
	DOCUMENTO_PENDENTE("Documento Pendente"),
	AUDIENCIA_CONCILIACAO("Audiencia de Conciliação"),
	IMPUGNACAO("Impugnação"),
	ACORDO_FIRMADO("Acordo Firmado"),
	INTERLOCUTORIA("Interlocutória"),
	PEDIDO_IMPROCEDENTE("Pedido Improcedente"),
	PGTO_PREVISTO("Pagamento Previsto"),
	RECEBIDO("Recebido"),
	EM_DEBITO("Em Débito"),
	CONCLUIDO("Concluído");
	
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
