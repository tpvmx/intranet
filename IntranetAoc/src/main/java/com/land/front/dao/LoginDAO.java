package com.land.front.dao;

import com.land.back.entities.Empleado;
import com.land.back.entities.Permiso;

@SuppressWarnings("serial")
public class LoginDAO extends DAOComun {

	public LoginDAO(Empleado empleadoActual) throws Exception {
		super(empleadoActual);
	}

	public void doConstantes() throws Exception {
		session.initConstants();
	}

	public Permiso buscaPermisoIP(String ipAddress) throws Exception {

		Permiso per = new Permiso();
		per.setIp(ipAddress);
		per.queryXIP();
		per = session.searchGenericOnly(per);
		return per;
	}

	public Permiso buscaPermisoMac(String macAddress) throws Exception {

		Permiso per = new Permiso();
		per.setMac(macAddress.toUpperCase());
		per.queryXMac();
		per = session.searchGenericOnly(per);
		return per;
	}

	public void iniciaConstantes() throws Exception {
		session.initConstants();
	}

}
