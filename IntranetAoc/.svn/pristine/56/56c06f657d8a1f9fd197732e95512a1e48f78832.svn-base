package com.land.front.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Vacacion;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.jpa.tx.util.NamesTX;
import com.land.back.util.Constantes;

public class VacacionDAO extends DAOComun {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;
	private List<Vacacion> listVacacion;
	private List<Vacacion> listVaca;

	public VacacionDAO(Empleado empleadoActual) throws Exception {
		super(empleadoActual);
		// TODO Auto-generated constructor stub
	}

	public void buscaVacacion() throws Exception {
		Vacacion va = new Vacacion();
		va.setNum_empleado(empleadoActual);
		va.queryXEmpleadoAutirizadas();
		listVacacion = session.searchGeneric(va);
	}

	public void buscaPendientesVacacion() throws Exception {
		Vacacion va = new Vacacion();
		va.setNum_empleado(empleadoActual);
		va.queryXPendientes();
		listVaca = session.searchGeneric(va);
	}
	
	public void eliminaVacacion(Vacacion myVacacion)throws Exception {
		session.removeGeneric(myVacacion);
	}

	public void guardaVacacion(Vacacion myVacacion) throws Exception {
		LinkedHashMap<String, Object> hmParams = new LinkedHashMap<String, Object>();
		hmParams.put("vac", myVacacion);
		TransaccionDTO dto = new TransaccionDTO();
		dto.setClassTx(NamesTX.TxVacacion);
		dto.setHmParametrosTx(hmParams);
		dto = session.ejecutaTransaccion(dto, empleadoActual);
	}

	public Empleado buscaEncargadoRH() throws Exception {
		Empleado emp = new Empleado();
		emp.setNum_empleado(new Long(Constantes.NUM_EMPLEADO_RH));
		emp = session.searchGenericForId(emp);

		return emp;
	}

	public List<Vacacion> getListVacacion() {
		return listVacacion;
	}

	public void setListVacacion(List<Vacacion> listVacacion) {
		this.listVacacion = listVacacion;
	}

	public Vacacion buscaUltimaVacacion() throws Exception {
		Vacacion vac = new Vacacion();
		vac.setNum_empleado(empleadoActual);
		vac.queryXEmpleadoUltimoSinAutorizar();
		vac = session.searchGenericOnly(vac);
		return vac;
	}

	public List<Vacacion> getListVaca() {
		return listVaca;
	}

	public void setListVaca(List<Vacacion> listVaca) {
		this.listVaca = listVaca;
	}

}
