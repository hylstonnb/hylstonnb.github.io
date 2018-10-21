package br.com.tresb.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.tresb.dao.GenericDAO;
import br.com.tresb.enums.EnumAcoes;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Entidade;
import br.com.tresb.model.Historico;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

public abstract class GenericService<E extends Entidade, DAO extends GenericDAO<E>> {

	private E entidade;

	public abstract DAO getDao();

	private static Logger logger = Logger.getLogger(GenericService.class);

	/**
	 * Responsavel por verificar se os campos obrigatorios da entidade estao
	 * preenchidos e se eles sao validos. Verifica tambem se o registro ja
	 * existe na base de dados.
	 * 
	 * @param entidade
	 *            a ser validada
	 * 
	 * @return true se a validacao estiver correta, caso contrario, false
	 */
	protected abstract boolean validar(E entidade);

	/**
	 * Verifica se a entidade eh valida antes de persisti-la. Salva um historico
	 * da inclusao ou alteracao efetuada e envia uma mensagem de sucesso a
	 * interface de usuario.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param entidade
	 *            - a ser persistida ou mesclada.
	 * @return true se a validacao e insercao/alteracao ocorrer normalmente no
	 *         bd, caso contrario, false.
	 */
	public boolean salvar(E entidade) {

		entidade.setPropriedadeRelevante(UtilObjeto.primeiraLetraCaixaAlta(entidade.getPropriedadeRelevante()));

		this.setEntidade(entidade);

		this.verificarStatus();

		boolean result = false;

		try {

			if (this.validar(this.getEntidade())) {

				if (this.getEntidade().isNew()) {

					this.getDao().salvar(this.getEntidade());

					this.salvarHistorico(EnumAcoes.INCLUSAO);

				} else {

					this.getDao().mesclar(this.getEntidade());

					this.salvarHistorico(EnumAcoes.ALTERACAO);
				}

				UtilMessages.addMessageInfo(UtilMessages.ACAO_REALIZADA_COM_SUCESSO);

				result = true;
			}

		} catch (Exception e) {

			e.printStackTrace();

			UtilMessages.addMessageError("Erro ao salvar registro!");

			GenericService.logger.error("Erro ao salvar/mesclar entidade.", e);
		}

		return result;
	}

	public Long inserir(E entidade) {

		entidade.setPropriedadeRelevante(UtilObjeto.primeiraLetraCaixaAlta(entidade.getPropriedadeRelevante()));

		this.setEntidade(entidade);

		this.verificarStatus();

		Long id = -1l;

		try {

			if (this.validar(this.getEntidade())) {

				if (this.getEntidade().isNew()) {

					id = this.getDao().inserir(this.getEntidade());

					this.salvarHistorico(EnumAcoes.INCLUSAO);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

			UtilMessages.addMessageError("Erro ao inserir registro!");

			GenericService.logger.error("Erro ao salvar/mesclar entidade.", e);
		}

		return id;
	}

	public void verificarStatus() {

		if (this.getEntidade().getStatus() == null) {

			this.getEntidade().setStatus(EnumStatus.ATIVO);
		}
	}

	/**
	 * Obtem a entidade por id, altera o status para excluido(exclusao logica) e
	 * faz o merge na entidade.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param id
	 *            da entidade a ser excluida.
	 */
	public void excluir(Long id) {

		this.setEntidade(this.getDao().obterPorId(id));

		this.getEntidade().setStatus(EnumStatus.EXCLUIDO);

		this.getDao().mesclar(entidade);

		this.salvarHistorico(EnumAcoes.EXCLUSAO);

		UtilMessages.addMessageInfo(UtilMessages.ACAO_REALIZADA_COM_SUCESSO);
	}

	/**
	 * Responsavel por verificar se um registro ja existe. Verifica se a
	 * entRetorno eh diferente de nulo, caso seja nula
	 * 
	 * @author Hylston Natann
	 * 
	 * @param entidade
	 *            - entidade a ser persistida
	 * @param entRetorno
	 *            - retorno da consulta
	 * 
	 * @return true caso o registro exista, caso contrario, false
	 */
	protected boolean registroJaExiste(E entidade, E entRetorno, String mensagem) {

		boolean retorno = false;

		if (entRetorno != null) {

			if (entidade.isNew() || (!entidade.isNew() && !entidade.getId().equals(entRetorno.getId()))) {

				UtilMessages.addMessageError(mensagem != null ? mensagem : UtilMessages.REGISTRO_JA_EXISTE);

				retorno = true;
			}
		}

		return retorno;
	}

	public List<E> listar() {

		return (List<E>) this.getDao().listar();
	}

	/**
	 * Responsavel por criar e gravar um historico, recebe por parametro um dos
	 * valores do EnumAcoes exceto Alteracao e Login, que possuem metodos
	 * especificos.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param acao
	 *            - acoes disponiveis no EnumAcoes
	 */
	public void salvarHistorico(EnumAcoes acao) {

		Historico historico = new Historico();

		historico = this.criarHistorico(acao);
	}

	/**
	 * Responsavel por criar e gravar historicos de login do usuario no sistema.
	 * 
	 * @author Hylston Natann
	 */
	public void salvarHistoricoLogin() {

		Historico historico = new Historico();

		historico.setAcao(EnumAcoes.LOGIN);

		historico.setDataHora(new Date());

		historico.setUsuario(this.obterUsuario());
	}

	/**
	 * Responsavel por criar e setar atributos de um historico.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param acao
	 *            - acoes disponiveis no EnumAcoes
	 * 
	 * @return {@link Historico}
	 */
	private Historico criarHistorico(EnumAcoes acao) {

		Historico historico = new Historico();

		historico.setAcao(acao);

		historico.setClasse(this.getEntidade().getClass().getSimpleName());

		historico.setDataHora(new Date());

		historico.setIdEntidade(this.getEntidade().getId());

		historico.setUsuario(this.obterUsuario());

		return historico;
	}

	public String obterUserAgent() {

		return FacesResources.servletRequest().getHeader("User-Agent");
	}

	public Usuario obterUsuario() {

		return (Usuario) FacesResources.getAttSession("usuarioAutenticado");
	}

	public E getEntidade() {

		return entidade;
	}

	public void setEntidade(E entidade) {

		this.entidade = entidade;
	}

}
