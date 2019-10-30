package com.land.back.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;

@Entity
@Table(name = "tb_evento")
public class Evento extends EntidadGenerica {
	/**
	 * Daniel
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_evento", sequenceName = "seq_tb_evento", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_evento", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_evento")
	private Long id_evento = 0L;

	@Column(name = "descripcion")
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio")
	private Date fecha_inicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_termino")
	private Date fecha_termino;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipoevento")
	private TipoEvento tipoevento;

	public Evento() {

	}

	public void queryAll() {
		this.queryHql = "FROM " + Evento.class.getCanonicalName() + " m ";
	}

	public void queryXTipo(String tipo) {
		this.queryHql = "FROM " + Evento.class.getCanonicalName() + " m"
				+ "  where m.tipoevento.id_tipoevento in("+tipo+") ";
	}
	
	@Transient
	public Long getTempTipoEvento() {
		if (tipoevento != null)
			return tipoevento.getId_tipoevento();
		else
			return 0L;
	}

	public void setTempTipoEvento(Long tempTipoEvento) {
		this.tipoevento = new TipoEvento(tempTipoEvento);
	}
	
	@Override
	public Object getId() {
		return id_evento;
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
		Evento actual = (Evento) obj;
		descripcion = actual.getDescripcion();
		fecha_inicio = actual.getFecha_inicio();
		fecha_termino = actual.getFecha_termino();
		tipoevento = actual.getTipoevento();
		return this;
	}

	public Long getId_evento() {
		return id_evento;
	}

	public void setId_evento(Long id_evento) {
		this.id_evento = id_evento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_termino() {
		return fecha_termino;
	}

	public void setFecha_termino(Date fecha_termino) {
		this.fecha_termino = fecha_termino;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoEvento getTipoevento() {
		return tipoevento;
	}

	public void setTipoevento(TipoEvento tipoevento) {
		this.tipoevento = tipoevento;
	}

}
