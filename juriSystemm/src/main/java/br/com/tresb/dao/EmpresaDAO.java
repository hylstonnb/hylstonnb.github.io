package br.com.tresb.dao;


import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Empresa;

@Repository
public class EmpresaDAO extends GenericDAO<Empresa>{
	
	public Empresa obterPorNomeFantasia(String nomeFantasia) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("nomeFantasia", nomeFantasia));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaUniqueResult(criteria);
	}
	
	@Override
	public Collection<Empresa> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaList(criteria);
	}
	

}
