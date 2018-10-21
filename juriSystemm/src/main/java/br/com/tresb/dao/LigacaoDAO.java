package br.com.tresb.dao;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumEstagioLigacao;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.enums.EnumStatusAgendamento;
import br.com.tresb.model.Ligacao;

@Repository
public class LigacaoDAO extends GenericDAO<Ligacao> {

	public Collection<Ligacao> listarPorCliente(Long idCliente) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.eq("cliente.id", idCliente));

		return super.criteriaList(criteria);
	}

	public Collection<Ligacao> listarPorIntervaloData(Date dataInicio, Date dataFim) {

		Collection<Ligacao> result = this.consultarRegistrosComHoraDefinida(dataInicio, dataFim);

		result.addAll(this.consultarRegistrosSemHoraDefinida(dataInicio));

		return result;
	}

	public Collection<Ligacao> listarPorStatusFechamento(EnumEstagioLigacao estagio) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.addOrder(Order.asc("dataHora"));

		return this.criteriaList(criteria);
	}

	@Override
	public Collection<Ligacao> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.addOrder(Order.asc("dataHora"));

		return super.criteriaList(criteria);
	}

	private Collection<Ligacao> consultarRegistrosSemHoraDefinida(Date dataInicio) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.add(Restrictions.eq("dataHora", dataInicio));

		criteria.addOrder(Order.desc("prioridade"));

		return super.criteriaList(criteria);
	}

	private Collection<Ligacao> consultarRegistrosComHoraDefinida(Date dataInicio, Date dataFim) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		Calendar hora = Calendar.getInstance();

		hora.setTime(dataInicio);

		hora.set(Calendar.MINUTE, 1);

		criteria.add(Restrictions.ge("dataHora", hora.getTime()));

		criteria.add(Restrictions.le("dataHora", dataFim));

		criteria.addOrder(Order.asc("dataHora"));

		return super.criteriaList(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Long> obterIdClientesComLigacao() {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.setProjection(Projections.property("cliente.id"));

		criteria.setProjection(Projections.distinct(Projections.property("cliente.id")));

		return criteria.list();
	}

	public Ligacao obterPorIdCliente(Long id) {

		Criteria criteria = super.getCriteria();

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.id", id));

		return (Ligacao) criteria.uniqueResult();
	}

	public Collection<Ligacao> consultarRegistrosNaoReagendados(Date data) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		criteria.add(Restrictions.le("dataHora", data));

		criteria.add(Restrictions.eq("statusAgendamento", EnumStatusAgendamento.PENDENTE));

		return super.criteriaList(criteria);
	}

	public void atualizarStatusExcluido() {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.eq("status", EnumStatus.EXCLUIDO));

		criteria.createAlias("cliente", "cliente");

		criteria.add(Restrictions.eq("cliente.status", EnumStatus.ATIVO));

		List<Ligacao> ligacoes = (List<Ligacao>) super.criteriaList(criteria);

		for (Ligacao ligacao : ligacoes) {

			ligacao.setStatus(EnumStatus.ATIVO);

			this.mesclar(ligacao);
		}
	}

}
