package com.land.front.face;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertDespachado")
public class ConvertDespachado implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Integer) {
			Integer valueConver = (Integer) value;
			return valueConver == 1 ? "Despachado" : "Sin Despachar";

		}
		return null;
	}

}
