package com.land.back.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_empleado")
public class Empleado extends EntidadGenerica {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_empleado")
	private Long num_empleado = 0L;

	@Column(name = "nombre_empleado")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellido_paterno;

	@Column(name = "apellido_materno")
	private String apellido_materno;

	@Column(name = "departamento")
	private String departamento;

	@Column(name = "puesto")
	private String puesto;

	@Column(name = "jerarquia")
	private Integer jerarquia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ingreso")
	private Date fecha_ingreso;

	@Column(name = "mac_address")
	private String mac_address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numempleado_jefe")
	private Empleado jefe;
	
	@Column(name = "tarjeta")
	private String tarjeta;

	@Column(name = "banco")
	private String banco;

	@Column(name = "clabe")
	private Long clabe;

	@Column(name = "cuenta")
	private Long cuenta;

	public Empleado() {

	}

	public Empleado(Long num_empleado) {
		this.num_empleado = num_empleado;
	}

	public void queryAll() {
		this.queryHql = "FROM " + Empleado.class.getCanonicalName() + " m ";
	}

	@Override
	public Object getId() {
		return num_empleado;
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
	public String toString() {
		return nombre + " " + apellido_paterno;
	}

	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		Empleado actual = (Empleado) obj;
		num_empleado = actual.getNum_empleado();
		nombre = actual.getNombre();
		apellido_paterno = actual.getApellido_paterno();
		apellido_materno = actual.getApellido_materno();
		departamento = actual.getDepartamento();
		puesto = actual.getPuesto();
		jerarquia = actual.getJerarquia();
		fecha_ingreso = actual.getFecha_ingreso();
		mac_address = actual.getMac_address();
		tarjeta = actual.getTarjeta();
		banco = actual.getBanco();
		clabe = actual.getClabe();
		cuenta = actual.getCuenta();
		return this;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellido_paterno + " " + apellido_materno;
	}

	public String getNombreCompletoUpperCase() {
		return nombre.toUpperCase() + " " + apellido_paterno.toUpperCase() + " " + apellido_materno.toUpperCase();
	}

	public Long getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(Long num_empleado) {
		this.num_empleado = num_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(Integer jerarquia) {
		this.jerarquia = jerarquia;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public Empleado getJefe() {
		return jefe;
	}

	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Long getClabe() {
		return clabe;
	}

	public void setClabe(Long clabe) {
		this.clabe = clabe;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

}
