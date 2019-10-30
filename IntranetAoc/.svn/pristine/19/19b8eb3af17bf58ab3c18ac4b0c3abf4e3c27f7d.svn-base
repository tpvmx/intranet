package com.land.front.beans.noticias;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.Noticias;
import com.land.back.entities.TipoNoticia;
import com.land.front.beans.BeanComun;
import com.land.front.beans.BeanSession;

import comland.front.dao.noticias.SummaryNewsDAO;

@ViewScoped
@ManagedBean
public class BeanNoticiasResumen extends BeanComun {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SummaryNewsDAO dao;
	private Noticias myNoticias = new Noticias();
	private TipoNoticia myTipoNoticia = new TipoNoticia();
	private Long tipoNoticia;

	@PostConstruct
	public void init() {

		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			dao = new SummaryNewsDAO(beanLogin.getPermisoActual().getEmpleado());
			datosIniciales();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void datosIniciales() {
		try {
			dao.buscaTipoNoticia();
		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta(getTexto("errornoti"));
		}
	}

	public String CrearNoticia() {
		String pagina = "";
		try {
			pagina = "noticia?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
		return pagina;

	}

	public String Actualizar() {
		String pagina = "";
		try {
			dao.ActualizaOrden();
			pagina = "noticiaResumen?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
		return pagina;

	}
	
	public void eliminaNoticia(Noticias myNoticia) {
		try {
			dao.eliminaNoticia(myNoticia);
			alertInfo(getTexto("dicMsgExitoso"));
			buscaNoticiasXTipo();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("ERROR") + e);
		}
	}

	public void buscaNoticiasXTipo() throws Exception {
		if (tipoNoticia.longValue() == -1) {
			alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("resuTipoNoticia")));
			return;
		}
		dao.buscaNoticiasResumen(tipoNoticia);
	}

	public List<Noticias> getListNoticiasResumen() {
		return dao.getListNoticiasResumen();
	}

	public Noticias getMyNoticias() {
		return myNoticias;
	}

	public void setMyNoticias(Noticias myNoticias) {
		this.myNoticias = myNoticias;
	}

	public Noticias getNoticiaActual() {
		return myNoticias;
	}

	public void setNoticiaActual(Noticias myNoticias) {
		this.myNoticias = myNoticias;
	}

 
	public List<TipoNoticia> getListTipoNoticia() {
		return dao.getListTipoNoticia();
	}

	public void setTipoNoticia(Long tipoNoticia) {
		this.tipoNoticia = tipoNoticia;
	}

	public TipoNoticia getMyTipoNoticia() {
		return myTipoNoticia;
	}

	public void setMyTipoNoticia(TipoNoticia myTipoNoticia) {
		this.myTipoNoticia = myTipoNoticia;
	}

	public Long getTipoNoticia() {
		return tipoNoticia;
	}
}
