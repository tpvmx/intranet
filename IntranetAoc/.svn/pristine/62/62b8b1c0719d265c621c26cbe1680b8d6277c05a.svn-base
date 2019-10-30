package com.land.front.dao;

import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Juntas;
import com.land.back.entities.Vacacion;
import com.land.back.util.Constantes;

public class JuntasDAO extends DAOComun {

	private static final long serialVersionUID = 1L;
	private List<Juntas> listJuntas;
	private List<Juntas> listJunta;

	public JuntasDAO(Empleado empleado) throws Exception {
		super(empleado);
	}
	
	public void buscaPendientesJuntas() throws Exception {
		Juntas junta = new Juntas();
		junta.setNumEmpleado(empleadoActual);
		junta.queryXPendienteJunta();
		listJunta = session.searchGeneric(junta);
	}

	public void guardaJunta(Juntas obj) throws Exception {
		obj.setNumEmpleado(empleadoActual);
		session.saveUpdateGeneric(obj, empleadoActual);
	}
	public void eliminaJuntas(Juntas myJunta)throws Exception {
		session.removeGeneric(myJunta);
	}
	

	public List<Juntas> getListJuntas() {
		return listJuntas;
	}

	public void setListJuntas(List<Juntas> listJuntas) {
		this.listJuntas = listJuntas;
	}



	public List<Juntas> getListJunta() {
		return listJunta;
	}



	public void setListJunta(List<Juntas> listJunta) {
		this.listJunta = listJunta;
	}



}
