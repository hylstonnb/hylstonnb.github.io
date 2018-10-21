package br.com.tresb.controller;

import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumPerfil;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.service.UsuarioService;

@ManagedBean
@Controller
@Scope("session")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	private static final String ID_FORM_LISTAGEM = "formListagemUsuario";

	private static final String FACES_REDIRECT = "?faces-redirect=true";

	private static final String ID_DIALOG_ALTERAR_SENHA = "frmAlterarSenha:dlgAlterarSenha";

	private static final String ID_GROWL_ALTERAR_SENHA = "frmAlterarSenha:growl";

	private Usuario usuario;

	private List<Usuario> usuarioList;

	private boolean renderizarMenu;
	
	private boolean renderizarAdvogados;

	public void instanciar() {

		this.setUsuario(new Usuario());
	}

	public void salvar() {

		//this.getUsuario().setEmpresa(this.getUsuarioAutenticado().getEmpresa());

		this.getService().salvar(this.getUsuario());
	}

	public String listagemUsuarios() {

		this.setUsuarioList(this.getService().listar());

		return this.getNavigationListagemUsuarios();
	}

	public void abrirEditar() {

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
				.getApplication().getNavigationHandler();

		configurableNavigationHandler.performNavigation(this.getNavigationCadastroUsuario() + UsuarioController.FACES_REDIRECT);
	}

	public String abrirCadastro() {

		this.instanciar();

		return this.getNavigationCadastroUsuario();
	}

	public String logOut() {

		FacesResources.setAttSession("usuarioAutenticado", null);

		return this.getNavigationLoginUsuario();
	}

	public void excluir() {

		this.getService().excluir(this.getUsuario().getId());

		this.getUsuarioList().remove(this.getUsuario());
	}

	public SelectItem[] getSelectItensStatus() {

		return EnumStatus.getSelectItems();
	}

	public SelectItem[] getSelectItensPerfil() {

		return EnumPerfil.getSelectItems();
	}

	private String getNavigationLoginUsuario() {

		return "login" + UsuarioController.FACES_REDIRECT;
	}

	private String getNavigationListagemUsuarios() {

		return "listagem-usuarios" + UsuarioController.FACES_REDIRECT;
	}

	private String getNavigationCadastroUsuario() {

		return "cadastro-usuario" + UsuarioController.FACES_REDIRECT;
	}

	public Usuario getUsuarioAutenticado() {

		return (Usuario) FacesResources.getAttSession("usuarioAutenticado");
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {

		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {

		this.usuarioList = usuarioList;
	}

	public UsuarioService getService() {

		return service;
	}

	public boolean isRenderizarMenu() {

		this.setRenderizarMenu(this.getUsuarioAutenticado().getPerfil().equals(EnumPerfil.NIVEL3));

		return renderizarMenu;
	}

	public void setRenderizarMenu(boolean renderizarMenu) {

		this.renderizarMenu = renderizarMenu;
	}

	public boolean isRenderizarAdvogados() {
		
		this.setRenderizarAdvogados(this.getUsuarioAutenticado().getPerfil().equals(EnumPerfil.ADMINISTRADOR));
		
		return renderizarAdvogados;
	}

	public void setRenderizarAdvogados(boolean renderizarAdvogados) {
		this.renderizarAdvogados = renderizarAdvogados;
	}

}
