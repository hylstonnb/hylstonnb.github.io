package br.com.tresb.model;

import javax.persistence.Embeddable;

import br.com.tresb.enums.EnumOperadora;

@Embeddable
public class Telefone {

	private String numero;

	private EnumOperadora operadora;

	private String contato;

	public String getNumero() {

		return numero;
	}

	public void setNumero(String numero) {

		this.numero = numero;
	}

	public EnumOperadora getOperadora() {

		return operadora;
	}

	public void setOperadora(EnumOperadora operadora) {

		this.operadora = operadora;
	}

	public String getContato() {

		return contato;
	}

	public void setContato(String contato) {

		this.contato = contato;
	}
}
