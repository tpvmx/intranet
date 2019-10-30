package com.land.front.beans;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.front.dao.ArticuloDAO;
import com.land.front.util.UtilTimestamp;

@ViewScoped
@ManagedBean
public class BeanArticulo extends BeanComun {

	/**
	 * LGONZALEZ
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDAO dao;
	private Long idNoticiaParam;

	@PostConstruct
	public void init() {
		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			dao = new ArticuloDAO(beanLogin.getPermisoActual().getEmpleado());
		} catch (Exception e) {
			alertError(getTexto("errorPagina") + " init " + e);
		}
	}

	@Override
	public void datosIniciales() {
		try {

		} catch (Exception e) {
			alertError(getTexto("errorPagina") + " datosIniciales " + e);
		}
	}

	public void buscaNoticiaParam() {
		try {
			dao.buscaNoticiaActual(idNoticiaParam);
			dao.buscaNoticiasInteres();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("erroradmiin") + e);
		}
	}

	public StreamedContent imagenOrden(int orden) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			NoticiasImagenes image = dao.getListImagenesNota().get(orden);
			if (image == null) {
				alertAlerta("Sin imagen " + orden);
				return new DefaultStreamedContent();
			}
			byte[] imagenbyte = image.getImagen();
			return new DefaultStreamedContent(new ByteArrayInputStream(imagenbyte), image.getContentType());
		}
	}

	public String construyeBase64(int orden) {
		NoticiasImagenes img = dao.getListImagenesNota().get(orden);
		if (img == null)
			return "";
		String base64 = Base64.getEncoder().encodeToString(img.getImagen());
		return "data:" + img.getContentType() + ";base64," + base64;
	}

	public String obtenTextoNota() {
		StringBuffer html = new StringBuffer();
		LinkedHashMap<Long, NoticiasImagenes> hmImagenesAgregadas = new LinkedHashMap<Long, NoticiasImagenes>();
		int orden = 0;
		Noticias nota = getNoticiaActual();

		try {
			NoticiasImagenes image = getListImagenes().get(orden);
			if (image != null) {
				String base64 = construyeBase64(orden);
				String incrustaImagen = "<img width=\"50%\" height=\"50%\" src=\"" + base64 + "\" />";
				html.append(incrustaImagen);
				orden++;
				hmImagenesAgregadas.put(image.getIdNoticiaGaleria(), image);
			}
			String[] textoSeparado;
			if (BeanSession.localeActual.equals(BeanSession.SPANISH))
				textoSeparado = nota.getNotaCompleta().split("##########");
			else
				textoSeparado = nota.getCompletenote().split("##########");
			for (String textoActual : textoSeparado) {
				html.append(textoActual);
				image = getListImagenes().get(orden);
				if (image != null) {
					String base64 = construyeBase64(orden);
					String incrustaImagen = "<img width=\"50%\" height=\"50%\" src=\"" + base64 + "\" />";
					html.append(incrustaImagen);
					orden++;
					hmImagenesAgregadas.put(image.getIdNoticiaGaleria(), image);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			alertAlerta("Imagenes vacias.");
			if (html.length() == 0) {
				html.append(nota.getNotaCompleta());
			}
		}

		/**
		 * VALIDAR INCRUSTADOR
		 */
		orden = 0;
		for (NoticiasImagenes imageAdd : getListImagenes()) {
			if (hmImagenesAgregadas.containsKey(imageAdd.getIdNoticiaGaleria())) {
				orden++;
				continue;
			}
			String base64 = construyeBase64(orden);
			String incrustaImagen = "<img width=\"50%\" height=\"50%\" src=\"" + base64 + "\" />";
			html.append(incrustaImagen);
		}

		return html.toString();
	}

	public String fechaLetra() {
		return UtilTimestamp.getInstance().getFechaLetra(new Date());
	}

	public List<Noticias> getListInteres() {
		return dao.getListNoticiasInteres();
	}

	public List<NoticiasImagenes> getListImagenes() {
		return dao.getListImagenesNota();
	}

	public Long getIdNoticiaParam() {
		return idNoticiaParam;
	}

	public void setIdNoticiaParam(Long idNoticiaParam) {
		this.idNoticiaParam = idNoticiaParam;
	}

	public Noticias getNoticiaActual() {
		return dao.getNotaActual();
	}

}
