package com.land.back.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_pdf")
public class CargaPdf extends EntidadGenerica {

	/**
	 * yare
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_pdf", sequenceName = "seq_tb_pdf", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_pdf", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_pdf")
	private Long idPdf = 0L;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "nombredocumento")
	private String nombreDocumento;

	@Column(name = "archivo")
	private byte[] archivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea;

	@Column(name = "tipoarchivo")
	private Integer tipoArchivo;

	public CargaPdf() {
	}

	public CargaPdf(Long id) {
		this.idPdf = id;
	}

	public Long getIdPdf() {
		return this.idPdf;
	}

	@Transient
	@Override
	public Object getId() {
		return idPdf;
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
		CargaPdf actual = (CargaPdf) obj;
		nombreDocumento = actual.getNombreDocumento();
		descripcion = actual.getDescripcion();

		return this;
	}



	public void queryTipo() {
		this.queryHql = " FROM " + CargaPdf.class.getCanonicalName() + " m "	
				+ " WHERE  m.tipoArchivo = :tipo " ;
		this.params.put("tipo", tipoArchivo);
		

	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

	public void setIdPdf(Long idPdf) {
		this.idPdf = idPdf;
	}

	public Integer getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(Integer tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

}
