package com.land.front.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.Empleado;
import com.land.back.entities.Papeleria;
import com.land.front.dao.PapeleriaDAO;

@ViewScoped
@ManagedBean
public class BeanPapeleria extends BeanComun {

	/**
	 * Yare
	 */

	private static final long serialVersionUID = 1L;
	private String sku;
	private String material;
	private String tamano;
	private Integer cantidad;
	private Date fecha;
	private Papeleria myPapeleria = new Papeleria();
	private Empleado myEmpleado = new Empleado();

	private PapeleriaDAO dao;

	public void guardaPapeleria() {
		try {

			if (fecha == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("papeFecha")));
				return;
			}

			if (sku.length() == 0) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("sku")));
				return;
			}
			if (cantidad == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("cantidad")));
				return;
			}
			if (material.length() == 0) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("material")));
				return;
			}
			if (tamano.length() == 0) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("tamano")));
				return;
			}

			myPapeleria.setFeccrea(fecha);
			myPapeleria.setSku(sku);
			myPapeleria.setCantidad(cantidad);
			myPapeleria.setMaterial(material);
			myPapeleria.setTamano(tamano);
			myPapeleria.setNum_empleado(empleadoActual);
			dao.guardaPapeleria(myPapeleria);
			alertInfo(getTexto("dicMsgExitoso"));

			limpiaCampos();
			datosIniciales();
			// datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("errorguar"));
		}
	}
	
	public void eliminaPapeleria(Papeleria myPapeleria) {
		try {
			dao.eliminaPapeleria(myPapeleria);
			datosIniciales();
			alertInfo(getTexto("beanPapeleriaEliminarExitoso"));
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("beanPapeleriaEliminarFallido"));
		}
	}

	public void limpiaCampos() {
		myPapeleria = new Papeleria();
		this.sku = "";
		this.cantidad = null;
		this.material = "";
		this.tamano = "";
		this.fecha = null;

	}

	@PostConstruct
	public void init() {

		try {
			datosIniciales();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void datosIniciales() {
		BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
		try {
			empleadoActual = beanLogin.getPermisoActual().getEmpleado();
			dao = new PapeleriaDAO(beanLogin.getPermisoActual().getEmpleado());
			dao.buscaPendientesPapeleria();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Papeleria getMyPapeleria() {
		return myPapeleria;
	}

	public void setMyPapeleria(Papeleria myPapeleria) {
		this.myPapeleria = myPapeleria;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Empleado getMyEmpleado() {
		return myEmpleado;
	}

	public void setMyEmpleado(Empleado myEmpleado) {
		this.myEmpleado = myEmpleado;
	}
	
	public List<Papeleria> getListPape() {
		return dao.getListPape();
	}

}