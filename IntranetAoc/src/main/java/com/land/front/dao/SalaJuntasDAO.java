package com.land.front.dao;

import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Papeleria;

public class SalaJuntasDAO extends DAOComun {

	private static final long serialVersionUID = 1L;
	private List<Papeleria> listPapeleria;

	public SalaJuntasDAO(Empleado empleado) throws Exception {
		super(empleado);
	}

	public void guardaPapeleria(Papeleria obj) throws Exception {
		session.saveUpdateGeneric(obj, empleadoActual);
	}

	public List<Papeleria> getListPapeleria() {
		return listPapeleria;
	}

	public void setListPapeleria(List<Papeleria> listPapeleria) {
		this.listPapeleria = listPapeleria;
	}

}
