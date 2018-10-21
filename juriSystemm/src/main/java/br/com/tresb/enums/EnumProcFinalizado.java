package br.com.tresb.enums;

public enum EnumProcFinalizado {

	MEDIANTE_ACORDO("Mediante Acordo"),
	MEDIANTE_SENTENCA("Mediante Sentença"),
	PEDIDO_IMPROCEDENTE("Pedido Improcedente");
		
	private String value;

	private EnumProcFinalizado(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}
}
