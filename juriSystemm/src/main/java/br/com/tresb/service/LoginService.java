package br.com.tresb.service;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.UsuarioDAO;
import br.com.tresb.model.Usuario;
import br.com.tresb.util.UtilEmail;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

@Service
public class LoginService extends GenericService<Usuario, UsuarioDAO> {

	@Autowired
	private UsuarioDAO dao;

	private static final String MSG_EMAIL_INVALIDO = "E-mail inválido!";

	private static final String MSG_SENHA_INVALIDA = "Senha inválida!";

	private void completarEmail(Usuario usuario) {

		if (usuario.getEmail() != null && !usuario.getEmail().contains("@")) {

			usuario.setEmail(usuario.getEmail() + "@3bconsultoria.com.br");
		}
	}

	/**
	 * Responsavel por verificar se o e-mail e a senha informados pelo usuario
	 * sao validos. Caso sejam validos, a entidade eh retornada, porem apenas
	 * com com alguns atributos definidos. Essa entidade sera setada na sessao.
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario autenticarUsuario(Usuario usuario) {

		this.completarEmail(usuario);

		Usuario user = this.obterUsuarioPorEmail(usuario.getEmail());

		if (user == null) {

			UtilMessages.addMessageWarn(LoginService.MSG_EMAIL_INVALIDO);

			return null;
		}

		usuario.setSenha(UtilObjeto.getHashCode(UtilObjeto.getHashCode(usuario.getSenha()) + user.getSalt()));

		usuario = this.obterUsuarioPorEmailSenha(usuario);

		if (usuario == null) {

			UtilMessages.addMessageWarn(LoginService.MSG_SENHA_INVALIDA);

			return null;

		} else {

			return this.atribuirValores(usuario);
		}
	}

	/**
	 * Responsavel por atribuir valores de alguns dos atributos da entidade
	 * Usuario que sera retornada. Esses atributos serao necessarios na sessao
	 * do usuario.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param usuario
	 *            - entidade obtida do bd com todas as suas definicoes e
	 *            relacionamentos.
	 * 
	 * @return Usuario - entidade com apenas alguns atributos definidos.
	 */
	public Usuario atribuirValores(Usuario usuario) {

		Usuario user = new Usuario();

		user.setId(usuario.getId());

		user.setNome(usuario.getNome());

		user.setPerfil(usuario.getPerfil());

		user.setEmpresaAtual(usuario.getEmpresaPrincipal());

		return user;
	}

	public void enviarEmailAlterarSenha(Usuario usuario) {

		usuario = this.obterPorEmailEDataNasc(usuario);

		String senhaGerada = this.gerarSenha();

		if (usuario != null) {

			usuario.setSalt(UtilObjeto.getSalt());

			usuario.setSenha(UtilObjeto.getHashCode(UtilObjeto.getHashCode(senhaGerada) + usuario.getSalt()));

			if (super.salvar(usuario)) {

				this.enviarSenha(usuario.getEmail(), senhaGerada);

				UtilMessages.addMessageInfo("Foi enviado um e-mail com a nova senha para o e-mail informado!");

			} else {

				UtilMessages.addMessageWarn("Erro ao salvar alterações, tente novamente!");
			}

		} else {

			UtilMessages.addMessageWarn("Dados não conferem. Tente novamente!");
		}
	}

	private void enviarSenha(String email, String senha) {

		String titulo = "Alteração de senha - 3B-Soft";

		String mensagem = "A redefinição da sua senha foi realizada com sucesso no 3B-Soft! Para acessar, utilize a senha: " + senha;

		String destino = email;

		try {

			UtilEmail.enviarEmail(destino, titulo, mensagem);

		} catch (EmailException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Responsavel por gerar um senha aleatoria alfanumerica de dez caracteres.
	 * 
	 * @author Hylston Natann
	 * 
	 * @return {@link String} - senha gerada
	 */
	private String gerarSenha() {

		return RandomStringUtils.randomAlphanumeric(10);
	}

	public Usuario obterPorEmailEDataNasc(Usuario usuario) {

		return this.getDao().obterPorEmailEDataNasc(usuario);
	}

	private Usuario obterUsuarioPorEmailSenha(Usuario usuario) {

		return this.getDao().obterPorEmailSenha(usuario);
	}

	private Usuario obterUsuarioPorEmail(String email) {

		return this.getDao().obterPorEmail(email);
	}

	@Override
	public UsuarioDAO getDao() {

		return dao;
	}

	@Override
	protected boolean validar(Usuario entidade) {

		return true;
	}
}
