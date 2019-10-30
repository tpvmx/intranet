package com.land.front.dao;

import java.util.ArrayList;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Evento;
import com.land.back.entities.TipoEvento;
import com.land.back.entities.Usuario;

public class CalendarioDAO extends DAOComun {
	/**
	 * Daniel
	 */
	private static final long serialVersionUID = 1L;
	private List<Evento> listEventos = new ArrayList<Evento>();
	private List<TipoEvento> listTiposEventos = new ArrayList<TipoEvento>();
	private List<TipoEvento> listTiposEventos1 = new ArrayList<TipoEvento>();

	public CalendarioDAO(Empleado empleado) throws Exception {
		super(empleado);
		// TODO Auto-generated constructor stub
	}

	public void buscaEventos() throws Exception {
		Evento e = new Evento();
		e.queryAll();
		listEventos = session.searchGeneric(e);
	}

	public void buscaTipoEventos() throws Exception {
		TipoEvento te = new TipoEvento();
		te.queryAll();
		listTiposEventos = session.searchGeneric(te);
		te.setId_tipoevento(-1L);
		te.setDescripcion("--Seleccione--");
		listTiposEventos.add(0, te);
	}

	public void buscaTipoEventos1() throws Exception {
		TipoEvento te = new TipoEvento();
		te.queryAll();
		listTiposEventos1 = session.searchGeneric(te);
	}

	public void guardaEvento(Evento myEvento) throws Exception {
		session.saveUpdateGeneric(myEvento, null);
	}

	public List<Evento> getListEventos() {
		return listEventos;
	}

	public void setListEventos(List<Evento> listEventos) {
		this.listEventos = listEventos;
	}

	public List<TipoEvento> getListTiposEventos() {
		return listTiposEventos;
	}

	public void setListTiposEventos(List<TipoEvento> listTiposEventos) {
		this.listTiposEventos = listTiposEventos;
	}

	public List<TipoEvento> getListTiposEventos1() {
		return listTiposEventos1;
	}

	public void setListTiposEventos1(List<TipoEvento> listTiposEventos1) {
		this.listTiposEventos1 = listTiposEventos1;
	}

	public void buscaEventosFiltrado(String idsSeleccion) throws Exception {
		Evento e = new Evento();
		e.queryXTipo(idsSeleccion);
		listEventos = session.searchGeneric(e);
	}

	public void eliminaEvento(Evento myEvento) throws Exception {
		session.removeGeneric(myEvento);

	}

}
