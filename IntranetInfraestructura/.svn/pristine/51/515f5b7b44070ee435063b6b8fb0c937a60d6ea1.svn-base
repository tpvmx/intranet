package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_permiso")
public class Permiso extends EntidadGenerica {

	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_permiso")
	private Long idPermiso = 0L;

	@Column(name = "mac")
	private String mac;

	@Column(name = "ip")
	private String ip;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_empleado")
	private Empleado empleado;

	@Column(name = "estatus")
	private Integer estatus;

	@Column(name = "admin")
	private Integer admin;

	@Transient
	private boolean seleccion;

	public Permiso() {

	}

	public void queryAll() {
		this.queryHql = "FROM " + Permiso.class.getCanonicalName() + " m ";
	}

	public void queryXMac() {
		this.queryHql = "FROM " + Permiso.class.getCanonicalName() + " m WHERE m.mac = :mac AND m.estatus = 1 ";
		this.params.put("mac", mac);
	}

	public void queryXIP() {
		this.queryHql = "FROM " + Permiso.class.getCanonicalName() + " m WHERE m.ip = :ip AND m.estatus = 1";
		this.params.put("ip", ip);
	}

	@Override
	public Object getId() {
		return idPermiso;
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
		return this;
	}

	public Long getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

}
