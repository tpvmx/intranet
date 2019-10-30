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
@Table(name = "tb_juntas")
public class Juntas extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_juntas", sequenceName = "seq_tb_juntas", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_juntas", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_junta")
	private Long idJunta = 0L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechajuntainicio")
	private Date fechaJuntaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechajuntafin")
	private Date fechaJuntaFin;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_empleado")
	private Empleado numEmpleado;

	@Column(name = "estatus")
	private int estatus = 0;

	@Transient
	private Date fechaInicio;
	@Transient
	private Date fechaFin;

	public Juntas() {
	}

	public Juntas(Long id) {
		this.idJunta = id;
	}

	public Long getIdJunta() {
		return this.idJunta;
	}

	public void setIdJunta(Long idJunta) {
		this.idJunta = idJunta;
	}

	@Transient
	@Override
	public Object getId() {
		return idJunta;
	}

	@Transient
	@Override
	public String getQueryHql() {
		return this.queryHql;
	}

	@Transient
	@Override
	public Map<Object, Object> getParametros() {
		return this.params;
	}

	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		Juntas actual = (Juntas) obj;
		numEmpleado = actual.getNumEmpleado();
		feccrea = actual.getFeccrea();
		fechaJuntaInicio = actual.getFechaJuntaInicio();
		fechaJuntaFin = actual.getFechaJuntaFin();
		descripcion = actual.getDescripcion();

		estatus = actual.getEstatus();

		return this;
	}

	public void queryAll() {
		this.queryHql = "FROM " + Juntas.class.getCanonicalName() + " m " + " where m.estatus = 0 ";
	}

	// consulta fecha
	public void queryXFechaJuntas() {
		this.queryHql = " FROM " + Juntas.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta AND (m.feccrea BETWEEN :fecha1 AND :fecha2 OR m.fechaJuntaInicio BETWEEN :fecha1 AND :fecha2 ) order by m.feccrea ";
		this.params.put("fecha1", fechaInicio);
		this.params.put("fecha2", fechaFin);
		this.params.put("sta", estatus);

	}

	// consultapendientes
	public void queryXPendienteJunta() {
		this.queryHql = " FROM " + Juntas.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = 0  AND m.numEmpleado = :empleado ORDER BY m.feccrea DESC ";
		this.params.put("empleado", numEmpleado);

	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
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

	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Empleado getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(Empleado numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

}
