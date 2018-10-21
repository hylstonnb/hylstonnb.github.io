package br.com.tresb.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.tresb.model.Documento;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.service.DocumentoService;

@Controller
@ManagedBean
@Scope("session")
public class DocumentoController {

	@Autowired
	private DocumentoService service;

	public void instanciar() {

		this.setDocumento(new Documento());
	}

	private Documento documento;

	public void salvar() {

		if (this.getService().salvar(this.getDocumento())) {

			// ProcessoController processoc =
			// SpringResources.getBean(ProcessoController.class);
			//
			// processoc.instanciar();
			// processoc.atualizarDocumentoList();

			FacesResources.getRequestContext().execute("frmCadastroProcesso:dlgCadastroDocumento.hide()");
			//
			// FacesResources.getRequestContext().update("tvwCliente:frmCadastroProcesso:pklDocumentosPendentes");
			//
			// FacesResources.getRequestContext().execute("tvwCliente:frmCadastroProcesso:dlgCadastroProcesso.hide()");
			//
			// FacesResources.getRequestContext().execute("tvwCliente:frmCadastroProcesso:dlgCadastroProcesso.show()");
		}

	}

	public Documento getDocumento() {

		if (this.documento == null) {

			this.instanciar();
		}

		return documento;
	}

	public void setDocumento(Documento documento) {

		this.documento = documento;
	}

	public DocumentoService getService() {

		return service;
	}

}
