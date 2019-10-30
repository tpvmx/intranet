package com.land.front.dao;

import java.util.List;

import com.land.back.entities.CargaPdf;
import com.land.back.entities.Empleado;
import com.land.back.jpa.tx.util.EntidadGenerica;

public class CargaPdfDAO extends DAOComun {

	private static final long serialVersionUID = 1L;

	private List<CargaPdf> listCargaPdf;
	private CargaPdf cargaPdf;
	
	public CargaPdfDAO(Empleado empleado) throws Exception {
		super(empleado);
	}

	public <T extends EntidadGenerica> T buscaXID(T obj) throws Exception {
		return session.searchGenericForId(obj);
	}
	public void insertarPDF(String nombreArchivo, String contenttype, byte[] imagen, Integer tipoArchivo) throws Exception {
		CargaPdf pdf = new CargaPdf();
		pdf.setTipoArchivo(tipoArchivo);
		pdf.queryTipo();
		pdf = session.searchGenericOnly(pdf);
		if (pdf == null){
			pdf = new CargaPdf();
		}
		pdf.setTipoArchivo(tipoArchivo);
		pdf.setArchivo(imagen);
		pdf.setDescripcion(nombreArchivo);
		
	
	
		session.saveUpdateGeneric(pdf, empleadoActual);
		
	}



	public List<CargaPdf> getListCargaPdf() {
		return listCargaPdf;
	}

	public void setListCargaPdf(List<CargaPdf> listCargaPdf) {
		this.listCargaPdf = listCargaPdf;
	}

	public CargaPdf getCargaPdf() {
		return cargaPdf;
	}

	public void setCargaPdf(CargaPdf cargaPdf) {
		this.cargaPdf = cargaPdf;
	}

	

}
