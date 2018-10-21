package br.com.tresb.dao;

import org.springframework.stereotype.Repository;

import br.com.tresb.model.Historico;

/**
 * Responsavel por fornecer acesso a consultas e transacoes no banco de dados,
 * referentes a entidade Historico.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
@Repository
public class HistoricoDAO extends GenericDAO<Historico> {

	/**
	 * Responsavel por fornecer uma colecao de historicos, seguindo criterios
	 * definidos pelo objeto UtilPaginacaoInfo. Esse metodo eh utilizado para a
	 * paginacao da datatable onde sao listados os historicos. A consulta pode
	 * conter filtros que podem ser informados pelo usuario.
	 * 
	 * @param UtilPaginacaoInfo
	 *            - fornece dados para efetuar a consulta
	 * 
	 * @return Collection<Historico>
	 */
//	public Collection<Historico> listar(UtilPaginacaoInfo paginacaoInfo) {
//
//		Criteria criteria = super.getCriteria();
//
//		String usuarioFiltro = paginacaoInfo.getFilters().get("usuario");
//
//		if (UtilObjeto.isNotEmpty(usuarioFiltro)) {
//			criteria.createAlias("usuario", "u");
//			criteria.add(Restrictions.ilike("u.nome", usuarioFiltro + "%"));
//		}
//
//		return super.listar(criteria, paginacaoInfo);
//	}
}
