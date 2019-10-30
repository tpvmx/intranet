package com.land.back.tx;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Folio;
import com.land.back.entities.GastoDetalle;
import com.land.back.entities.GastoViaje;
import com.land.back.jpa.SesionJPANotClose;
import com.land.back.jpa.tx.config.TransaccionComun;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.nomapping.DiaDinamico;
import com.land.back.util.Constantes;

public class TxGuardaViatico extends TransaccionComun {
	GastoViaje myGastoViaje;
	List<GastoDetalle> listGastos;

	@Override
	public TransaccionDTO ejecutaTransaccion(SesionJPANotClose sesion, Empleado empleadoActual) throws Exception {
		// GUARDAMOS GASTO VIAJE
		this.session = sesion;
		myGastoViaje.setFeccrea(new Date());
		myGastoViaje.setEstatus(0);
		myGastoViaje.setFolio(getFolio());
		sesion.saveUpdateGeneric(myGastoViaje, empleadoActual);

		// GUARDAMOS GASTO DETALLE
		for (GastoDetalle var : listGastos) {
			GastoDetalle gas = new GastoDetalle();
			String cadenaFinal = "";
			for (DiaDinamico dia : var.getListDias()) {
				cadenaFinal += dia.getDia() + "," + dia.getValor() + "|";
			}
			gas.setConcepto(var.getConcepto());
			gas.setOrden(var.getOrden());
			gas.setDias(cadenaFinal);
			gas.setGastoviaje(myGastoViaje);
			sesion.saveUpdateGeneric(gas, empleadoActual);
		}
		return dtoResponse;
	}

	private int getFolio() throws Exception {
		Folio fol = new Folio();
		fol.setReferencia(Constantes.FOLIOS_FORMULARIO);
		fol.queryUltimo();
		fol = session.searchGenericOnly(fol);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int anioActual = cal.get(Calendar.YEAR);
		int folioNuevo = 0;
		if (anioActual == fol.getAnio().intValue())
			folioNuevo = fol.getFolio().intValue() + 1;
		else
			folioNuevo = 1;
		fol.setAnio(anioActual);
		fol.setFolio(folioNuevo);
		session.saveUpdateGeneric(fol, empleadoActual);

		String folio = "" + anioActual + "" + folioNuevo;
		return Integer.parseInt(folio);
	}

	@Override
	public void inicializaParametros(HashMap<String, Object> hmParametrosTX) {
		myGastoViaje = (GastoViaje) hmParametrosTX.get("gasto");
		listGastos = (List<GastoDetalle>) hmParametrosTX.get("list");

	}

}
