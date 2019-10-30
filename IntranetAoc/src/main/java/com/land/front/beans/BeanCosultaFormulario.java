package com.land.front.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.GastoViaje;
import com.land.back.entities.Juntas;
import com.land.back.entities.Papeleria;
import com.land.back.entities.Vacacion;
import com.land.back.util.Constantes;
import com.land.front.dao.ConsultaFormDAO;

@ViewScoped
@ManagedBean
public class BeanCosultaFormulario extends BeanComun {

	/**
	 * Yare
	 */

	private static final long serialVersionUID = 1L;

	private Vacacion myvacacion = new Vacacion();
	private Papeleria myPapeleria = new Papeleria();
	private GastoViaje myGastoViaje = new GastoViaje();
	private Juntas myJunta = new Juntas();
	private String consult;

	private Date fechaInicio;
	private Date fechaFin;

	private ConsultaFormDAO dao;

	private Boolean edita = false;
	private Boolean edito = false;
	private Boolean editaviaje = false;
	private Boolean editajuntas = false;

	private Boolean despachado = false;

	@PostConstruct
	public void init() {

		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			dao = new ConsultaFormDAO(beanLogin.getPermisoActual().getEmpleado());
			datosIniciales();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void datosIniciales() {

		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void consulta() {
		try {
			if (consult == null) {
				alertAlerta(getTexto("consulRequerida"));
				return;
			}
			if (fechaInicio == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("consulInicial")));
				return;
			}
			if (fechaFin == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("consulFinal")));
				return;
			}

			if (consult.equals("papeleria")) {
				int estatus = 0;
				if (despachado) {
					estatus = Constantes.ESTATUS_DESPACHADO;

				} else {
					estatus = Constantes.ESTATUS_SINDESPACHAR;
				}

				dao.buscaPapeleria(fechaInicio, fechaFin, estatus);

				edita = false;
				editaviaje=false;
				if (getListPapeleria().isEmpty()) {
					alertAlerta(getTexto("consulErrorDatos"));
					edita = false;
					editaviaje=false;
				}
				edito = true;
				return;

			}
			if (consult.equals("viaticos")) {
				int estatus = 0;
				if (despachado) {
					estatus = Constantes.ESTATUS_DESPACHADO;

				} else {
					estatus = Constantes.ESTATUS_SINDESPACHAR;
				}

				dao.buscaViaticos(fechaInicio, fechaFin, estatus);

				edita = false;
				edito = false;
				if (getListGastoViaje().isEmpty()) {
					alertAlerta(getTexto("consulErrorDatosV"));
					edita = false;
					edito = false;
				}
				editaviaje = true;
				return;

			}
			int estatu = 0;
			if (consult.equals("vacaciones")) {
				if (despachado) {
					estatu = Constantes.ESTATUS_DESPACHADO;

				} else {
					estatu = Constantes.ESTATUS_SINDESPACHAR;

				}
				// myvacacion.setFechaInicio(fechaInicio);
				// myvacacion.setFechaFin(fechaFin);
				dao.buscaVacacion(fechaInicio, fechaFin, estatu);
				edito = false;
				editaviaje=false;
				
				if (getListVacacion().isEmpty()) {
					alertAlerta(getTexto("consulErrorDatosVaca"));
					edito = false;
					editaviaje=false;
				}
				edita = true;
				return;
			}
			if (consult.equals("juntas")) {
				if (despachado) {
					estatu = Constantes.ESTATUS_DESPACHADO;

				} else {
					estatu = Constantes.ESTATUS_SINDESPACHAR;

				}
			
				dao.buscaJuntas(fechaInicio, fechaFin, estatu);
				edito = false;
				editaviaje=false;
				edita=false;
				
				if (getListJuntas().isEmpty()) {
					alertAlerta(getTexto("consulErrorJuntas"));
					edito = false;
					editaviaje=false;
					edita=false;
				}
				editajuntas = true;
				return;
			}
			limpiaCampos(Boolean.TRUE);

		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta("error");
		}
	}

	public void editarEstatus() {
		try {
			if (consult.equals("vacaciones")) {
				dao.nuevoEstatusVacacion(myvacacion);
				consulta();
			} else if (consult.equals("viaticos")) {
				dao.nuevoEstatusViaticos(myGastoViaje);
				consulta();
			} else if (consult.equals("juntas")) {
				dao.nuevoEstatusJuntas(myJunta);
				consulta();
			} else {
				dao.nuevoEstatusPapeleria(myPapeleria);
				consulta();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta(getTexto("errorDatos"));
		}
	}

	public void limpiaCampos(Boolean all) {

		consult = "";
		edita = false;
		edito = false;
		editaviaje = false;
		editajuntas=false;
		fechaInicio = null;
		fechaFin = null;

	}

	public void limpiarCampos() {
		try {

			limpiaCampos(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vacacion getMyvacacion() {
		return myvacacion;
	}

	public void setMyvacacion(Vacacion myvacacion) {
		this.myvacacion = myvacacion;
	}

	public String getConsult() {
		return consult;
	}

	public void setConsult(String consult) {
		this.consult = consult;
	}

	public List<Vacacion> getListVacacion() {
		return dao.getListVacacion();
	}

	public List<Papeleria> getListPapeleria() {
		return dao.getListPapeleria();
	}

	public List<GastoViaje> getListGastoViaje() {
		return dao.getListGastoViaje();
	}
	public List<Juntas> getListJuntas() {
		return dao.getListJuntas();
	}

	public Boolean getEdita() {
		return edita;
	}

	public void setEdita(Boolean edita) {
		this.edita = edita;
	}

	public Boolean getEdito() {
		return edito;
	}

	public void setEdito(Boolean edito) {
		this.edito = edito;
	}

	public Papeleria getMyPapeleria() {
		return myPapeleria;
	}

	public void setMyPapeleria(Papeleria myPapeleria) {
		this.myPapeleria = myPapeleria;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Boolean getDespachado() {
		return despachado;
	}

	public void setDespachado(Boolean despachado) {
		this.despachado = despachado;
	}

	public GastoViaje getMyGastoViaje() {
		return myGastoViaje;
	}

	public void setMyGastoViaje(GastoViaje myGastoViaje) {
		this.myGastoViaje = myGastoViaje;
	}

	public Boolean getEditaviaje() {
		return editaviaje;
	}

	public void setEditaviaje(Boolean editaviaje) {
		this.editaviaje = editaviaje;
	}

	public Boolean getEditajuntas() {
		return editajuntas;
	}

	public void setEditajuntas(Boolean editajuntas) {
		this.editajuntas = editajuntas;
	}

	public Juntas getMyJunta() {
		return myJunta;
	}

	public void setMyJunta(Juntas myJunta) {
		this.myJunta = myJunta;
	}

}