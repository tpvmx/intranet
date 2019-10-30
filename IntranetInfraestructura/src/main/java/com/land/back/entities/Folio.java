package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_folio")
public class Folio extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "referencia")
	private String referencia;

	@Column(name = "folio")
	private Integer folio;
	
	@Column(name = "anio")
	private Integer anio;

	public Folio() {
	}

	@Transient
	@Override
	public Object getId() {
		return referencia;
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
		Folio actual = (Folio) obj;
		folio = actual.getFolio();
		anio = actual.getAnio();
		return this;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public void queryUltimo() {
		this.queryHql = " FROM " + Folio.class.getCanonicalName() + " m " + " WHERE m.referencia = :ref ";
		this.params.put("ref", referencia);
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}
