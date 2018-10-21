package br.com.tresb.enums;

/**
 * Armazena as acoes disponiveis no sistema. Esses valores sao utilizados ao se
 * gerar um historico, visto que este armazena a acao realizada.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public enum EnumAcoes {

	LOGIN("Login"),
	INCLUSAO("Inclusão"),
	ALTERACAO("Alteração"),
	EXCLUSAO("Exclusão");

	private String value;

	private EnumAcoes(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}

}
