package com.land.front.beans.noticias;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.util.Constantes;
import com.land.front.beans.BeanComun;
import com.land.front.beans.BeanSession;

import comland.front.dao.noticias.InsertNewsDAO;

@ViewScoped
@ManagedBean
public class BeanImagenes extends BeanComun {

	/**
	 * JTOVAR
	 */
	private static final long serialVersionUID = 1L;
	private InsertNewsDAO dao;
	private Long idNoticiaParam = 0L;
	private Noticias noticiaActual = new Noticias();

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
				dao.buscaImagenesXNota(idNoticiaParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
	}

	public String construyeBase64(int tipoImagen) {
		NoticiasImagenes image = dao.getHmImagenesXNota().get(tipoImagen);
		if (image == null)
			return "";
		String base64 = Base64.getEncoder().encodeToString(image.getImagen());
		return "data:" + image.getContentType() + ";base64," + base64;
	}

	public String muestraImagenActualPrincipal() {
		return construyeBase64(Constantes.IMAGEN_PRINCIPAL);
	}

	public String muestraImagenActualSecundaria() {
		return construyeBase64(Constantes.IMAGEN_SECUNDARIA);
	}

	public String muestraImagenActual3() {
		return construyeBase64(Constantes.IMAGEN_3);
	}

	public String muestraImagenActual4() {
		return construyeBase64(Constantes.IMAGEN_4);
	}

	public String muestraImagenActual5() {
		return construyeBase64(Constantes.IMAGEN_5);
	}

	public void subirImagenPrincipal(FileUploadEvent event) {
		cargaImagen(event, Constantes.IMAGEN_PRINCIPAL);
	}

	public void subirImagenSecundaria(FileUploadEvent event) {
		cargaImagen(event, Constantes.IMAGEN_SECUNDARIA);
	}

	public void subirImagen3(FileUploadEvent event) {
		cargaImagen(event, Constantes.IMAGEN_3);
	}

	public void subirImagen4(FileUploadEvent event) {
		cargaImagen(event, Constantes.IMAGEN_4);
	}

	public void subirImagen5(FileUploadEvent event) {
		cargaImagen(event, Constantes.IMAGEN_5);
	}

	private void cargaImagen(FileUploadEvent event, int imagenPrincipal) {
		try {
			String archivo = event.getFile().getFileName();
			byte[] contenido = event.getFile().getContents();
			String tipo = event.getFile().getContentType();
			dao.insertarImagenes(archivo, tipo, contenido, noticiaActual.getIdNoticias(), imagenPrincipal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Noticias getNoticiaActual() {
		return noticiaActual;
	}

	public void setNoticiaActual(Noticias noticiaActual) {
		this.noticiaActual = noticiaActual;
	}

	public Long getIdNoticiaParam() {
		return idNoticiaParam;
	}

	public void setIdNoticiaParam(Long idNoticiaParam) {
		this.idNoticiaParam = idNoticiaParam;
	}

}
