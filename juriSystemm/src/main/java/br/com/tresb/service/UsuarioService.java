package br.com.tresb.service;

import java.util.ArrayList;
import java.util.List;

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
public class UsuarioService extends GenericService<Usuario, UsuarioDAO> {

	@Autowired
	private UsuarioDAO dao;

	private static final String MSG_SENHA_ATUAL_INVALIDA = "A senha atual È inv·lida!";

	private static final String MSG_NOVA_SENHA_NAO_CONFERE = "A nova senha n„o confere!";
	
	private static final String msgRegistroJaExiste = "Esse e-mail j· encontra-se cadastrado para outro usu·rio.";

	@Override
	public boolean salvar(Usuario usuario) {

		boolean isNew = usuario.isNew();

		String senhaGerada = "123";//this.gerarSenha();

		if (isNew) {

			usuario.setSalt(UtilObjeto.getSalt());

			usuario.setSenha(UtilObjeto.getHashCode(UtilObjeto.getHashCode(senhaGerada) + usuario.getSalt()));
		}

		boolean result = super.salvar(usuario);

		if (result && isNew) {

			this.enviarSenha(usuario.getEmail(), senhaGerada);
		}

		return result;
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

	/**
	 * Responsavel por enviar a senha inicial, quando um usuario eh cadastrado
	 * no sistema.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param email
	 *            - do usuario que receber√° a senha.
	 * @param senha
	 *            - que sera enviada por e-mail.
	 */
	private void enviarSenha(String email, String senha) {

		String titulo = "Cadastro de Usu·rio - 3BSoft";

		String mensagem = "Seu cadastro foi efetuado no 3BSoft! Para acessar utilize a senha: " + senha;

		String destino = email;

		try {

			UtilEmail.enviarEmail(destino, titulo, mensagem);

		} catch (EmailException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Responsavel por verificar se o usuario informou a senha atual correta e
	 * se o valor do campo nova senha esta de acordo com o valor do campo de
	 * confirmacao, por fim grava a alteracao de senha no bd se as condicoes
	 * forem satisfeitas.
	 * 
	 * @author Hylston Natann
	 * 
	 * @param usuarioPojo
	 * @param idUsuarioAutenticado
	 * 
	 * @return true se a senha foi alterada, senao false
	 */
	public boolean alterarSenha(Usuario usuario, Long idUsuarioAutenticado) {

		boolean result = false;

		Usuario usuarioAutenticado = this.getDao().obterPorId(idUsuarioAutenticado);

		if (UtilObjeto.getHashCode(UtilObjeto.getHashCode(usuario.getSenhaAtual()) + usuarioAutenticado.getSalt()).equals(
				usuarioAutenticado.getSenha())) {

			if (usuario.getNovaSenha().equals(usuario.getConfirmacaoNovaSenha())) {

				usuarioAutenticado.setSalt(UtilObjeto.getSalt());

				usuarioAutenticado.setSenha(UtilObjeto.getHashCode(UtilObjeto.getHashCode(usuario.getNovaSenha()) + usuarioAutenticado.getSalt()));

				this.getDao().mesclar(usuarioAutenticado);

				UtilMessages.addMessageInfo(UtilMessages.ACAO_REALIZADA_COM_SUCESSO);

				result = true;

			} else {

				UtilMessages.addMessageWarn(UsuarioService.MSG_NOVA_SENHA_NAO_CONFERE);
			}

		} else {

			UtilMessages.addMessageWarn(UsuarioService.MSG_SENHA_ATUAL_INVALIDA);
		}

		return result;
	}

	@Override
	public UsuarioDAO getDao() {

		return dao;
	}

	@Override
	protected boolean validar(Usuario usuario) {

		boolean result = false;

		List<String> camposObrigatorios = new ArrayList<String>();

		if (UtilObjeto.isEmpty(usuario.getNome())) {

			camposObrigatorios.add("Nome");
		}
		
		if (UtilObjeto.isEmpty(usuario.getEmail())) {

			camposObrigatorios.add("E-mail");
		}

		if (camposObrigatorios.isEmpty()) {

			result = !this.registroJaExiste(usuario, this.getDao().obterPorEmail(usuario.getEmail()), msgRegistroJaExiste);

		} else {

			UtilMessages.addMessageCamposObrigatorios(camposObrigatorios);
		}

		return result;
	}

}
