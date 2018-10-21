package br.com.tresb.enums;

public enum EnumAbas {

	CADASTRO_CLIENTE("cadastro-cliente"),
	CADASTRO_ADVOGADO("cadastro-advogado"),
	CADASTRO_EMPRESA("cadastro-empresa"),
	CADASTRO_INDICANTE("cadastro-indicante"),
	CADASTRO_PROCESSO("cadastro-processo"),
	CADASTRO_USUARIO("cadastro-usuario");
		
	private String value;

	private EnumAbas(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}
}
