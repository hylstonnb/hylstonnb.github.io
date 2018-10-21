package br.com.tresb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumEstadosBR;
import br.com.tresb.enums.EnumOperadora;
import br.com.tresb.enums.EnumPerfil;
import br.com.tresb.model.Indicante;
import br.com.tresb.model.Cliente;
import br.com.tresb.model.Empresa;
import br.com.tresb.model.Endereco;
import br.com.tresb.model.Ligacao;
import br.com.tresb.model.Processo;
import br.com.tresb.model.Telefone;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.service.ClienteService;
import br.com.tresb.util.UtilClone;

@ManagedBean
@Controller
@Scope("session")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Autowired
	private ConfirmDialogController confirmDialog;

	@Autowired
	UtilClone<Telefone> utilCloneTelefone;

	private static final String FACES_REDIRECT = "?faces-redirect=true";

	private static final String ID_FORM_LISTAGEM = "formListagemClientes";

	private Cliente cliente;

	private List<Cliente> clienteList;

	private List<Cliente> clientesFiltrados;

	private List<Telefone> telefones;

	private List<Ligacao> ligacaoList;

	private List<Processo> processoList;

	private boolean listarClientesSemLigacao;

	private boolean renderizarAbaProcesso;
	
	private List<Indicante> indicanteList;

	public void instanciar() {

		this.setCliente(new Cliente());

		this.getCliente().setEndereco(new Endereco());

		this.instanciarTelefones();
	}

	private void instanciarTelefones() {

		this.setTelefones(new ArrayList<Telefone>());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());

		this.getTelefones().add(new Telefone());
	}

	public void salvar() {

		Empresa empresa = ((Usuario) FacesResources.getAttSession("usuarioAutenticado")).getEmpresaAtual();

		this.getCliente().setEmpresa(empresa);

		boolean isnew = this.getCliente().isNew();

		if (isnew) {

			this.getCliente().setDataCadastro(new Date());
		}

		this.getService().salvar(this.getCliente(), this.getTelefones());
	}

	public String abrirEditar() {

		this.setCliente(this.getService().getDao().carregarEntidade(this.getCliente().getId()));

		this.setTelefones(this.getCliente().getTelefones());

		while (this.getTelefones().size() < 4) {

			this.getTelefones().add(new Telefone());
		}

		return this.getNavigationCadastroCliente();
	}

	

	public void onChange(TabChangeEvent event) {

		String title = event.getTab().getTitle();

		if (title.equalsIgnoreCase("processos")) {

			this.atualizarProcessosList();

			//ProcessoController pc = SpringResources.getBean(ProcessoController.class);

			//pc.setExibirPainel(false);

		} else if (title.equalsIgnoreCase("ligações")) {

			//LigacaoController lc = SpringResources.getBean(LigacaoController.class);

			//lc.carregarLigacao(this.getCliente());
		}
	}

	public void atualizarProcessosList() {

		//this.setProcessoList(this.getService().listarProcessoPorCliente(this.getCliente().getId()));
	}

	public String abrirCadastrar() {

		this.instanciar();

		this.atualizarIndicanteList();

		return this.getNavigationCadastroCliente();
	}

	public void abreExcluir() {

		this.getConfirmDialog().setAction("#{clienteController.excluir}");

		this.getConfirmDialog().getConfirmButton().setUpdate(":" + ClienteController.ID_FORM_LISTAGEM);
	}

	public void abreVisualizar() {

		this.setCliente(this.getService().getDao().carregarEntidade(this.getCliente().getId()));

		this.setTelefones(utilCloneTelefone.clonar(this.getCliente().getTelefones()));
	}

	public void abreVisualizar(Long idCliente) {

		this.setCliente(this.getService().getDao().carregarEntidade(idCliente));

		this.setTelefones(utilCloneTelefone.clonar(this.getCliente().getTelefones()));
	}

	public String listagemClientes() {

		this.setClienteList(null);

		return this.getNavigationListagemClientes();
	}

	public List<Indicante> completeIndicante(String query) {

		List<Indicante> sugestoes = new ArrayList<Indicante>();

		for (Indicante indicante : this.getIndicanteList()) {

			if (indicante.getNome().toLowerCase().startsWith(query.toLowerCase())) {

				sugestoes.add(indicante);
			}
		}

		return sugestoes;
	}

	public void excluir() {

		this.getService().excluir(this.getCliente().getId());
	}

	public boolean isRenderizarAbaProcesso() {

		this.setRenderizarAbaProcesso(!this.getCliente().isNew()
				&& !((Usuario) FacesResources.getAttSession("usuarioAutenticado")).getPerfil().equals(EnumPerfil.NIVEL3));

		return renderizarAbaProcesso;
	}

	public void setRenderizarAbaProcesso(boolean renderizarAbaProcesso) {

		this.renderizarAbaProcesso = renderizarAbaProcesso;
	}

	public void atualizarIndicanteList() {

		this.setindicanteList(this.getService().listarIndicantes());
	}

	private String getNavigationListagemClientes() {

		return "listagem-clientes" + ClienteController.FACES_REDIRECT;
	}

	private String getNavigationCadastroCliente() {

		return "abas" + ClienteController.FACES_REDIRECT;
	}

	public SelectItem[] getSelectItensOperadora() {

		return EnumOperadora.getSelectItens();
	}

	public SelectItem[] getSelectItensEstados() {

		return EnumEstadosBR.getSelectItens();
	}

	public Cliente getCliente() {

		if (this.cliente == null) {

			this.cliente = new Cliente();
		}

		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public ClienteService getService() {

		return service;
	}

	public List<Cliente> getClienteList() {

		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {

		this.clienteList = clienteList;
	}

	public ConfirmDialogController getConfirmDialog() {

		return confirmDialog;
	}

	public void setConfirmDialog(ConfirmDialogController confirmDialog) {

		this.confirmDialog = confirmDialog;
	}

	public List<Indicante> getIndicanteList() {

		return indicanteList;
	}

	public void setindicanteList(List<Indicante> indicanteList) {

		this.indicanteList = indicanteList;
	}

	public List<Telefone> getTelefones() {

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
	}

	public List<Ligacao> getLigacaoList() {

		return ligacaoList;
	}

	public void setLigacaoList(List<Ligacao> ligacaoList) {

		this.ligacaoList = ligacaoList;
	}

	public boolean isListarClientesSemLigacao() {

		return listarClientesSemLigacao;
	}

	public void setListarClientesSemLigacao(boolean listarClientesSemLigacao) {

		if (listarClientesSemLigacao == false) {

			this.setClienteList(this.getService().listar());

		} else {

			this.setClienteList(this.getService().listarClientesSemLigacao());
		}

		this.setClientesFiltrados(this.getClienteList());

		this.listarClientesSemLigacao = listarClientesSemLigacao;
	}

	public List<Cliente> getClientesFiltrados() {

		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {

		this.clientesFiltrados = clientesFiltrados;
	}

	public List<Processo> getProcessoList() {

		return processoList;
	}

	public void setProcessoList(List<Processo> processoList) {

		this.processoList = processoList;
	}

}
