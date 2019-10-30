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
@Table(name = "tb_noticia")
public class Noticias extends EntidadGenerica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_noticia", sequenceName = "seq_tb_noticia", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_noticia", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_noticia")
	private Long idNoticias = 0L;

	@Column(name = "titulo")
	private String titulo = "";

	@Column(name = "color_titulo")
	private String colorTitulo = "";

	@Column(name = "subtitulo")
	private String subtitulo = "";

	@Column(name = "color_subtitulo")
	private String colorSubTitulo = "";

	@Column(name = "notacompleta")
	private String notacompleta = "";

	@Column(name = "resumen")
	private String resumen = "";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecinicio")
	private Date fecinicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecfin")
	private Date fecfin;

	@Column(name = "usucrea")
	private int usucrea;

	@Column(name = "usuact")
	private int usuact;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "feccrea")
	private Date feccrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecact")
	private Date fecact;

	@Column(name = "orden")
	private int orden;

	// nuevos campos noticias en ingles

	@Column(name = "title")
	private String title = "";

	@Column(name = "subtitle")
	private String subtitle = "";

	@Column(name = "completenote")
	private String completenote = "";

	@Column(name = "summary")
	private String summary = "";

	@Column(name = "url")
	private String url = "";

	@Column(name = "id_tiponoticia")
	private long idTiponoticia = -1;

	public Noticias() {
	}

	public Noticias(Long id) {
		this.idNoticias = id;
	}

	public Long getIdNoticias() {
		return this.idNoticias;
	}

	public void setIdNoticias(Long idNoticias) {
		this.idNoticias = idNoticias;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return this.subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getNotaCompleta() {
		return this.notacompleta;
	}

	public void setNotaCompleta(String notacompleta) {
		this.notacompleta = notacompleta;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	// fechas
	public Date getFecinicio() {
		return fecinicio;
	}

	public void setFecinicio(Date fecinicio) {
		this.fecinicio = fecinicio;
	}

	public Date getFecfin() {
		return fecfin;
	}

	public void setFecfin(Date fecfin) {
		this.fecfin = fecfin;
	}

	public Integer getUsucrea() {
		return usucrea;
	}

	public void setUsucrea(Integer usucrea) {
		this.usucrea = usucrea;
	}

	public Integer getUsuact() {
		return usuact;
	}

	public void setUsuact(Integer usuact) {
		this.usuact = usuact;
	}

	// fechas
	public Date getFeccrea() {
		return feccrea;
	}

	public void setFeccrea(Date feccrea) {
		this.feccrea = feccrea;
	}

	public Date getFecact() {
		return fecact;
	}

	public void setFecact(Date fecact) {
		this.fecact = fecact;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	// noticias ingles

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getCompletenote() {
		return this.completenote;
	}

	public void setCompletenote(String completenote) {
		this.completenote = completenote;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getIdtiponoticia() {
		return idTiponoticia;
	}

	public void setIdtiponoticia(long idTiponoticia) {
		this.idTiponoticia = idTiponoticia;
	}

	@Transient
	@Override
	public Object getId() {
		return idNoticias;
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
		Noticias actual = (Noticias) obj;
		titulo = actual.getTitulo();
		subtitulo = actual.getSubtitulo();
		notacompleta = actual.getNotaCompleta();
		resumen = actual.getResumen();
		fecinicio = actual.getFecinicio();
		fecfin = actual.getFecfin();
		usucrea = actual.getUsucrea();
		usuact = actual.getUsuact();
		feccrea = actual.getFeccrea();
		fecact = actual.getFecact();
		orden = actual.getOrden();
		title = actual.getTitle();
		subtitle = actual.getSubtitle();
		completenote = actual.getCompletenote();
		summary = actual.getSummary();
		url = actual.getUrl();
		idTiponoticia = actual.getIdtiponoticia();
		colorTitulo = actual.getColorTitulo();
		colorSubTitulo = actual.getColorSubTitulo();
		return this;
	}

	public void queryAllNoticias() {
		this.queryHql = "FROM " + Noticias.class.getCanonicalName() + " g ";
	}

	public void queryNoticiasInicio() {

		this.queryHql = "FROM " + Noticias.class.getCanonicalName() + " g "
				+ "WHERE g.idTiponoticia = 1 ORDER BY g.orden ";

	}

	public void queryNoticiasTipoOrden() {

		this.queryHql = "FROM " + Noticias.class.getCanonicalName() + " g "
				+ "WHERE g.idTiponoticia = :tipo ORDER BY g.orden ";
		this.params.put("tipo", idTiponoticia);

	}

	public void queryNoticiasFechaTipo() {

		this.queryHql = "FROM " + Noticias.class.getCanonicalName() + " g "
				+ "WHERE g.idTiponoticia = :tipo AND :fecha BETWEEN g.fecinicio AND g.fecfin ORDER BY g.orden ";
		this.params.put("tipo", idTiponoticia);
		this.params.put("fecha", fecact);

	}

	public void queryOrdenNoticia() {
		this.queryHql = " SELECT  MAX (m.orden) FROM " + Noticias.class.getCanonicalName() + " m  "
				+ "WHERE m.idTiponoticia =:idTipo";
		this.params.put("idTipo", idTiponoticia);
	}

	public String getColorTitulo() {
		return colorTitulo;
	}

	public void setColorTitulo(String colorTitulo) {
		this.colorTitulo = colorTitulo;
	}

	public String getColorSubTitulo() {
		return colorSubTitulo;
	}

	public void setColorSubTitulo(String colorSubTitulo) {
		this.colorSubTitulo = colorSubTitulo;
	}
}
