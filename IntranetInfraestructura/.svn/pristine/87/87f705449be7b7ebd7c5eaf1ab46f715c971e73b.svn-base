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
import com.land.back.util.Constantes;

@Entity
@Table(name = "tb_gastos_viaje")
public class GastoViaje extends EntidadGenerica {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_gastos_viaje", sequenceName = "seq_tb_gastos_viaje", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_gastos_viaje", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_gastoviaje")
	private Long id_gastoviaje = 0L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_solicitud")
	private Date fecha_solicitud = new Date();

	@Column(name = "nivel")
	private Integer nivel;

	@Column(name = "tableta")
	private Integer tableta;

	@Column(name = "portableta")
	private Integer portableta;

	@Column(name = "telefono")
	private Integer telefono;

	@Column(name = "portelefono")
	private Integer portelefono;

	@Column(name = "monitor")
	private Integer monitor;

	@Column(name = "pormonitor")
	private Integer pormonitor;

	@Column(name = "destino")
	private String destino;

	@Column(name = "motivo")
	private String motivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicioviaje")
	private Date fecha_inicioviaje;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_terminoviaje")
	private Date fecha_terminoviaje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_empleado")
	private Empleado numEmpleado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea;

	@Column(name = "estatus")
	private int estatus = 0;

	@Transient
	private Date fechaInicio;
	@Transient
	private Date fechaFin;

	@Column(name = "folio")
	private Integer folio;

	public void queryAll() {
		this.queryHql = "FROM " + GastoViaje.class.getCanonicalName() + " m ";
	}

	@Override
	public Object getId() {
		return id_gastoviaje;
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
		GastoViaje actual = (GastoViaje) obj;
		fecha_solicitud = actual.getFecha_solicitud();
		nivel = actual.getNivel();
		tableta = actual.getTableta();
		portableta = actual.getPortableta();
		telefono = actual.getTelefono();
		portelefono = actual.getPortelefono();
		monitor = actual.getMonitor();
		pormonitor = actual.getPormonitor();
		destino = actual.getDestino();
		motivo = actual.getMotivo();
		fecha_inicioviaje = actual.getFecha_inicioviaje();
		fecha_terminoviaje = actual.getFecha_terminoviaje();
		numEmpleado = actual.getNumEmpleado();
		folio = actual.getFolio();
		return this;
	}

	// consulta fecha
	public void queryXFechaViaticos() {
		this.queryHql = " FROM " + GastoViaje.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta AND m.feccrea BETWEEN :fecha1 AND :fecha2  order by m.feccrea ";
		this.params.put("fecha1", fechaInicio);
		this.params.put("fecha2", fechaFin);
		this.params.put("sta", estatus);

	}

	public void queryXGastoViejaUltimoPendiente() {
		this.queryHql = " FROM " + GastoViaje.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta ORDER BY m.id_gastoviaje DESC";
		this.params.put("sta", Constantes.INACTIVO);
	}

	public void queryPendientes() {
		this.queryHql = " FROM " + GastoViaje.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta  AND m.numEmpleado = :empleado order by m.id_gastoviaje DESC";
		this.params.put("sta", Constantes.INACTIVO);
		this.params.put("empleado", numEmpleado);
	}

	public Long getId_gastoviaje() {
		return id_gastoviaje;
	}

	public void setId_gastoviaje(Long id_gastoviaje) {
		this.id_gastoviaje = id_gastoviaje;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getTableta() {
		return tableta;
	}

	public void setTableta(Integer tableta) {
		this.tableta = tableta;
	}

	public Integer getPortableta() {
		return portableta;
	}

	public void setPortableta(Integer portableta) {
		this.portableta = portableta;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getPortelefono() {
		return portelefono;
	}

	public void setPortelefono(Integer portelefono) {
		this.portelefono = portelefono;
	}

	public Integer getMonitor() {
		return monitor;
	}

	public void setMonitor(Integer monitor) {
		this.monitor = monitor;
	}

	public Integer getPormonitor() {
		return pormonitor;
	}

	public void setPormonitor(Integer pormonitor) {
		this.pormonitor = pormonitor;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getFecha_inicioviaje() {
		return fecha_inicioviaje;
	}

	public void setFecha_inicioviaje(Date fecha_inicioviaje) {
		this.fecha_inicioviaje = fecha_inicioviaje;
	}

	public Date getFecha_terminoviaje() {
		return fecha_terminoviaje;
	}

	public void setFecha_terminoviaje(Date fecha_terminoviaje) {
		this.fecha_terminoviaje = fecha_terminoviaje;
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

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

	public Empleado getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(Empleado numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

}
