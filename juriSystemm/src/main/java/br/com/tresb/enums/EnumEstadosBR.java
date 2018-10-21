package br.com.tresb.enums;

import javax.faces.model.SelectItem;

/**
 * Responsavel por armazenar a sigla e o nome de todos os estados brasileiros.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public enum EnumEstadosBR {

	ACRE("AC", "Acre"),
	ALAGOAS("AL", "Alagoas"),
	AMAPA("AP", "Amap�"),
	AMAZONAS("AM", "Amazonas"),
	BAHIA("BA", "Bahia"),
	CEARA("CE", "Cear�"),
	DISTRITO_FEDERAL("DF", "Distrito Federal"),
	ESPIRITO_SANTO("ES", "Esp�rito Santo"),
	GOIAS("GO", "Goi�s"),
	MARANHAO("MA", "Maranh�o"),
	MATO_GROSSO("MT", "Mato Grosso"),
	MATO_GROSSO_DO_SUL("MS", "Mato Grosso do Sul"),
	MINAS_GERAIS("MG", "Minas Gerais"),
	PARA("PA", "Par�"),
	PARAIBA("PB", "Para�ba"),
	PARANA("PR", "Paran�"),
	PERNAMBUCO("PE", "Pernambuco"),
	PIAUI("PI", "Piau�"),
	RIO_DE_JANEIRO("RJ", "Rio de Janeiro"),
	RIO_GRANDE_DO_NORTE("RN", "Rio Grande do Norte"),
	RIO_GRANDE_DO_SUL("RS", "Rio Grande do Sul"),
	RONDONIA("RO", "Rond�nia"),
	RORAIMA("RR", "Roraima"),
	SANTA_CATARINA("SC", "Santa Catarina"),
	SAO_PAULO("SP", "S�o Paulo"),
	SERGIPE("SE", "Sergipe"),
	TOCANTINS("TO", "Tocantins");

	private String sigla;

	private String nome;

	private EnumEstadosBR(String sigla, String nome) {

		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {

		return sigla;
	}

	public String getNome() {

		return nome;
	}

	public static SelectItem[] getSelectItens() {

		SelectItem[] itens = new SelectItem[EnumEstadosBR.values().length + 1];

		itens[0] = new SelectItem(null, "Sel.");

		int i = 1;

		for (EnumEstadosBR estado : EnumEstadosBR.values()) {

			itens[i++] = new SelectItem(estado, estado.getSigla());
		}

		return itens;
	}
}
