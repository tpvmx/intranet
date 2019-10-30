package com.land.front.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.land.back.entities.CargaPdf;
import com.land.back.util.Constantes;
import com.land.front.dao.CargaPdfDAO;

@ViewScoped
@ManagedBean

public class BeanPDF extends BeanComun {

	/**
	 * Yare
	 */

	private static final long serialVersionUID = 1L;

	private List<CargaPdf> carga;
	private CargaPdf cargapdf;;

	private UploadedFile file;
	private String tipo;
	private Integer tipoArchivo=0;

	private CargaPdfDAO dao;

	@PostConstruct
	public void init() {

		try {

			datosIniciales();
		} catch (Exception e) {

		}
	}

	//public void tipopdf() {
	//}

	public void cargapdf(FileUploadEvent event) {

		try {
			if (tipo.equals("proteccion")) {
				tipoArchivo = Constantes.PDF_PROTECCION;

				return;
				}
			if (tipo.equals("productos")) {
				tipoArchivo = Constantes.PDF_POLITICAS_PRODUCTOS;

				return;
			}
			if (tipo.equals("polviaje")) {
				tipoArchivo = Constantes.PDF_POLITICAS_VIAJE;

				return;
			}
			if (tipo.equals("polcaja")) {
				tipoArchivo = Constantes.PDF_POLITICAS_CAJA;

				return;
			}
			if (tipo.equals("polvestimenta")) {
				tipoArchivo = Constantes.PDF_POLITICAS_VESTIMENTA;

				return;
			}
			if (tipo.equals("polactivo")) {
				tipoArchivo = Constantes.PDF_POLITICAS_ACTIVO;

				return;
			}
			if (tipo.equals("polconvivencia")) {
				tipoArchivo = Constantes.PDF_POLITICAS_CONVIVENCIA;

				return;
			}
			int algo = 899;
			String nombreArchivo = event.getFile().getFileName();
			byte[] imagen = event.getFile().getContents();
			String contenttype = event.getFile().getContentType();
			dao.insertarPDF(nombreArchivo, contenttype, imagen, tipoArchivo);
		

		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("errorinsert") + e);
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@Override
	public void datosIniciales() {
		BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
		try {
			dao = new CargaPdfDAO(beanLogin.getPermisoActual().getEmpleado());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public CargaPdf getCargapdf() {
		return cargapdf;
	}

	public void setCargapdf(CargaPdf cargapdf) {
		this.cargapdf = cargapdf;
	}

	public List<CargaPdf> getCarga() {
		return carga;
	}

	public void setCarga(List<CargaPdf> carga) {
		this.carga = carga;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public CargaPdfDAO getDao() {
		return dao;
	}

	public void setDao(CargaPdfDAO dao) {
		this.dao = dao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(Integer tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

}