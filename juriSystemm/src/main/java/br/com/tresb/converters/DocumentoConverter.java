package br.com.tresb.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tresb.dao.DocumentoDAO;
import br.com.tresb.model.Documento;
import br.com.tresb.resources.SpringResources;
import br.com.tresb.util.UtilObjeto;

@FacesConverter(value = "documentoConverter", forClass = Documento.class)
public class DocumentoConverter implements Converter {

	private DocumentoDAO daoDocumento;

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {

		if (UtilObjeto.isNotEmpty(submittedValue)) {

			return this.getDaoDocumento().obterPorDescricao(submittedValue);
		}

		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {

		try {

			if (value == null || value.equals("")) {

				return "";

			} else {

				return String.valueOf(((Documento) value).getDescricao());
			}

		} catch (Exception e) {

			System.out.println("Documento converter");

			return "";
		}

	}

	public DocumentoDAO getDaoDocumento() {

		if (this.daoDocumento == null) {

			this.daoDocumento = SpringResources.getBean(DocumentoDAO.class);
		}

		return daoDocumento;
	}
}
