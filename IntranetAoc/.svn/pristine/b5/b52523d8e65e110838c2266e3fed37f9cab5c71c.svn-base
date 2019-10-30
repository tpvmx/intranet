package com.land.front.dao;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import com.land.back.entities.Empleado;
import com.land.back.jpa.SesionJPAIntranet;
import com.land.front.util.UtilSesion;

public abstract class DAOComun implements Serializable {

	/**
	 * LGONZALEZ
	 */
	private static final long serialVersionUID = 1L;
	protected SesionJPAIntranet session;
	protected Empleado empleadoActual;

	// public DAOComun(SesionJPA _session, Usuario userActual) {
	// // this.session = SessionJPA.getInstance();
	// this.session = _session;
	// this.usuarioActual = userActual;
	// }

	public String getTexto(String key) {
		FacesContext context = UtilSesion.getFacesContext();
		return context.getApplication().evaluateExpressionGet(context, "#{texto['" + key + "']}", String.class);
	}

	public DAOComun(Empleado empleadoActual) throws Exception {
		this.session = SesionJPAIntranet.getInstanceIndependiente();
		this.empleadoActual = empleadoActual;
	}

	public void setEmpleadoActual(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;
	}

	protected Integer convertEstatus(Boolean status) {
		return status.booleanValue() ? 1 : 0;
	}

	protected Boolean convertEstatus(int status) {
		return status == 1 ? Boolean.TRUE : Boolean.FALSE;
	}
}
