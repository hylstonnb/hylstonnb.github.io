package br.com.tresb.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Usuario;

@Repository
public class UsuarioDAO extends GenericDAO<Usuario> {

	/**
	 * Responsavel por obter um Usuario pelo seu email, levando em consideracao
	 * que o status seja diferente de excluido.
	 * 
	 * @param Email
	 *            do usuario que se deseja obter
	 * 
	 * @return {@link Usuario}
	 */
	public Usuario obterPorEmail(String email) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.eq("email", email).ignoreCase());

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return super.criteriaUniqueResult(criteria);
	}

	/**
	 * Responsavel por obter um Usuario pelo seu email e senha, levando em
	 * consideracao que o status seja diferente de excluido.
	 * 
	 * @param Usuario
	 *            contendo os dados e-mail e senha
	 * 
	 * @return {@link Usuario}
	 */
	public Usuario obterPorEmailSenha(Usuario usuario) {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.eq("email", usuario.getEmail()));

		criteria.add(Restrictions.eq("senha", usuario.getSenha()));

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		return super.criteriaUniqueResult(criteria);
	}

	public Usuario obterPorEmailEDataNasc(Usuario usuario) {

		Criteria criteria = super.getCriteria();

		criteria.add(Restrictions.eq("email", usuario.getEmail()).ignoreCase());

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.eq("dataNascimento", usuario.getDataNascimento()));

		return super.criteriaUniqueResult(criteria);
	}

	@Override
	public Collection<Usuario> listar() {

		Criteria criteria = this.getCriteria();

		criteria.add(Restrictions.ne("status", EnumStatus.EXCLUIDO));

		criteria.add(Restrictions.eq("empresa", this.getUserC().getUsuarioAutenticado().getEmpresaAtual()));

		return this.criteriaList(criteria);
	}

}
