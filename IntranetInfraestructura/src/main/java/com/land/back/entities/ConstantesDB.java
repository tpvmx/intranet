package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

/**
 * Correo generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "constantes")
public class ConstantesDB extends EntidadGenerica {

	@Id
	@Column(name = "id_constantes")
	private Long idConstantes;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "valor")
	private String valor;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descdetallada")
	private String descdetallada;

	public Long getIdConstantes() {
		return idConstantes;
	}

	public void setIdConstantes(Long idConstantes) {
		this.idConstantes = idConstantes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescdetallada() {
		return descdetallada;
	}

	public void setDescdetallada(String descdetallada) {
		this.descdetallada = descdetallada;
	}

	public void queryBuscaTodas() {
		queryHql = "select c from " + ConstantesDB.class.getCanonicalName() + " c ";
	}

	@Transient
	@Override
	public Object getId() {
		return idConstantes;
	}

	@Transient
	@Override
	public Map<Object, Object> getParametros() {
		return this.params;
	}

	@Transient
	@Override
	public String getQueryHql() {
		return this.queryHql;
	}

	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		// TODO Auto-generated method stub
		return null;
	}
}