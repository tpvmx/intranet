/*
 * SesionUtil.java
 *
 * Copyright (c) 2015 SCM S.A. de C.V, Todos los derechos reservados.
 *
 * Este software es propiedad exclusiva de SCM y es confidencial. 
 * No se debera distribuir este software, bajo ninguna circunstancia 
 * y sin consentimiento por escrito de SCM.
 *
 */
package com.land.front.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class UtilSesion {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	public static FacesContext getFacesContext() {
		return  FacesContext.getCurrentInstance();
	}
	
	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("userid");
		else
			return null;
	}
}
