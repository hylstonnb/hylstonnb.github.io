package br.com.tresb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumEstadosBR;
import br.com.tresb.enums.EnumOperadora;
import br.com.tresb.model.Indicante;
import br.com.tresb.model.Empresa;
import br.com.tresb.model.Telefone;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.resources.SpringResources;
import br.com.tresb.service.IndicanteService;
import br.com.tresb.util.UtilClone;

@Controller
@ManagedBean
@Scope("session")
public class IndicanteController {

	@Autowired
	private IndicanteService service;

	@Autowired
	private ConfirmDialogController confirmDialog;

	@Autowired
	UtilClone<Telefone> utilCloneTelefone;

	private static final String ID_FORM_LISTAGEM = "formListagemindicantees";

	private static final String FACES_REDIRECT = "?faces-redirect=true";

	private Indicante indicante;

	private List<Indicante> indicanteList;

	private List<Telefone> telefones;

	public void instanciar() {

		this.setindicante(new Indicante());

		this.instanciarTelefones();
	}

	private void instanciarTelefones() {

		this.setTelefones(new ArrayList<Telefone>());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());
	}

	public String abrirCadastrar() {

		this.instanciar();

		return this.getNavigationCadastroindicante();
	}

	public void abrirEditar() {

		this.setindicante(this.getService().getDao().carregarEntidade(this.getIndicante().getId()));

		this.setTelefones(utilCloneTelefone.clonar(this.getIndicante().getTelefones()));

		while (this.getTelefones().size() < 4) {

			this.getTelefones().add(new Telefone());
		}

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
				.getApplication().getNavigationHandler();

		configurableNavigationHandler.performNavigation(this.getNavigationCadastroindicante() + IndicanteController.FACES_REDIRECT);
	}

	public String listagemindicantees() {

		this.setindicanteList(this.getService().listar());

		return this.getNavigationListagemindicantees();
	}

	public void salvarTelaCliente() {

		this.setarTelefones();

		Empresa empresa = ((Usuario) FacesResources.getAttSession("usuarioAutenticado")).getEmpresaAtual();

		this.getIndicante().setEmpresa(empresa);

		if (this.getService().salvar(this.getIndicante())) {

			ClienteController clientec = SpringResources.getBean(ClienteController.class);

			clientec.atualizarIndicanteList();
			
			FacesResources.getRequestContext().execute("formCadastroindicante:dlgCadastroindicante.hide()");
		}

	}

	public void salvar() {

		this.setarTelefones();

		Empresa empresa = ((Usuario) FacesResources.getAttSession("usuarioAutenticado")).getEmpresaAtual();

		this.getIndicante().setEmpresa(empresa);

		if (this.getService().salvar(this.getIndicante())){
			
			this.instanciar();
		}
	}

	public void setarTelefones() {

		this.getIndicante().setTelefones(new ArrayList<Telefone>());

		for (Telefone telefone : this.getTelefones()) {

			this.getIndicante().getTelefones().add(telefone);
		}
	}

	public void abreExcluir() {

		this.getConfirmDialog().setAction("#{indicanteController.excluir}");

		this.getConfirmDialog().getConfirmButton().setUpdate(":" + IndicanteController.ID_FORM_LISTAGEM);
	}

	public void excluir() {

		this.getService().excluir(this.getIndicante().getId());

		this.getIndicanteList().remove(this.getIndicante());
	}

	private String getNavigationListagemindicantees() {

		return "listagem-indicantees" + IndicanteController.FACES_REDIRECT;
	}

	private String getNavigationCadastroindicante() {

		return "home" + IndicanteController.FACES_REDIRECT;
	}

	public SelectItem[] getSelectItensEstados() {

		return EnumEstadosBR.getSelectItens();
	}

	public SelectItem[] getSelectItensOperadora() {

		return EnumOperadora.getSelectItens();
	}

	public Indicante getIndicante() {

		if (this.indicante == null) {

			this.instanciar();
		}

		return indicante;
	}

	public void setindicante(Indicante indicante) {

		this.indicante = indicante;
	}

	public IndicanteService getService() {

		return service;
	}

	public List<Telefone> getTelefones() {

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
	}

	public List<Indicante> getIndicanteList() {

		return indicanteList;
	}

	public void setindicanteList(List<Indicante> indicanteList) {

		this.indicanteList = indicanteList;
	}

	public ConfirmDialogController getConfirmDialog() {

		return confirmDialog;
	}

	public void setConfirmDialog(ConfirmDialogController confirmDialog) {

		this.confirmDialog = confirmDialog;
	}

}
