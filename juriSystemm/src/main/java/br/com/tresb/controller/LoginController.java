package br.com.tresb.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.service.LoginService;

@Controller
@ManagedBean
@Scope("session")
public class LoginController {

	@Autowired
	private LoginService service;

	private Usuario usuario;

	private Usuario usuarioNovaSenha;

	private static final String FACES_REDIRECT = "?faces-redirect=true";
	
	@Autowired
	private AbasController abasControl;

	public String autenticarUsuario() {

		Usuario usuarioAutenticado = this.getService().autenticarUsuario(this.getUsuario());

		if (usuarioAutenticado != null) {

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

			request.getSession().setAttribute("usuarioAutenticado", usuarioAutenticado);

			FacesResources.setAttSession("usuarioAutenticado", usuarioAutenticado);

			this.getService().salvarHistoricoLogin();
			
			abasControl.inicializar();

			return this.getNavigationHome();
		}

		return null;
	}

	public String getNavigationHome() {

		return "home" + LoginController.FACES_REDIRECT;
	}

	public void enviarEmailAlterarSenha() {

		this.getService().enviarEmailAlterarSenha(this.getUsuarioNovaSenha());
	}

	public Usuario getUsuario() {

		if (this.usuario == null) {

			this.usuario = new Usuario();
		}

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	public LoginService getService() {

		return service;
	}

	public Usuario getUsuarioNovaSenha() {

		if (this.usuarioNovaSenha == null) {

			this.usuarioNovaSenha = new Usuario();
		}

		return usuarioNovaSenha;
	}

	public void setUsuarioNovaSenha(Usuario usuarioNovaSenha) {

		this.usuarioNovaSenha = usuarioNovaSenha;
	}

}
