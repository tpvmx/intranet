package com.land.back.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.back.util.Constantes;

@Entity
@Table(name = "tb_noticiagaleria")
public class NoticiasImagenes extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_noticiaimagen", sequenceName = "seq_tb_noticiaimagen", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_noticiaimagen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_noticiagaleria")
	private Long idNoticiaGaleria = 0L;

	@Column(name = "nombre_archivo")
	private String nombreArchivo;

	@Column(name = "contenttype")
	private String contentType;

	@Column(name = "imagen")
	private byte[] imagen;

	@Column(name = "id_noticia")
	private Long idNoticia;

	@Column(name = "principal")
	private int principal;

	public NoticiasImagenes() {
	}

	public NoticiasImagenes(Long id) {
		this.idNoticiaGaleria = id;
	}

	public Long getIdNoticiaGaleria() {
		return this.idNoticiaGaleria;
	}

	public void setIdNoticiaGaleria(Long idNoticiaGaleria) {
		this.idNoticiaGaleria = idNoticiaGaleria;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Long getIdNoticia() {
		return this.idNoticia;
	}

	public void setIdNoticia(Long idNoticia) {
		this.idNoticia = idNoticia;
	}

	public int getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	@Transient
	@Override
	public Object getId() {
		return idNoticiaGaleria;
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
		NoticiasImagenes actual = (NoticiasImagenes) obj;
		nombreArchivo = actual.getNombreArchivo();
		contentType = actual.getContentType();
		imagen = actual.getImagen();
		idNoticia = actual.getIdNoticia();
		principal = actual.getPrincipal();
		return this;
	}

	public void queryImage() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticia= :idNot AND m.principal = 2";
		this.params.put("idNot", idNoticia);

	}

	public void queryImageGalery() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticiaGaleria= :idNot AND m.principal = 2";
		this.params.put("idNot", idNoticia);

	}

	public void queryImageGaleria() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticia= :idNot AND m.principal = 3";
		this.params.put("idNot", idNoticia);

	}

	public void queryImageGaleryTipo() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticiaGaleria= :idNot AND m.principal = 3";
		this.params.put("idNot", idNoticia);

	}

	public void queryXNoticiaTipo() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticia= :idNot AND m.principal = :tipo";
		this.params.put("tipo", principal);
		this.params.put("idNot", idNoticia);
	}

	public void queryXImagenesDiffPrincipal() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticia= :idNot AND m.principal != :tipo ORDER BY m.principal";
		this.params.put("tipo", Constantes.IMAGEN_PRINCIPAL);
		this.params.put("idNot", idNoticia);
	}

	public void queryXNoticia() {
		this.queryHql = "FROM " + NoticiasImagenes.class.getCanonicalName() + " m "
				+ " WHERE m.idNoticia= :idNot ";
		this.params.put("idNot", idNoticia);
		
	}

}
