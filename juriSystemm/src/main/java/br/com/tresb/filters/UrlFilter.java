package br.com.tresb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tresb.dao.UsuarioDAO;
import br.com.tresb.enums.EnumPerfil;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.SpringResources;
import br.com.tresb.util.UtilObjeto;

/**
 * Classe responsavel por filtrar as solicitacoes de url do usuario, liberando
 * apenas a tela de login caso o usuario nao esteja autenticado no sistema.
 * 
 * @author Hylston Natann
 * @version 1.0
 */
public class UrlFilter implements Filter {

	public void destroy() {

	}

	public void init(FilterConfig filter) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();

		if (uri.contains("resource")) {

			chain.doFilter(request, response);

			return;
		}

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioAutenticado");

		String urlLoginPage = req.getContextPath() + "/pages/login.jsf";

		if (uri.equals(req.getContextPath() + "/cadastrar.jsf")) {

			this.cadastrarUsuario();

			((HttpServletResponse) response).sendRedirect(urlLoginPage);

		} else if (usuario == null && uri.contains(urlLoginPage)) {

			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsf");

		} else if (usuario != null && uri.equals(urlLoginPage)) {

			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/home.jsf");

		} else {

			chain.doFilter(request, response);
		}
	}

	public void cadastrarUsuario() {

		UsuarioDAO dao = SpringResources.getBean(UsuarioDAO.class);

		Usuario usuario = new Usuario();

		usuario.setNome("Natann");

		usuario.setEmail("natann@3bconsultoria.com.br");

		usuario.setPerfil(EnumPerfil.ADMINISTRADOR);

		usuario.setSalt(UtilObjeto.getSalt());

		usuario.setStatus(EnumStatus.ATIVO);

		usuario.setSenha(UtilObjeto.getHashCode(UtilObjeto.getHashCode("123") + usuario.getSalt()));

		if (dao.obterPorEmail(usuario.getEmail()) == null) {

			dao.salvar(usuario);
		}
	}

}
