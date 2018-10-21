package br.com.tresb.enums;

import javax.faces.model.SelectItem;

public enum EnumOperadora {

	CLARO("Claro", "21"),
	OI("Oi", "31"),
	TIM("Tim", "41"),
	VIVO("Vivo", "20");

	private String nome;

	private String numero;

	private EnumOperadora(String nome, String numero) {

		this.nome = nome;

		this.numero = numero;
	}

	public String getNome() {

		return nome;
	}

	public String getNumero() {

		return numero;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumOperadora.values().length + 1];

		itens[0] = new SelectItem(null, "Selecione");

		int i = 1;

		for (EnumOperadora operadora : EnumOperadora.values()) {

			itens[i++] = new SelectItem(operadora, operadora.getNome());
		}

		return itens;
	}

}
