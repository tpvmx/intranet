package com.land.front.dao;

import java.util.Date;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.GastoViaje;
import com.land.back.entities.Juntas;
import com.land.back.entities.Papeleria;
import com.land.back.entities.Vacacion;
import com.land.back.util.Constantes;
import com.land.front.util.UtilTimestamp;

@SuppressWarnings("static-access")
public class ConsultaFormDAO extends DAOComun {

	private static final long serialVersionUID = 1L;
	private List<Papeleria> listPapeleria;
	private List<Vacacion> listVacacion;
	private List<GastoViaje> listGastoViaje;
	private List<Juntas> listJuntas;

	public ConsultaFormDAO(Empleado empleadoActual) throws Exception {
		super(empleadoActual);
	}

	public void buscaVacacion(Date fechaInicio, Date fechaFin, int estatus) throws Exception {
		Vacacion va = new Vacacion();
		Date nuevoInicio = UtilTimestamp.getInstance().getDateCeroHoras(fechaInicio);
		Date nuevoFin = UtilTimestamp.getInstance().getDate24Horas(fechaFin);
		va.setFechaInicio(nuevoInicio);
		va.setFechaFin(nuevoFin);
		va.setEstatus(estatus);
		va.queryXFechaVacacion();

		listVacacion = session.searchGeneric(va);

	}

	public void buscaJuntas(Date fechaInicio, Date fechaFin, int estatus) throws Exception {
		Juntas juntas = new Juntas();
		Date nuevoInicio = UtilTimestamp.getInstance().getDateCeroHoras(fechaInicio);
		Date nuevoFin = UtilTimestamp.getInstance().getDate24Horas(fechaFin);
		juntas.setFechaInicio(nuevoInicio);
		juntas.setFechaFin(nuevoFin);
		juntas.setEstatus(estatus);
		juntas.queryXFechaJuntas();

		listJuntas = session.searchGeneric(juntas);

	}

	public void buscaViaticos(Date fechaInicio, Date fechaFin, int estatus) throws Exception {
		 GastoViaje gasto = new GastoViaje();
		Date nuevoInicio = UtilTimestamp.getInstance().getDateCeroHoras(fechaInicio);
		Date nuevoFin = UtilTimestamp.getInstance().getDate24Horas(fechaFin);
		gasto.setFechaInicio(nuevoInicio);
		gasto.setFechaFin(nuevoFin);
		gasto.setEstatus(estatus);
		gasto.queryXFechaViaticos();
		listGastoViaje = session.searchGeneric(gasto);

	}

	public void buscaPapeleria(Date fechaInicio, Date fechaFin, int estatus) throws Exception {
		Papeleria pa = new Papeleria();
		Date nuevoInicio = UtilTimestamp.getInstance().getDateCeroHoras(fechaInicio);
		Date nuevoFin = UtilTimestamp.getInstance().getDate24Horas(fechaFin);
		pa.setFechaInicio(nuevoInicio);
		pa.setFechaFin(nuevoFin);
		pa.setEstatus(estatus);
		pa.queryXFechaPapeleria();
		listPapeleria = session.searchGeneric(pa);
	}

	public void nuevoEstatusVacacion(Vacacion myVacacion) throws Exception {
		myVacacion.setEstatus(Constantes.ESTATUS_DESPACHADO);
		session.saveUpdateGeneric(myVacacion, empleadoActual);
	}

	public void nuevoEstatusPapeleria(Papeleria mypape) throws Exception {
		mypape.setEstatus(Constantes.ESTATUS_DESPACHADO);
		session.saveUpdateGeneric(mypape, empleadoActual);
	}
	
	public void nuevoEstatusViaticos(GastoViaje mygasto) throws Exception {
		mygasto.setEstatus(Constantes.ESTATUS_DESPACHADO);
		session.saveUpdateGeneric(mygasto, empleadoActual);
	}
	public void nuevoEstatusJuntas(Juntas myjunta) throws Exception {
		myjunta.setEstatus(Constantes.ESTATUS_DESPACHADO);
		session.saveUpdateGeneric(myjunta, empleadoActual);
	}

	public List<Papeleria> getListPapeleria() {
		return listPapeleria;
	}

	public void setListPapeleria(List<Papeleria> listPapeleria) {
		this.listPapeleria = listPapeleria;
	}

	public List<Vacacion> getListVacacion() {
		return listVacacion;
	}

	public void setListVacacion(List<Vacacion> listVacacion) {
		this.listVacacion = listVacacion;
	}

	public List<GastoViaje> getListGastoViaje() {
		return listGastoViaje;
	}

	public void setListGastoViaje(List<GastoViaje> listGastoViaje) {
		this.listGastoViaje = listGastoViaje;
	}

	public List<Juntas> getListJuntas() {
		return listJuntas;
	}

	public void setListJuntas(List<Juntas> listJuntas) {
		this.listJuntas = listJuntas;
	}

}
