package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_tipo")
public class TipoUsuario extends EntidadGenerica {

	/**
	 * LGONZALEZ
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipo")
	private Integer idTipoUsuario = 0;

	@Column(name = "descripcion")
	private String descripcion;

	public TipoUsuario() {
	}

	public TipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.descripcion;
	}

	public void queryAll() {
		this.queryHql = "FROM " + TipoUsuario.class.getCanonicalName() + " m ";
	}

	public void queryActivos() {
		this.queryHql = "FROM " + TipoUsuario.class.getCanonicalName() + " m ";
	}

	@Transient
	@Override
	public Object getId() {
		return idTipoUsuario;
	}

	@Override
	public String getQueryHql() {
		return this.queryHql;
	}

	@Override
	public Map<Object, Object> getParametros() {
		return this.params;
	}

	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		TipoUsuario actual = (TipoUsuario) obj;
		descripcion = actual.getDescripcion();
		return this;
	}

	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}


}
