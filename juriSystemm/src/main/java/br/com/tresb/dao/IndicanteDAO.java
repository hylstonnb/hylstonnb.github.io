package br.com.tresb.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Indicante;

@Repository
public class IndicanteDAO extends GenericDAO<Indicante> {

	@Override
	public Collection<Indicante> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaList(criteria);
	}

	@Override
	public Indicante obterPorNome(String nome) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("nome", nome));

		criteria.add(Restrictions.eq("empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaUniqueResult(criteria);
	}

}
