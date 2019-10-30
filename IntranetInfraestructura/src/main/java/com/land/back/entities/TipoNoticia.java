package com.land.back.entities;


import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.land.back.jpa.tx.util.EntidadGenerica;


@Entity
@Table(name = "tb_tiponoticia")
public class TipoNoticia extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id	
	@Column(name = "id_tiponoticia")
	private Long idTipoNoticia = 0L;

	@Column(name = "descripcion")
	private String descripcion;	
	
	public TipoNoticia() {
	}

	public TipoNoticia(Long idTipoNoticia) {
		this.idTipoNoticia = idTipoNoticia;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * DATOS SIN MAPPEAR
	 */

	@Transient
	private Integer idTipoNoticiaTemp = 0;
	
	@Transient
	@Override
	public Object getId() {
		return idTipoNoticia;
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
	
	public void queryAll() {
		this.queryHql = "FROM " + TipoNoticia.class.getCanonicalName()+ " m ";
	}

	
	
	

	
	@Override
	public <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj) {
		TipoNoticia actual = (TipoNoticia) obj;	
		idTipoNoticia = actual.getIdTipoNoticia();
		descripcion = actual.getDescripcion();
		
		return this;
	}
	
	
	public Long getIdTipoNoticia() {
		return idTipoNoticia;
	}

	public void setIdTipoNoticia(Long idTipoNoticia) {
		this.idTipoNoticia = idTipoNoticia;
	}
	
	

}
