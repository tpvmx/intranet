package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_tipoevento")
public class TipoEvento extends EntidadGenerica{
	
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_tipoevento")
	private Long id_tipoevento = 0L;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estatus")
	private Integer estatus;
	
	@Column(name = "style")
	private String style;
	
	@Transient
	private boolean seleccion;
	
	public TipoEvento() {
		
	}
	
     public TipoEvento(Long id) {
		this.id_tipoevento = id;
	}
	
	public void queryAll() {
		this.queryHql = "FROM " + TipoEvento.class.getCanonicalName() + " m ";
	}

	@Override
	public Object getId() {
		return id_tipoevento;
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
		TipoEvento actual = (TipoEvento) obj;
		descripcion = actual.getDescripcion();
		estatus = actual.getEstatus();
		style = actual.getStyle();
		return this;
	}

	public Long getId_tipoevento() {
		return id_tipoevento;
	}

	public void setId_tipoevento(Long id_tipoevento) {
		this.id_tipoevento = id_tipoevento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

}
