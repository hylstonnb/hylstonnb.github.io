package br.com.tresb.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Documento;

@Repository
public class DocumentoDAO extends GenericDAO<Documento> {

	public Documento obterPorDescricao(String descricao) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("descricao", descricao));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaUniqueResult(criteria);
	}

}
