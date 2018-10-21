package br.com.tresb.dao;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.enums.EnumTipoProcesso;
import br.com.tresb.model.Processo;

@Repository
public class ProcessoDAO extends GenericDAO<Processo> {

	public Collection<Processo> listarPorCliente(Long idCliente) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.eq("cliente.id", idCliente));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return super.criteriaList(criteria);
	}

	@Override
	public Collection<Processo> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		return this.criteriaList(criteria);
	}

	public Collection<Processo> listarEnviadosNoMes(Date dataInicio, Date dataFinal) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.ge("dataEnvio", dataInicio));

		criteria.add(Restrictions.le("dataEnvio", dataFinal));

		criteria.add(Restrictions.ne("tipo", EnumTipoProcesso.DAMS));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		return this.criteriaList(criteria);
	}

	public int obterQtdeProcRecebidosNoPeriodo(Date dataInicio, Date dataFinal) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.ge("dataRecebimento", dataInicio));

		criteria.add(Restrictions.le("dataRecebimento", dataFinal));

		criteria.add(Restrictions.ne("tipo", EnumTipoProcesso.DAMS));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		return this.criteriaList(criteria).size();
	}
}
