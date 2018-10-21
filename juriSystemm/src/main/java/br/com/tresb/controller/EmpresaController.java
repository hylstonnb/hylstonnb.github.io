package br.com.tresb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumEstadosBR;
import br.com.tresb.enums.EnumOperadora;
import br.com.tresb.model.Empresa;
import br.com.tresb.model.Endereco;
import br.com.tresb.model.Telefone;
import br.com.tresb.service.EmpresaService;
import br.com.tresb.util.UtilClone;

@Controller
@ManagedBean
@Scope("session")
public class EmpresaController {
	
	private static final String FACES_REDIRECT = "?faces-redirect=true";
	
	@Autowired
	private EmpresaService service;
	
	private Empresa empresa;
	
	@Autowired
	UtilClone<Telefone> utilCloneTelefone;
	
	private List<Telefone> telefones;
	
	public void instanciar() {

		this.setEmpresa(new Empresa());
		
		this.getEmpresa().setEndereco(new Endereco());

		this.instanciarTelefones();
	}
	
	public void salvar() {

		this.setarTelefones();

		if (this.getService().salvar(this.getEmpresa())){
			
			this.instanciar();
			this.getNavigationCadastroEmpresa();
		}
	}

	private void instanciarTelefones() {

		this.setTelefones(new ArrayList<Telefone>());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());
	}

	public void setarTelefones() {

		this.getEmpresa().setTelefones(new ArrayList<Telefone>());

		for (Telefone telefone : this.getTelefones()) {

			this.getEmpresa().getTelefones().add(telefone);
		}
	}
	
	public EmpresaService getService() {
		return service;
	}

	public void setService(EmpresaService service) {
		this.service = service;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public SelectItem[] getSelectItensEstados() {

		return EnumEstadosBR.getSelectItens();
	}

	public SelectItem[] getSelectItensOperadora() {

		return EnumOperadora.getSelectItens();
	}
	
	private String getNavigationCadastroEmpresa() {

		return "cadastro-empresa" + EmpresaController.FACES_REDIRECT;
	}
	
	public String abrirCadastrar() {

		this.instanciar();

		return this.getNavigationCadastroEmpresa();
	}
		
}
