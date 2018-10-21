package br.com.tresb.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tresb.dao.IndicanteDAO;
import br.com.tresb.model.Indicante;
import br.com.tresb.resources.SpringResources;
import br.com.tresb.util.UtilObjeto;

@FacesConverter(value = "indicanteConverter", forClass = Indicante.class)
public class IndicanteConverter implements Converter {

	private IndicanteDAO daoIndicante;

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {

		if (UtilObjeto.isNotEmpty(submittedValue)) {

			return this.getDaoIndicante().obterPorNome(submittedValue);
		}

		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {

		try {

			if (value == null || value.equals("")) {

				return "";

			} else {

				return String.valueOf(((Indicante) value).getNome());
			}

		} catch (Exception e) {

			System.out.println("Captador converter");

			return "";
		}

	}

	public IndicanteDAO getDaoIndicante() {

		if (this.daoIndicante == null) {

			this.daoIndicante = SpringResources.getBean(IndicanteDAO.class);
		}

		return daoIndicante;
	}
}
