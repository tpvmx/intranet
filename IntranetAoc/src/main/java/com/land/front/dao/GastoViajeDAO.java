package com.land.front.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.GastoDetalle;
import com.land.back.entities.GastoViaje;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.jpa.tx.util.NamesTX;
import com.land.back.nomapping.DiaDinamico;
import com.land.back.util.Constantes;

public class GastoViajeDAO extends DAOComun {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;
	private List<GastoViaje> listGastosInactivos = new ArrayList<GastoViaje>();

	public GastoViajeDAO(Empleado empleadoActual) throws Exception {
		super(empleadoActual);
		// TODO Auto-generated constructor stub
	}

	public void guardaGasto(GastoViaje myGastoViaje) throws Exception {
		myGastoViaje.setFeccrea(new Date());
		myGastoViaje.setEstatus(0);
		session.saveUpdateGeneric(myGastoViaje, empleadoActual);
	}
	
	public Boolean eliminaGastoViaje(GastoViaje myGastoViaje) throws Exception {
		LinkedHashMap<String, Object> hmParams = new LinkedHashMap<String, Object>();
		hmParams.put("myGastoViaje", myGastoViaje);
		TransaccionDTO dto = new TransaccionDTO();
		dto.setClassTx(NamesTX.TxEliminaViatico);
		dto.setHmParametrosTx(hmParams);
		dto = session.ejecutaTransaccion(dto, empleadoActual);
		return dto.isComplete();
	}

	public void guardaTabla(List<GastoDetalle> listGastos) throws Exception {
		GastoDetalle gas = new GastoDetalle();
		String cadenaFinal = "";
		for (GastoDetalle var : listGastos) {
			for (DiaDinamico dia : var.getListDias()) {
				cadenaFinal += dia.getDia() + "," + dia.getValor() + "|";
			}
			gas.setConcepto(var.getConcepto());
			gas.setOrden(var.getOrden());
			gas.setDias(cadenaFinal);
			session.saveUpdateGeneric(gas, empleadoActual);
		}
	}

	public Empleado buscaEncargadoRH() throws Exception {
		Empleado emp = new Empleado();
		emp.setNum_empleado(new Long(Constantes.NUM_EMPLEADO_RH));
		emp = session.searchGenericForId(emp);
		return emp;
	}

	public Boolean guardaViatico(GastoViaje myGastoViaje, List<GastoDetalle> listGastos) throws Exception {
		LinkedHashMap<String, Object> hmParams = new LinkedHashMap<String, Object>();
		hmParams.put("gasto", myGastoViaje);
		hmParams.put("list", listGastos);
		TransaccionDTO dto = new TransaccionDTO();
		dto.setClassTx(NamesTX.TxGuardaViatico);
		dto.setHmParametrosTx(hmParams);
		dto = session.ejecutaTransaccion(dto, empleadoActual);
		return dto.isComplete();
	}

	public GastoViaje buscaUltimoGastoViaje() throws Exception {
		GastoViaje gasto = new GastoViaje();
		gasto.queryXGastoViejaUltimoPendiente();
		return session.searchGenericOnly(gasto);
	}
	
	public void buscaXEstatus() throws Exception {
		GastoViaje gv= new GastoViaje();
		gv.setNumEmpleado(empleadoActual);
		gv.queryPendientes();
		listGastosInactivos = session.searchGeneric(gv);
	}

	public List<GastoDetalle> buscaDetallesGasto(GastoViaje gastoViaje) throws Exception {
		GastoDetalle det = new GastoDetalle();
		det.setGastoviaje(gastoViaje);
		det.queryXGastoViaje();
		return session.searchGeneric(det);
	}

	public List<GastoViaje> getListGastosInactivos() {
		return listGastosInactivos;
	}

	public void setListGastosInactivos(List<GastoViaje> listGastosInactivos) {
		this.listGastosInactivos = listGastosInactivos;
	}

}
