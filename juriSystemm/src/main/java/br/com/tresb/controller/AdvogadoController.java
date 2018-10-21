package br.com.tresb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumEstadosBR;
import br.com.tresb.enums.EnumOperadora;
import br.com.tresb.model.Advogado;
import br.com.tresb.model.Empresa;
import br.com.tresb.model.Endereco;
import br.com.tresb.model.Telefone;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.service.AdvogadoService;

@ManagedBean
@Controller
@Scope("session")
public class AdvogadoController {

	@Autowired
	private AdvogadoService service;

	private Advogado advogado;
	
	private List<Telefone> telefones;

	public void instanciar() {

		this.setAdvogado(new Advogado());
		
		this.getAdvogado().setEndereco(new Endereco());

		this.instanciarTelefones();
	}
	
	public void salvar() {

		this.getService().salvar(this.getAdvogado(), this.getTelefones());
	}
	
	private void instanciarTelefones() {

		this.setTelefones(new ArrayList<Telefone>());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());
	}

	public SelectItem[] getSelectItensOperadora() {

		return EnumOperadora.getSelectItens();
	}
	
	public SelectItem[] getSelectItensEstados() {

		return EnumEstadosBR.getSelectItens();
	}

	public AdvogadoService getService() {
		return service;
	}

	public void setService(AdvogadoService service) {
		this.service = service;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
