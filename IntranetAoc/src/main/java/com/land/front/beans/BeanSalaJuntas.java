package com.land.front.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.Empleado;
import com.land.back.entities.Juntas;
import com.land.front.dao.JuntasDAO;

@ViewScoped
@ManagedBean
public class BeanSalaJuntas extends BeanComun {

	/**
	 * Yare
	 */

	private static final long serialVersionUID = 1L;
	private String descripcion;
	
	private Date fechaJuntaInicio;
	private Date fechaJuntaFin;
	private Juntas myJunta = new Juntas();
	private Empleado myEmpleado = new Empleado();

	private JuntasDAO dao;

	public void guardaJunta() {
		try {

			if (fechaJuntaInicio == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("fechajuntaInicio")));
				return;
			}
			if (fechaJuntaFin == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("fechajuntaFin")));
				return;
			}

			if (descripcion.length() == 0) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("descripcion")));
				return;
			}
			
			

			myJunta.setFechaJuntaInicio(fechaJuntaInicio);
			myJunta.setFechaJuntaFin(fechaJuntaFin);
			myJunta.setDescripcion(descripcion);
		   
			
			dao.guardaJunta(myJunta);
			alertInfo(getTexto("dicMsgExitoso"));
         
			
			datosIniciales();
			limpiaCampos();
			// datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("errorguar"));
		}
	}
	
	public void eliminaJunta(Juntas myJunta) {
		try {
			dao.eliminaJuntas(myJunta);
			datosIniciales();
			alertInfo(getTexto("beanSalaJuntasEliminarExitoso"));
		} catch (Exception e) {
			alertError(getTexto("beanSalaJuntasEliminarFallido"));
			e.printStackTrace();
		}
	}

	public void limpiaCampos() {
		myJunta = new Juntas();
		myEmpleado=new Empleado();
		this.descripcion = "";
		this.fechaJuntaInicio = null;
		this.fechaJuntaFin = null;
		

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
			dao = new JuntasDAO(beanLogin.getPermisoActual().getEmpleado());
			dao.buscaPendientesJuntas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	



	public Juntas getMyJuntas() {
		return myJunta;
	}

	public void setMyJuntas(Juntas myJuntas) {
		this.myJunta = myJuntas;
	}

	
	public Empleado getMyEmpleado() {
		return myEmpleado;
	}

	public void setMyEmpleado(Empleado myEmpleado) {
		this.myEmpleado = myEmpleado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Juntas getMyJunta() {
		return myJunta;
	}

	public void setMyJunta(Juntas myJunta) {
		this.myJunta = myJunta;
	}

	public Date getFechaJuntaInicio() {
		return fechaJuntaInicio;
	}

	public void setFechaJuntaInicio(Date fechaJuntaInicio) {
		this.fechaJuntaInicio = fechaJuntaInicio;
	}

	public Date getFechaJuntaFin() {
		return fechaJuntaFin;
	}

	public void setFechaJuntaFin(Date fechaJuntaFin) {
		this.fechaJuntaFin = fechaJuntaFin;
	}

	public List<Juntas> getListJunta() {
		return dao.getListJunta();
	}


	

}