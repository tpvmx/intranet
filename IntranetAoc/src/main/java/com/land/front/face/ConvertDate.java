package com.land.front.face;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertDate")
public class ConvertDate implements Converter {

	private SimpleDateFormat miDF = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value instanceof String) {
			String valueConver = (String) value;
			if (value.length() > 0)
				try {
					return miDF.parse(valueConver);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Date) {
			Date valueConver = (Date) value;
			return miDF.format(valueConver);
		}
		return null;
	}

}
