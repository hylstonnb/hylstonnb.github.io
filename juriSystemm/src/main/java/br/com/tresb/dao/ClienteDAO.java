package br.com.tresb.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Cliente;

@Repository
public class ClienteDAO extends GenericDAO<Cliente> {

	public Collection<Cliente> listarClientesSemLigacao(List<Long> idClientes) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.not(Restrictions.in("id", idClientes)));

		return super.criteriaList(criteria);
	}

	@Override
	public Cliente obterPorNome(String nome) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("nome", nome));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.eq("empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		return this.criteriaUniqueResult(criteria);
	}

}
