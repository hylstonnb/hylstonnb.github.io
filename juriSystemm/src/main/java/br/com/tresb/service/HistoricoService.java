package br.com.tresb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.HistoricoDAO;

/**
 * Responsavel por disponibilizar metodos referentes a logica de negocio, da
 * entidade AreaDeInteresse.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
@Service
public class HistoricoService {

	@Autowired
	private HistoricoDAO dao;
//
//	public List<Historico> listar() {
//
//		return (List<Historico>) this.getDao().listar();
//	}
//
//	/**
//	 * Responsavel por receber uma lista de Historico e retornar uma lista de
//	 * HistoricoPojo.
//	 * 
//	 * @author Hylston Natann
//	 * 
//	 * @param historicos
//	 * 
//	 * @return {@link List<HistoricoPojo>}
//	 */
//	public List<HistoricoPojo> listarPojo(List<Historico> historicos) {
//
//		List<HistoricoPojo> historicosPojo = new ArrayList<HistoricoPojo>();
//
//		for (Historico historico : historicos) {
//
//			historicosPojo.add(this.montarPojo(historico));
//		}
//
//		return historicosPojo;
//	}
//
//	/**
//	 * Responsavel por obter um objeto do tipo Historico e retornar um objeto do
//	 * tipo HistoricoPojo, com base nos dados do objeto Historico recebido.
//	 * 
//	 * @param historico
//	 * 
//	 * @return {@link HistoricoPojo}
//	 */
//	private HistoricoPojo montarPojo(Historico historico) {
//
//		HistoricoPojo historicoPojo = new HistoricoPojo();
//
//		historicoPojo.setId(historico.getId());
//
//		historicoPojo.setAcao(historico.getAcao().getKey());
//
//		historicoPojo.setClasse(historico.getClasse());
//
//		historicoPojo.setDataHora(historico.getDataHoraFormatada());
//
//		historicoPojo.setUsuario(historico.getUsuario().getNome());
//
//		historicoPojo.setIdEntidade(historico.getIdEntidade());
//
//		historicoPojo.setNomeEntidade(historico.getIdEntidade() != null ? this.obterNomeEntidade(historicoPojo) : null);
//
//		return historicoPojo;
//	}
//
//	/**
//	 * Obtem o dao necessario de acordo com a classe a qual o historico diz
//	 * respeito, obtem a entidade e retorna a propriedadeRelevante dessa
//	 * entidade.
//	 * 
//	 * @param historico
//	 * 
//	 * @return {@link String}
//	 */
//	private String obterNomeEntidade(HistoricoPojo historico) {
//
//		String result;
//
//		GenericDAO<?> dao = SpringCtxHolder.getBean(UtilString.upperToLowercase(historico.getClasse(), 0, 1) + "DAO");
//
//		Entidade entidade = dao.obterPorId(historico.getIdEntidade());
//
//		result = entidade != null ? entidade.getPropriedadeRelevante() : "";
//
//		return result;
//	}
//
//	/**
//	 * Responsavel por setar as keys referente aos nomes dos campos alterados e
//	 * o valor anterior desses campos.
//	 * 
//	 * @author Hylston Natann
//	 * 
//	 * @param id
//	 * 
//	 * @return {@link HistoricoPojo} - historicoPojo com a lista de campos
//	 *         alterados definida.
//	 */
//	public HistoricoPojo carregarDadosCampoValor(Long id) {
//
//		Historico historico = this.getDao().obterPorId(id);
//
//		HistoricoPojo historicoPojo = this.montarPojo(historico);
//
//		historicoPojo.setCampoValor(new ArrayList<CampoValorPojo>());
//
//		for (Entry<String, String> campo : historico.getCamposAlterados().entrySet()) {
//
//			CampoValorPojo campoValor = new CampoValorPojo();
//
//			campoValor.setCampo("label." + campo.getKey());
//
//			if (campo.getValue().startsWith("enum")) {
//
//				campoValor.setValorAnterior(this.obterValorEnum(campo.getValue()));
//
//			} else {
//
//				campoValor.setValorAnterior(campo.getValue());
//			}
//
//			historicoPojo.getCampoValor().add(campoValor);
//		}
//
//		return historicoPojo;
//	}
//
//	/**
//	 * Responsavel por receber uma string com as keys de um enum separadas por
//	 * virgula e obter a string a qual a key se refere, para montar novamente a
//	 * string de retorno separada por virgula. A String retornada nao ira conter as
//	 * chaves, mas sim os valores aos quais as chaves se referem.
//	 * 
//	 * @author Hylston Natann
//	 * 
//	 * @param string
//	 * 
//	 * @return {@link String}
//	 */
//	private String obterValorEnum(String string) {
//
//		String valorAnterior = "";
//
//		for (String value : Arrays.asList(string.split(", "))) {
//
//			valorAnterior += UtilMessagesBundle.getString(value) + ", ";
//		}
//
//		return valorAnterior.substring(0, valorAnterior.length() - 2);
//	}

	public HistoricoDAO getDao() {

		return dao;
	}

}
