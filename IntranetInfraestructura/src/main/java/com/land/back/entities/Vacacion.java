package com.land.back.entities;

import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_vacacion")
public class Vacacion extends EntidadGenerica {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_vacacion", sequenceName = "seq_tb_vacacion", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_vacacion", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_vacacion")
	private Long id_vacacion = 0L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicial")
	private Date fecha_inicial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_final")
	private Date fecha_final;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_regreso")
	private Date fecha_regreso;

	@Column(name = "dias")
	private Integer dias;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "estatus")
	private Integer estatus = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_empleado")
	private Empleado num_empleado;

	@Transient
	private Date fechaInicio;
	@Transient
	private Date fechaFin;

	@Column(name = "folio")
	private Integer folio;

	@Transient
	private Integer totalDias;

	public Vacacion() {

	}

	@Override
	public Object getId() {
		return id_vacacion;
	}

	@Override
	public String getQueryHql() {
		return queryHql;
	}

	@Override
	public Map<Object, Object> getParametros() {
		return this.params;
	}

	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		Vacacion actual = (Vacacion) obj;
		feccrea = actual.getFeccrea();
		fecha_inicial = actual.getFecha_inicial();
		fecha_final = actual.getFecha_final();
		fecha_regreso = actual.getFecha_regreso();
		dias = actual.getDias();
		observaciones = actual.getObservaciones();
		estatus = actual.getEstatus();
		num_empleado = actual.getNum_empleado();
		folio = actual.getFolio();
		return this;
	}

	public void queryAll() {
		this.queryHql = "FROM " + Vacacion.class.getCanonicalName() + " m " + " where m.estatus = 0 ";
	}

	public void queryXEmpleadoAutirizadas() {
		this.queryHql = " FROM " + Vacacion.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = 1 AND m.num_empleado = :empleado order by m.id_vacacion ";
		this.params.put("empleado", num_empleado);
	}

	public void queryXEmpleadoUltimoSinAutorizar() {
		this.queryHql = " FROM " + Vacacion.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = 0 AND m.num_empleado = :empleado ORDER BY m.id_vacacion DESC ";
		this.params.put("empleado", num_empleado);
	}

	// consulta fecha
	public void queryXFechaVacacion() {
		this.queryHql = " FROM " + Vacacion.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta AND  m.feccrea BETWEEN :fecha1 AND :fecha2  order by m.feccrea ";
		this.params.put("fecha1", fechaInicio);
		this.params.put("fecha2", fechaFin);
		this.params.put("sta", estatus);

	}

	// consultapendientes
	public void queryXPendientes() {
		this.queryHql = " FROM " + Vacacion.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = 0  AND m.num_empleado = :empleado ORDER BY m.id_vacacion DESC";
		this.params.put("empleado", num_empleado);

	}

	public Long getId_vacacion() {
		return id_vacacion;
	}

	public void setId_vacacion(Long id_vacacion) {
		this.id_vacacion = id_vacacion;
	}

	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

	public Date getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(Date fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public Date getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}

	public Date getFecha_regreso() {
		return fecha_regreso;
	}

	public void setFecha_regreso(Date fecha_regreso) {
		this.fecha_regreso = fecha_regreso;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Empleado getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(Empleado num_empleado) {
		this.num_empleado = num_empleado;
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

	public Integer getTotalDias() {

		return totalDias;

	}

	public void setTotalDias(Integer totalDias) {
		this.totalDias = totalDias;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

}
