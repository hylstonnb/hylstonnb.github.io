package br.com.tresb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumAbas;

@ManagedBean
@Controller
@Scope("session")
public class AbasController {

	@Autowired
	private IndicanteController indControl;

	@Autowired
	private ClienteController clienteControl;

	@Autowired
	private EmpresaController empControl;
	
	@Autowired
	private AdvogadoController advControl;
	
	@Autowired
	private UsuarioController userControl;
	
	@Autowired
	private ProcessoController procControl;

	private static final String FACES_REDIRECT = "?faces-redirect=true";

	private static final String ID_FORM_LISTAGEM = "formListagemClientes";
	
	private Map<Integer, Boolean> exibirAbas;

	private boolean exibirAbaCadCliente;

	private boolean exibirAbaCadIndicante;

	private boolean exibirAbaCadEmpresa;
	
	private boolean exibirAbaCadAdvogado;
	
	private boolean exibirAbaCadUsuario;
	
	private boolean exibirAbaCadProcesso;

	private int indexAtivo;
	
	public void inicializar(){
		
		this.setExibirAbaCadCliente(false);
		
		this.setExibirAbaCadEmpresa(false);
		
		this.setExibirAbaCadIndicante(false);
		
		this.setExibirAbaCadUsuario(false);
		
		exibirAbas = new HashMap<Integer, Boolean>(); 
		
		for (EnumAbas aba : EnumAbas.values()) {
			
			exibirAbas.put(aba.ordinal(), false);
		}		
	}

	public void exibirAbaCadCliente() {

		clienteControl.instanciar();

		this.setExibirAbaCadCliente(true);

		exibirAbas.put(EnumAbas.CADASTRO_CLIENTE.ordinal(), true);

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_CLIENTE));
	}
	
	public void exibirAbaCadAdvogado() {

		advControl.instanciar();

		this.setExibirAbaCadAdvogado(true);

		exibirAbas.put(EnumAbas.CADASTRO_ADVOGADO.ordinal(), true);

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_ADVOGADO));
	}

	public void exibirAbaCadIndicante() {

		indControl.instanciar();

		this.setExibirAbaCadIndicante(true);

		exibirAbas.put(EnumAbas.CADASTRO_INDICANTE.ordinal(), true);

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_INDICANTE));
	}

	public void exibirAbaCadUsuario() {
		
		userControl.instanciar();
		
		this.setExibirAbaCadUsuario(true);
		
		exibirAbas.put(EnumAbas.CADASTRO_USUARIO.ordinal(), true);
		
		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_USUARIO));
	}

	public void exibirAbaCadProcesso() {
		
		procControl.instanciar();
		
		this.setExibirAbaCadProcesso(true);
		
		exibirAbas.put(EnumAbas.CADASTRO_PROCESSO.ordinal(), true);
		
		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_PROCESSO));
	}
	
	public void salvarUsuario() {

		userControl.salvar();

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_USUARIO));
	}
	
	public void salvarIndicante() {

		indControl.salvar();

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_INDICANTE));
	}

	public void salvarCliente() {

		clienteControl.salvar();

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_CLIENTE));
	}

	public void salvarEmpresa() {

		empControl.salvar();

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_EMPRESA));
	}
	
	public void salvarAdvogado() {

		advControl.salvar();

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_ADVOGADO));
	}

	public void salvarProcesso() {
		
		procControl.salvar();
		
		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_PROCESSO));
	}

	public void exibirAbaCadEmpresa() {

		empControl.instanciar();

		this.setExibirAbaCadEmpresa(true);
		
		exibirAbas.put(EnumAbas.CADASTRO_EMPRESA.ordinal(), true);

		this.setIndexAtivo(this.verificarAbasAtivas(EnumAbas.CADASTRO_EMPRESA));
	}

	public int verificarAbasAtivas(EnumAbas aba) {

		int abas = 0;
		
		for (int i = 0; i < aba.ordinal(); i++) {
			
			if (exibirAbas.get(i).booleanValue() == true){
				
				abas++;
			}
		}
		
		return abas;
	}

	public void onTabChange(TabChangeEvent event) {

		String aba = event.getTab().getTitle();
	}

	public void onTabClose(TabCloseEvent event) {

		String aba = event.getTab().getTitle();

		if (aba.equals("Cadastro Indicante")) {

			this.setExibirAbaCadIndicante(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_INDICANTE.ordinal(), false);

		} else if (aba.equals("Cadastro Cliente")) {

			this.setExibirAbaCadCliente(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_CLIENTE.ordinal(), false);

		} else if (aba.equals("Cadastro Empresa")) {

			this.setExibirAbaCadEmpresa(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_EMPRESA.ordinal(), false);
			
		} else if (aba.equals("Cadastro Advogado")) {

			this.setExibirAbaCadAdvogado(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_ADVOGADO.ordinal(), false);
		} else if (aba.equals("Cadastro Processo")) {
			
			this.setExibirAbaCadProcesso(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_PROCESSO.ordinal(), false);
		} else if (aba.equals("Cadastro Usuário")) {
			
			this.setExibirAbaCadUsuario(false);
			
			exibirAbas.put(EnumAbas.CADASTRO_USUARIO.ordinal(), false);
		}	
	}

	public void onChange(TabChangeEvent event) {

		String title = event.getTab().getTitle();

		if (title.equalsIgnoreCase("processos")) {

			// this.atualizarProcessosList();

			// ProcessoController pc =
			// SpringResources.getBean(ProcessoController.class);

			// pc.setExibirPainel(false);

		} else if (title.equalsIgnoreCase("ligações")) {

			// LigacaoController lc =
			// SpringResources.getBean(LigacaoController.class);

			// lc.carregarLigacao(this.getCliente());
		}
	}

	private String getNavigationAbas() {

		return "abas" + AbasController.FACES_REDIRECT;
	}

	public boolean isExibirAbaCadCliente() {
		return exibirAbaCadCliente;
	}

	public void setExibirAbaCadCliente(boolean exibirAbaCadCliente) {
		this.exibirAbaCadCliente = exibirAbaCadCliente;
	}

	public boolean isExibirAbaCadIndicante() {
		return exibirAbaCadIndicante;
	}

	public void setExibirAbaCadIndicante(boolean exibirAbaCadIndicante) {
		this.exibirAbaCadIndicante = exibirAbaCadIndicante;
	}

	public boolean isExibirAbaCadEmpresa() {
		return exibirAbaCadEmpresa;
	}

	public boolean isExibirAbaCadUsuario() {
		return exibirAbaCadUsuario;
	}

	public void setExibirAbaCadUsuario(boolean exibirAbaCadUsuario) {
		this.exibirAbaCadUsuario = exibirAbaCadUsuario;
	}

	public void setExibirAbaCadEmpresa(boolean exibirAbaCadEmpresa) {
		this.exibirAbaCadEmpresa = exibirAbaCadEmpresa;
	}

	public int getIndexAtivo() {
		return indexAtivo;
	}

	public void setIndexAtivo(int indexAtivo) {
		this.indexAtivo = indexAtivo;
	}

	public boolean isExibirAbaCadAdvogado() {
		return exibirAbaCadAdvogado;
	}

	public void setExibirAbaCadAdvogado(boolean exibirAbaCadAdvogado) {
		this.exibirAbaCadAdvogado = exibirAbaCadAdvogado;
	}

	public boolean isExibirAbaCadProcesso() {
		return exibirAbaCadProcesso;
	}

	public void setExibirAbaCadProcesso(boolean exibirAbaCadProcesso) {
		this.exibirAbaCadProcesso = exibirAbaCadProcesso;
	}

}
