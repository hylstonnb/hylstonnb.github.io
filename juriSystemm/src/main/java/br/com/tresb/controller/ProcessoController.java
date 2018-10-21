package br.com.tresb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.enums.EnumPrioridade;
import br.com.tresb.model.Advogado;
import br.com.tresb.model.Documento;
import br.com.tresb.model.Processo;
import br.com.tresb.service.ProcessoService;

@Controller
@ManagedBean
@Scope("session")
public class ProcessoController {
	
	private static final String FACES_REDIRECT = "?faces-redirect=true";
	
	@Autowired
	private ProcessoService service;
	
	private Processo processo;
	
	private Advogado adv1;
	
	private Advogado adv2;
	
	private DualListModel<Documento> dualListDocumentos;

	private DualListModel<Documento> dualListDocumentosPendentes;
	
	public void instanciar() {

		this.setProcesso(new Processo());
		
		this.carregarDocumentos();
	}
	
	public void salvar() {

		if (this.getService().salvar(this.getProcesso())){
			
			this.instanciar();
		}
	}
	
	public List<Advogado> completeAdvogado(String query) {
		
		List<Advogado> allAdvs = (List<Advogado>) service.listarAdvogados();
		
		List<Advogado> filteredAdvs = new ArrayList<Advogado>();
         
        for (int i = 0; i < allAdvs.size(); i++) {
            
        	Advogado adv = allAdvs.get(i);
            
        	if(adv.getNome().toLowerCase().startsWith(query)) {
                filteredAdvs.add(adv);
            }
        }
         
        return filteredAdvs;
    }
	
	private void carregarDocumentos() {

		if (this.getProcesso().getId() != null) {

			this.setProcesso(this.getService().getDao().carregarEntidade(this.getProcesso().getId()));
		}

		List<Documento> source = this.getService().listarDocumentos();

		List<Documento> target = this.getProcesso().getDocumentosEntregues() != null ? this.getProcesso().getDocumentosEntregues() : new ArrayList<Documento>();

		for (Documento documento : target) {

			source.remove(documento);
		}

		this.setDualListDocumentos(new DualListModel<Documento>(source, target));

		// Pendentes
		List<Documento> sourcePendentes = this.getService().listarDocumentos();

		List<Documento> targetPendentes = this.getProcesso().getDocumentosFaltantes() != null ? this.getProcesso().getDocumentosFaltantes()
				: new ArrayList<Documento>();

		for (Documento documento : targetPendentes) {

			sourcePendentes.remove(documento);
		}

		this.setDualListDocumentosPendentes(new DualListModel<Documento>(sourcePendentes, targetPendentes));
	}

	
	public SelectItem[] getSelectItensEnumPrioridade() {
		
		return EnumPrioridade.getSelectItens();
	}

	public ProcessoService getService() {
		return service;
	}

	public void setService(ProcessoService service) {
		this.service = service;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Advogado getAdv1() {
		return adv1;
	}

	public void setAdv1(Advogado adv1) {
		this.adv1 = adv1;
	}

	public Advogado getAdv2() {
		return adv2;
	}

	public void setAdv2(Advogado adv2) {
		this.adv2 = adv2;
	}

	public DualListModel<Documento> getDualListDocumentos() {
		return dualListDocumentos;
	}

	public void setDualListDocumentos(DualListModel<Documento> dualListDocumentos) {
		this.dualListDocumentos = dualListDocumentos;
	}

	public DualListModel<Documento> getDualListDocumentosPendentes() {
		return dualListDocumentosPendentes;
	}

	public void setDualListDocumentosPendentes(DualListModel<Documento> dualListDocumentosPendentes) {
		this.dualListDocumentosPendentes = dualListDocumentosPendentes;
	}
	
				
}
