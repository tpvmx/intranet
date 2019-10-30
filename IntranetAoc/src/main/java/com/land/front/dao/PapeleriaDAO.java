package com.land.front.dao;

import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Papeleria;
import com.land.back.entities.Vacacion;

public class PapeleriaDAO extends DAOComun {

	private static final long serialVersionUID = 1L;
	private List<Papeleria> listPapeleria;
	private List<Papeleria> listPape;
	
	public void buscaPendientesPapeleria() throws Exception {
		Papeleria papeleria = new Papeleria();
		papeleria.setNum_empleado(empleadoActual);
		papeleria.queryXPendientesPapeleria();
		listPape = session.searchGeneric(papeleria);
	}

	public PapeleriaDAO(Empleado empleado) throws Exception {
		super(empleado);
	}

	public void guardaPapeleria(Papeleria obj) throws Exception {
		session.saveUpdateGeneric(obj, empleadoActual);
	}
	
	public void eliminaPapeleria(Papeleria myPapeleria)throws Exception{
		session.removeGeneric(myPapeleria);
	}

	public List<Papeleria> getListPapeleria() {
		return listPapeleria;
	}

	public void setListPapeleria(List<Papeleria> listPapeleria) {
		this.listPapeleria = listPapeleria;
	}

	public List<Papeleria> getListPape() {
		return listPape;
	}

	public void setListPape(List<Papeleria> listPape) {
		this.listPape = listPape;
	}

}
