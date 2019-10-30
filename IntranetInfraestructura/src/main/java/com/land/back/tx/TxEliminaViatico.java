package com.land.back.tx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.land.back.entities.Empleado;
import com.land.back.entities.GastoDetalle;
import com.land.back.entities.GastoViaje;
import com.land.back.jpa.SesionJPANotClose;
import com.land.back.jpa.tx.config.TransaccionComun;
import com.land.back.jpa.tx.config.TransaccionDTO;

public class TxEliminaViatico  extends TransaccionComun{
	private GastoViaje myGastoViaje = new GastoViaje();
	private GastoDetalle myGastoDetalle = new GastoDetalle();

	@Override
	public TransaccionDTO ejecutaTransaccion(SesionJPANotClose sesion, Empleado empleadoActual) throws Exception {
		session = sesion;
		List<GastoDetalle> ListGastoDetalle = new ArrayList<GastoDetalle>();
		myGastoDetalle.setGastoviaje(myGastoViaje);
		myGastoDetalle.queryXGastoViaje();
		ListGastoDetalle= session.searchGeneric(myGastoDetalle);
		for(GastoDetalle var: ListGastoDetalle) {
			sesion.removeGeneric(var);
		}
		sesion.removeGeneric(myGastoViaje);
		
		return dtoResponse;
	}

	@Override
	public void inicializaParametros(HashMap<String, Object> hmParametrosTX) {
		myGastoViaje = (GastoViaje)hmParametrosTX.get("myGastoViaje");
	}

}
