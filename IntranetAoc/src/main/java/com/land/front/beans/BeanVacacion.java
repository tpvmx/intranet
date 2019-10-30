package com.land.front.beans;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.Empleado;
import com.land.back.entities.Vacacion;
import com.land.front.dao.VacacionDAO;
import com.land.front.util.UtilSesion;

@ManagedBean
@ViewScoped
public class BeanVacacion extends BeanComun {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;
	private VacacionDAO dao;
	private Vacacion myVacacion = new Vacacion();
	private Vacacion myVacacionEdita = new Vacacion();
	private Empleado myEmpleado = new Empleado();
	private Integer totalAcumulado = 0;
	private LinkedHashMap<String, Vacacion> hmAgregadas = new LinkedHashMap<String, Vacacion>();

	@Override
	public void datosIniciales() {
		try {
			dao.buscaVacacion();
			for (Vacacion vac : getListVacaciones()) {
				totalAcumulado += vac.getDias();
			}
			dao.buscaPendientesVacacion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			empleadoActual = beanLogin.getPermisoActual().getEmpleado();
			dao = new VacacionDAO(empleadoActual);
			datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onEditaVacacion(){
		try {
			myVacacion =myVacacionEdita;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminaVacacion(Vacacion myVacacion) {
		try {
			dao.eliminaVacacion(myVacacion);
			datosIniciales();
			alertInfo(getTexto("beanVacacionEliminarExitoso"));
		} catch (Exception e) {
			alertError(getTexto("beanVacacionEliminarFallido"));
			e.printStackTrace();
		}
	}

	public void guardaVacacion() {
		try {

			if (myVacacion.getFecha_inicial() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("vacacionFechaInicial")));
				return;
			}
			if (myVacacion.getFecha_final() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("vacacionFechaFinal")));
				return;
			}
			if (myVacacion.getDias() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("vacacionDias")));
				return;
			}
			if (myVacacion.getFecha_regreso() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("vacacionFechaRegreso")));
				return;
			}
			myVacacion.setNum_empleado(empleadoActual);

			dao.guardaVacacion(myVacacion);
			limpiaCampos();
			alertInfo(getTexto("dicMsgExitoso"));
		} catch (Exception e) {
			alertError(getTexto("beanVacacionERROR"));
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void vacacionReporte() {

		try {

			Vacacion vacUltima = dao.buscaUltimaVacacion();
			if (vacUltima == null) {
				alertAlerta("No existe una petición de vacaciones pendiente.");
				return;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			List listaDetails = new ArrayList();

			for (Vacacion vacacion : getListVacaciones()) {

				HashMap<String, Object> hm = new HashMap<String, Object>(0);

				hm.put("fechainicio", formatFecha.format(vacacion.getFecha_inicial()));
				hm.put("fechafin", formatFecha.format(vacacion.getFecha_final()));
				hm.put("dias", vacacion.getDias().toString());

				listaDetails.add(hm);
			}

			params.put("fechaactual", formatFecha.format(vacUltima.getFeccrea()));
			params.put("no.empleado", vacUltima.getNum_empleado().getNum_empleado().toString());
			params.put("nombre", vacUltima.getNum_empleado().getNombre());
			params.put("apepaterno", vacUltima.getNum_empleado().getApellido_paterno());
			params.put("apematerno", vacUltima.getNum_empleado().getApellido_materno());
			params.put("departamento", vacUltima.getNum_empleado().getDepartamento());
			params.put("puesto", vacUltima.getNum_empleado().getPuesto());
			params.put("fechaingreso", formatFecha.format(vacUltima.getNum_empleado().getFecha_ingreso()));
			params.put("fechainicial", formatFecha.format(vacUltima.getFecha_inicial()));
			params.put("fechafinal", formatFecha.format(vacUltima.getFecha_final()));
			params.put("fecharegreso", formatFecha.format(vacUltima.getFecha_regreso()));
			params.put("totaldias", vacUltima.getDias().toString());
			params.put("observaciones", vacUltima.getObservaciones() == null ? "" : vacUltima.getObservaciones());
			params.put("empleado", vacUltima.getNum_empleado().getNombreCompleto());
			if (vacUltima.getNum_empleado().getJefe() == null)
				params.put("aprobado", "");
			else
				params.put("aprobado", vacUltima.getNum_empleado().getJefe().getNombreCompleto());

			Empleado rh = dao.buscaEncargadoRH();
			if(rh != null)
				params.put("revisado", rh.getNombreCompleto());
			params.put("foliousoarea", vacUltima.getFolio().toString());

			params.put("imagenaoc",
					new File(UtilSesion.getExternalContext().getRealPath("/resources/images/aoc_reporte.jpg")));

			// Para Imprimir PDF
			lanzaReportePDF("/resources/reportes/vacaciones.jasper", params, listaDetails, "Vacaciones");
			limpiaCampos();
		} catch (Exception e) {
			e.printStackTrace();
			alertError("Error al construir el reporte");
		}

	}

	public void limpiaCampos() {

		datosIniciales();
		myVacacion = new Vacacion();
	}

	public VacacionDAO getDao() {
		return dao;
	}

	public void setDao(VacacionDAO dao) {
		this.dao = dao;
	}

	public Vacacion getMyVacacion() {
		return myVacacion;
	}

	public void setMyVacacion(Vacacion myVacacion) {
		this.myVacacion = myVacacion;
	}

	public Empleado getMyEmpleado() {
		return myEmpleado;
	}

	public void setMyEmpleado(Empleado myEmpleado) {
		this.myEmpleado = myEmpleado;
	}

	public List<Vacacion> getListVacacion() {
		return new ArrayList<Vacacion>(hmAgregadas.values());
	}

	public List<Vacacion> getListVacaciones() {
		return dao.getListVacacion();
	}

	public List<Vacacion> getListVaca() {
		return dao.getListVaca();
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return formatFecha;
	}

	public Integer getTotalAcumulado() {
		return totalAcumulado;
	}

	public void setTotalAcumulado(Integer totalAcumulado) {
		this.totalAcumulado = totalAcumulado;
	}

	public Vacacion getMyVacacionEdita() {
		return myVacacionEdita;
	}

	public void setMyVacacionEdita(Vacacion myVacacionEdita) {
		this.myVacacionEdita = myVacacionEdita;
	}

}
