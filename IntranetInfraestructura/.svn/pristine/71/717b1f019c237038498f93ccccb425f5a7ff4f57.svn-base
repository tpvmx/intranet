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
@Table(name = "tb_papeleria")
public class Papeleria extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_papeleria", sequenceName = "seq_tb_papeleria", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_papeleria", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_papeleria")
	private Long idPapeleria = 0L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea;

	@Column(name = "sku")
	private String sku;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "material")
	private String material;

	@Column(name = "tamano")
	private String tamano;

	@Column(name = "usuacrea")
	private int usucrea;

	@Column(name = "estatus")
	private int estatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_empleado")
	private Empleado num_empleado;

	@Transient
	private Date fechaInicio;
	@Transient
	private Date fechaFin;

	public Papeleria() {
	}

	public Papeleria(Long id) {
		this.idPapeleria = id;
	}

	public Long getIdNoticias() {
		return this.idPapeleria;
	}

	public void setIdPapeleria(Long idPapeleria) {
		this.idPapeleria = idPapeleria;
	}

	@Transient
	@Override
	public Object getId() {
		return idPapeleria;
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
		Papeleria actual = (Papeleria) obj;
		usucrea = actual.getUsucrea();
		feccrea = actual.getFeccrea();
		sku = actual.getSku();
		cantidad = actual.getCantidad();
		material = actual.getMaterial();
		tamano = actual.getTamano();
		estatus = actual.getEstatus();
		num_empleado=actual.getNum_empleado();

		return this;
	}

	public void queryAll() {
		this.queryHql = "FROM " + Papeleria.class.getCanonicalName() + " m " + " where m.estatus = 0 ";
	}

	// consulta fecha
	public void queryXFechaPapeleria() {
		this.queryHql = " FROM " + Papeleria.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = :sta AND m.feccrea BETWEEN :fecha1 AND :fecha2  order by m.feccrea ";
		this.params.put("fecha1", fechaInicio);
		this.params.put("fecha2", fechaFin);
		this.params.put("sta", estatus);
		}
	
	// consultapendientes
	public void queryXPendientesPapeleria() {
		this.queryHql = " FROM " + Papeleria.class.getCanonicalName() + " m "
				+ " WHERE m.estatus = 0  AND m.num_empleado = :empleado ORDER BY m.idPapeleria DESC";
		this.params.put("empleado", num_empleado);

	}



	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getUsucrea() {
		return usucrea;
	}

	public void setUsucrea(int usucrea) {
		this.usucrea = usucrea;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Long getIdPapeleria() {
		return idPapeleria;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
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

	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

}
