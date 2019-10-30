package com.land.back.jpa.tx.config;

import java.util.HashMap;

import com.land.back.entities.Empleado;
import com.land.back.jpa.SesionJPANotClose;

/**
 * @author LuisGlz
 */

public interface TransaccionLauncher<T extends TransaccionDTO> {
	// public T ejecutaTransaccion(SessionHBNotClose sesion) throws Exception;
	public T ejecutaTransaccion(SesionJPANotClose sesion, Empleado empleadoActual) throws Exception;
	public void inicializaParametros(HashMap<String, Object> hmParametrosTX);
}