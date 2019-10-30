package com.land.back.nomapping;

import java.util.List;

public class ConceptoDinamico {
	private int orden;
	private String concepto = "";
	private List<DiaDinamico> list;
	private boolean edit;

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public List<DiaDinamico> getList() {
		return list;
	}

	public void setList(List<DiaDinamico> list) {
		this.list = list;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void edit() {
		if (edit) {
			edit = false;
		} else {
			edit = true;
		}
	}

}
