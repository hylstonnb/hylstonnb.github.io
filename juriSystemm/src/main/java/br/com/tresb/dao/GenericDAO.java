package br.com.tresb.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.tresb.controller.UsuarioController;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Entidade;
import br.com.tresb.resources.SpringResources;

/**
 * Classe responsável por fornecer a implementação dos métodos básicos de
 * persistencia e acesso ao bd, disponibilizando também o uso de Criteria
 * através da superclasse.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
@Repository
public class GenericDAO<E extends Entidade> extends HibernateDAO {

	private Class<E> type;

	private UsuarioController userC;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAO() {

		Type t = this.getClass().getGenericSuperclass();

		if (!t.getClass().getSimpleName().equals("Class")) {

			ParameterizedType pt = (ParameterizedType) t;

			type = (Class) pt.getActualTypeArguments()[0];
		}
	}

	protected static Logger logger = LoggerFactory.getLogger(GenericDAO.class);

	public void salvarOuAtualizar(E entidade) {

		this.getHibernateTemplate().setCheckWriteOperations(false);

		this.getHibernateTemplate().saveOrUpdate(entidade);

		this.getHibernateTemplate().flush();
	}

	public void salvar(E entidade) {

		this.getHibernateTemplate().setCheckWriteOperations(false);

		this.getHibernateTemplate().save(entidade);

		this.getHibernateTemplate().flush();

	}

	public void mesclar(E entidade) {

		this.getHibernateTemplate().setCheckWriteOperations(false);

		this.getHibernateTemplate().merge(entidade);

		this.getHibernateTemplate().flush();
	}

	public Long inserir(E entidade) {

		this.getHibernateTemplate().setCheckWriteOperations(false);

		return (Long) this.getHibernateTemplate().save(entidade);
	}

	public void excluir(E entidade) {

		this.getHibernateTemplate().delete(entidade);
	}

	public Collection<E> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaList(criteria);
	}

	public E obterPorId(Long id) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("id", id));

		return this.criteriaUniqueResult(criteria);
	}

	public E obterPorIdStatusAtivo(Long id) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("id", id));

		criteria.add(Restrictions.eq("status", EnumStatus.ATIVO));

		return this.criteriaUniqueResult(criteria);
	}

	public E carregarEntidade(Long id) {

		return super.getHibernateTemplate().load(type, id);

	}

	public E obterPorNome(String nome) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("nome", nome));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return this.criteriaUniqueResult(criteria);
	}

	@SuppressWarnings("unchecked")
	protected E criteriaUniqueResult(Criteria criteria) {

		return (E) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected Collection<E> criteriaList(Criteria criteria) {

		return criteria.list();
	}

	public Criteria getCriteria() {

		return super.getCriteria(type);
	}


	public UsuarioController getUserC() {

		if (this.userC == null) {

			this.userC = SpringResources.getBean(UsuarioController.class);
		}

		return userC;
	}
}