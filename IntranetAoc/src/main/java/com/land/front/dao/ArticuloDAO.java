package com.land.front.dao;

import java.util.Date;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.util.Constantes;
import com.land.front.util.UtilTimestamp;

public class ArticuloDAO extends DAOComun {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Noticias> listNoticiasInteres;
	private Noticias notaActual;
	private List<NoticiasImagenes> listImagenesNota;

	public ArticuloDAO(Empleado empleado) throws Exception {
		super(empleado);
	}

	public void buscaNoticiasInteres() throws Exception {
		Noticias ga = new Noticias();
		ga.setFecact(UtilTimestamp.getDateCeroHoras(new Date()));
		ga.setIdtiponoticia(Constantes.TIPO_INTERES);
		ga.queryNoticiasFechaTipo();
		listNoticiasInteres = session.searchGeneric(ga);
	}

	public void buscaNoticiaActual(Long idNoticiaParam) throws Exception {
		Noticias note = new Noticias();
		note.setIdNoticias(idNoticiaParam);
		notaActual = (Noticias) session.searchGenericForId(note);
		
		NoticiasImagenes ima = new NoticiasImagenes();
		ima.setIdNoticia(notaActual.getIdNoticias());
		ima.queryXImagenesDiffPrincipal();
		listImagenesNota = session.searchGeneric(ima);
	}

	public List<Noticias> getListNoticiasInteres() {
		return listNoticiasInteres;
	}

	public void setListNoticiasInteres(List<Noticias> listNoticiasInteres) {
		this.listNoticiasInteres = listNoticiasInteres;
	}

	public List<NoticiasImagenes> getListImagenesNota() {
		return listImagenesNota;
	}

	public void setListImagenesNota(List<NoticiasImagenes> listImagenesNota) {
		this.listImagenesNota = listImagenesNota;
	}

	public Noticias getNotaActual() {
		return notaActual;
	}

	public void setNotaActual(Noticias notaActual) {
		this.notaActual = notaActual;
	}

}
