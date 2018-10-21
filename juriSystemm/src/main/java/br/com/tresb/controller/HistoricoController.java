package br.com.tresb.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Responsavel por disponibilizar metodos e atributos referentes a entidade
 * Historico. Os recursos disponiveis nessa classse sao utilizados na camada de
 * visao. Metodos que tratam da logica de negocio sao de responsabilidade da
 * classe HistoricoService.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
@Controller
@Scope("session")
@ManagedBean(name = "historicoController")
public class HistoricoController {

//	@Autowired
//	private HistoricoService service;
//
//	private HistoricoPojo historico;
//
//	private LazyDataModel<HistoricoPojo> historicoList;
//
//	public void listar() {
//
//		this.setHistoricoList(new HistoricoSelectableLazy(this));
//	}
//
//	public String abrirListagem() {
//
//		this.listar();
//
//		return this.getNavigationListagemHistoricos();
//	}
//
//	public void abreVisualizarCampos() {
//
//		this.setHistorico(this.getService().carregarDadosCampoValor(this.getHistorico().getId()));
//
//		if (this.getHistorico().getCampoValor() != null && !this.getHistorico().getCampoValor().isEmpty()) {
//
//			RequestContext request = RequestContext.getCurrentInstance();
//
//			request.execute("frmListagemCamposHistorico:dlgListagemCamposHistorico.show()");
//		}
//	}
//
//	public String getNavigationListagemHistoricos() {
//
//		return getClass().getSimpleName() + "/listagemHistoricos";
//	}
//
//	public LazyDataModel<HistoricoPojo> getHistoricoList() {
//
//		return historicoList;
//	}
//
//	public void setHistoricoList(LazyDataModel<HistoricoPojo> historicoList) {
//
//		this.historicoList = historicoList;
//	}
//
//	public HistoricoService getService() {
//
//		return service;
//	}
//
//	public HistoricoPojo getHistorico() {
//
//		if (this.historico == null) {
//
//			this.historico = new HistoricoPojo();
//		}
//
//		return historico;
//	}
//
//	public void setHistorico(HistoricoPojo historico) {
//
//		this.historico = historico;
//	}

}
