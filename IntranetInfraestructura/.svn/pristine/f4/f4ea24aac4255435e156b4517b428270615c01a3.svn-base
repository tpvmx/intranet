package com.land.back.tx;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.land.back.entities.Empleado;
import com.land.back.entities.Folio;
import com.land.back.entities.Vacacion;
import com.land.back.jpa.SesionJPANotClose;
import com.land.back.jpa.tx.config.TransaccionComun;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.util.Constantes;

public class TxVacacion extends TransaccionComun {

	private Vacacion myVacacion;

	@Override
	public TransaccionDTO ejecutaTransaccion(SesionJPANotClose sesion, Empleado empleadoActual) throws Exception {
		this.session = sesion;
		myVacacion.setFolio(getFolio());
		sesion.saveUpdateGeneric(myVacacion, empleadoActual);

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
		myVacacion = (Vacacion) hmParametrosTX.get("vac");

	}

}
