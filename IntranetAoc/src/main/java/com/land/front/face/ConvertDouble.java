package com.land.front.face;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertDouble")
public class ConvertDouble implements Converter {

	private DecimalFormat miDF = new DecimalFormat("$ ###,###,###,###,###,###,###,###,###,###.00");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value instanceof String) {
			String valueConver = (String) value;
			if (value.length() > 0)
				return new Double(valueConver.replace("$", "").replace(",", ""));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Double) {
			Double valueConver = (Double) value;
			return miDF.format(valueConver);

		}
		return null;
	}

}