package com.land.front.beans.noticias;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.entities.TipoNoticia;
import com.land.back.util.Constantes;
import com.land.front.beans.BeanComun;
import com.land.front.beans.BeanSession;
import com.land.front.util.UtilTimestamp;

import comland.front.dao.noticias.InsertNewsDAO;

@ViewScoped
@ManagedBean
public class BeanNoticias extends BeanComun {

	/**
	 * JTOVAR
	 */

	private static final long serialVersionUID = 1L;

	private Noticias noticiaActual = new Noticias();
	private List<NoticiasImagenes> noticiaImagenes;
	private NoticiasImagenes noticiaImagenesC;
	private Noticias resulNoticia;
	private boolean caldisabled; // with getter and setter
	private TipoNoticia myTipoNoticia = new TipoNoticia();
	private UploadedFile files;
	private InsertNewsDAO dao;
	private Long idNoticiaParam = 0L;

	@PostConstruct
	public void init() {

		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			dao = new InsertNewsDAO(beanLogin.getPermisoActual().getEmpleado());
			datosIniciales();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void datosIniciales() {
		try {
			dao.buscaTipoNoticia();
			caldisabled = true;
			limpiaCampos();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("dicErrorDatosIni") + e);
		}
	}

	public void buscaNoticiaParam() {
		try {
			if (idNoticiaParam.longValue() != 0) {
				noticiaActual.setIdNoticias(idNoticiaParam);
				noticiaActual = dao.buscaXID(noticiaActual); // lleno mi objeto ya con los datos
				validaTipo();
			} else {
				limpiaCampos();
			}

		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
	}

	public String fechaLetra() {
		return UtilTimestamp.getInstance().getFechaLetra(new Date());
	}

	public boolean getCaldisabled() {
		return caldisabled;
	}

	public void setCaldisabled(boolean caldisabled) {
		this.caldisabled = caldisabled;
	}

	public UploadedFile getFiles() {
		return files;
	}

	public void setFiles(UploadedFile files) {
		this.files = files;
	}

	public int getTIMEOUT_INACTIVIDAD() {
		return Constantes.TIMEOUT_INACTIVIDAD;
	}

	public String InsertaNoticia() {
		String pagina = "";
		try {

			if (noticiaActual.getIdtiponoticia() == -1) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", "Tipo de Noticia"));
				return "";
			}

			if (noticiaActual.getFecinicio() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", "Fecha Inicio"));
				return "";
			}
			if (noticiaActual.getFecfin() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", "Fecha Fin"));
				return "";
			}

			noticiaActual = dao.InsertarNoticia(noticiaActual);
			pagina = "noticiaImagenes?faces-redirect=true&idNoticias=" + noticiaActual.getIdNoticias();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("errorinsert") + e);
		}
		return pagina;

	}

	public String Finalizar() {
		String pagina = "";
		try {

			limpiaCampos();
			pagina = "noticiasResumen?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
		return pagina;

	}

	public void subirImagenPrincipal(FileUploadEvent event) {

		try {
			Noticias de = resulNoticia;
			String archivo = event.getFile().getFileName();
			byte[] contenido = event.getFile().getContents();
			String tipo = event.getFile().getContentType();
			dao.insertarImagenes(archivo, tipo, contenido, de.getIdNoticias(), Constantes.IMAGEN_PRINCIPAL);

		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("errorinsert") + e);
		}

	}

	private void limpiaCampos() {
		noticiaActual = new Noticias();
		idNoticiaParam = 0L;
	}

	public void validaTipo() {
		if (noticiaActual.getIdtiponoticia() == -1) {
			caldisabled = true;
			noticiaActual.setUrl("");
		} else if (noticiaActual.getIdtiponoticia() == Constantes.TIPO_INTERES) {
			caldisabled = false;
		} else if (noticiaActual.getIdtiponoticia() == Constantes.TIPO_PRINCIPAL) {
			caldisabled = true;
			noticiaActual.setUrl("");
		}
	}

	public Noticias getNoticiaActual() {
		return noticiaActual;
	}

	public void setNoticiaActual(Noticias noticiaActual) {
		this.noticiaActual = noticiaActual;
	}

	public TipoNoticia getTipoNoticia() {
		return myTipoNoticia;
	}

	public void setTipoNoticia(TipoNoticia myTipoNoticia) {
		this.myTipoNoticia = myTipoNoticia;
	}

	public List<TipoNoticia> getListTipoNoticia() {
		return dao.getListTipoNoticia();
	}

	public Noticias getResulNoticia() {
		return resulNoticia;
	}

	public void setResulNoticia(Noticias resulNoticia) {
		this.resulNoticia = resulNoticia;
	}

	public Long getIdNoticiaParam() {
		return idNoticiaParam;
	}

	public void setIdNoticiaParam(Long idNoticiaParam) {
		this.idNoticiaParam = idNoticiaParam;
	}

}