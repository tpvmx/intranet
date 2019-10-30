package com.land.back.entities;

import java.util.List;
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
import javax.persistence.Transient;

import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.back.nomapping.DiaDinamico;

@Entity
@Table(name = "tb_gastodetalle")
public class GastoDetalle extends EntidadGenerica {

	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tb_gasto_detalle", sequenceName = "seq_tb_gasto_detalle", allocationSize = 1)
	@GeneratedValue(generator = "seq_tb_gasto_detalle", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_gastodetalle")
	private Long idGastodetalle = 0L;

	@Column(name = "costos")
	private Integer costo;

	@Column(name = "orden")
	private Integer orden;

	@Column(name = "concepto")
	private String concepto;

	@Column(name = "dias")
	private String dias = "";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gastoviaje")
	private GastoViaje Gastoviaje;

	@Transient
	private List<DiaDinamico> listDias;

	@Transient
	private Double total = 0D;

	public GastoDetalle() {

	}

	public void queryAll() {
		this.queryHql = "FROM " + GastoDetalle.class.getCanonicalName() + " m ";
	}

	@Override
	public Object getId() {
		return idGastodetalle;
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
		GastoDetalle actual = (GastoDetalle) obj;
		costo = actual.getCosto();
		orden = actual.getOrden();
		concepto = actual.getConcepto();
		dias = actual.getDias();
		Gastoviaje = actual.getGastoviaje();
		return this;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public List<DiaDinamico> getListDias() {
		return listDias;
	}

	public void setListDias(List<DiaDinamico> listDias) {
		this.listDias = listDias;
	}

	public GastoViaje getGastoviaje() {
		return Gastoviaje;
	}

	public void setGastoviaje(GastoViaje gastoviaje) {
		Gastoviaje = gastoviaje;
	}

	public Double getTotal() {
		if (listDias == null) {
			return total;
		}
		total = 0D;
		for (DiaDinamico var : listDias) {
			total += var.getValor();
		}
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void queryXGastoViaje() {
		this.queryHql = "FROM " + GastoDetalle.class.getCanonicalName()
				+ " d WHERE d.Gastoviaje = :gasto ORDER BY d.orden";
		this.params.put("gasto", Gastoviaje);
	}

}
