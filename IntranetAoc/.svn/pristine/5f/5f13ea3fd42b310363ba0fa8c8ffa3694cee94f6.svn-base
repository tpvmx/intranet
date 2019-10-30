package com.land.front.beans;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.front.util.UtilSesion;

import comland.front.dao.noticias.SummaryNewsDAO;
import mx.org.banxico.dgie.ws.DgieWSLocator;
import mx.org.banxico.dgie.ws.DgieWSPortSoapBindingStub;

@ViewScoped
@ManagedBean
public class BeanInicio extends BeanComun {
	/**
	 * JCTOVAR
	 */
	private static final long serialVersionUID = 1L;
	private SummaryNewsDAO dao;

	private Noticias myNoticias = new Noticias();
	private List<String> listDimencionesPrimero;
	private List<String> listDimenciones;
	private Integer aniosCumple;
	private String proximosCumples = "";
	private String tipoCambios = "";

	@PostConstruct
	public void init() {
		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			empleadoActual = beanLogin.getPermisoActual().getEmpleado();
			dao = new SummaryNewsDAO(empleadoActual);
			datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void datosIniciales() {
		try {
			if (empleadoActual != null) {
				List<Object> cumpleaños = cumpleAnios();
				if (!cumpleaños.isEmpty()) {
					aniosCumple = (Integer) cumpleaños.get(1);
					UtilSesion.getRequestContext().execute("PF('dlgCumple').show();");
				}
			}
			proximosCumples = dao.buscaCumpleaneros();
			if (proximosCumples.isEmpty())
				proximosCumples = "Sin eventos";
			dao.buscaNoticiasInicio();
			dao.buscaNoticiasInicioBanner();
			armaDimencionesPrimero();
			armaDimenciones();
			tipoCambioBanxico();
		} catch (Exception e) {
			e.printStackTrace();
			alertError("Se perdio la session, reinicie su explorador.");
		}
	}

	private List<Object> cumpleAnios() {
		List<Object> listDatos = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(empleadoActual.getFechaNacimiento());
		int mesCumpleanios = cal.get(Calendar.MONTH);
		int diaCumpleanios = cal.get(Calendar.DAY_OF_MONTH);
		int anioCumpleanios = cal.get(Calendar.YEAR);
		cal.setTime(new Date());
		// cal.set(Calendar.MONTH, 5);
		// cal.set(Calendar.DAY_OF_MONTH, 29);
		// cal.getTime();

		int mesActual = cal.get(Calendar.MONTH);
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);
		int anioActual = cal.get(Calendar.YEAR);
		if (mesCumpleanios == mesActual) {
			if (diaCumpleanios == diaActual) {
				listDatos.add(Boolean.TRUE);
				listDatos.add(new Integer(anioActual - anioCumpleanios));
			}
		}
		// No es dia de su cumpleaños
		if (listDatos.isEmpty()) {
			/**
			 * APLICAMOS REGLA para MOSTRAR UN VIERNES ANTES LA POSTAL
			 */
			Calendar calCuandoCae = Calendar.getInstance();
			calCuandoCae.setTime(empleadoActual.getFechaNacimiento());
			calCuandoCae.set(Calendar.YEAR, anioActual);
			calCuandoCae.getTime();

			int diaSemana = calCuandoCae.get(Calendar.DAY_OF_WEEK);
			if (diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY) {
				calCuandoCae.add(Calendar.DAY_OF_MONTH, -1);
				diaSemana = calCuandoCae.get(Calendar.DAY_OF_WEEK);
				if (diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY) {
					calCuandoCae.add(Calendar.DAY_OF_MONTH, -1);
					diaSemana = calCuandoCae.get(Calendar.DAY_OF_WEEK);
				}
				// Validamos si hoy es el viernes antes de su cumple años
				int mesViernesAntes = calCuandoCae.get(Calendar.MONTH);
				int diaViernesAntes = calCuandoCae.get(Calendar.DAY_OF_MONTH);
				if (mesViernesAntes == mesActual) {
					if (diaViernesAntes == diaActual) {
						listDatos.add(Boolean.TRUE);
						listDatos.add(new Integer(anioActual - anioCumpleanios));
					}
				}
			}
		}
		return listDatos;
	}

	public void armaDimencionesPrimero() {
		listDimencionesPrimero = new ArrayList<String>();
		listDimencionesPrimero.add("grid-item1 animate-box");
		listDimencionesPrimero.add("grid-item2 animate-box");
		listDimencionesPrimero.add("grid-item3 animate-box");
		listDimencionesPrimero.add("grid-item4 animate-box");
		listDimencionesPrimero.add("grid-item5 animate-box");
		listDimencionesPrimero.add("grid-item6 animate-box");
		listDimencionesPrimero.add("grid-item7 animate-box");
		listDimencionesPrimero.add("grid-item8 animate-box");
	}

	public void armaDimenciones() {
		listDimenciones = new ArrayList<String>();
		listDimenciones.add("two-third animate-box");
		listDimenciones.add("one-third animate-box");
		listDimenciones.add("one-half animate-box");
		listDimenciones.add("one-half animate-box");
		listDimenciones.add("one-third animate-box");
		listDimenciones.add("two-third animate-box");
		// SegundaVuelta
		listDimenciones.add("two-third animate-box");
		listDimenciones.add("one-third animate-box");
		listDimenciones.add("one-half animate-box");
		listDimenciones.add("one-half animate-box");
		listDimenciones.add("one-third animate-box");
		listDimenciones.add("two-third animate-box");
	}

	public String dameColorTitulo(Noticias not) {
		return not.getColorTitulo() == null ? "" : "color: #" + not.getColorTitulo();
	}

	public String dameColorSubTitulo(Noticias not) {
		return not.getColorSubTitulo() == null ? "" : "color: #" + not.getColorSubTitulo();
	}

	public String obtenDimencion(Integer index) {
		String actual = "";
		try {
			actual = listDimenciones.get(Integer.parseInt(index.toString()));

		} catch (IndexOutOfBoundsException e) {

			int numeroAleatorio = (int) (Math.random() * 5 + 0);
			actual = listDimenciones.get(numeroAleatorio);
		}

		return actual;
	}

	public String obtenDimencionPrimero(Integer index) {
		String actual = "";
		try {
			if (index <= 7)
				actual = listDimencionesPrimero.get(Integer.parseInt(index.toString()));
		} catch (IndexOutOfBoundsException e) {
			int numeroAleatorio = (int) (Math.random() * 5 + 0);
			actual = listDimencionesPrimero.get(numeroAleatorio);
		}
		return actual;
	}

	public List<Noticias> getListNoticiasInicio() {
		return dao.getListNoticiasResumen();
	}

	public List<Noticias> getListNoticiasBanner() {
		return dao.getListNoticiasBanner();
	}

	boolean bandera = true;

	public String pintaImage(Noticias noticia) {
		if (dao.getHmBase64Images().containsKey(noticia.getIdNoticias())) {
			NoticiasImagenes img = dao.getHmBase64Images().get(noticia.getIdNoticias());
			String style = "";
			/**
			 * PRODUCCION
			 */
			String base64 = Base64.getEncoder().encodeToString(img.getImagen());
			style += " background-image: url(data:" + img.getContentType() + ";base64," + base64 + ");";

			/**
			 * PARA PRUEBAS DE DESARROLLO
			 * 
			 * String imgTest = ""; switch (noticia.getIdNoticias().intValue()) { case 1:
			 * imgTest = "resources/imagenes/vision_es.png"; break;
			 * 
			 * default: imgTest = "resources/imagenes/vision_es.png"; break; } style += "
			 * background-image: url(" + imgTest + ");";
			 */
			return style;
		} else {
			if (bandera) {
				bandera = false;
				return "background: black;";
			} else {
				bandera = true;
				return "background: #00537B;";
			}
		}
	}

	public void tipoCambioBanxico() {
		try {
			DgieWSLocator locator = new DgieWSLocator();
			locator.setDgieWSPortEndpointAddress(locator.getDgieWSPortAddress());
			DgieWSPortSoapBindingStub stub = (DgieWSPortSoapBindingStub) locator.getDgieWSPort();
			String response = stub.tiposDeCambioBanxico();
			tipoCambios = getParseaTipoCambio(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getParseaTipoCambio(String responseBanxicoXML) throws Exception {
		String tiposCambio = "BANXICO /  ";
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.parse(new ByteArrayInputStream(responseBanxicoXML.getBytes()));

		NodeList listNodoPrincipal = doc.getDocumentElement().getChildNodes();
		Node nodo;
		for (int i = 0; i < listNodoPrincipal.getLength(); i++) {
			nodo = listNodoPrincipal.item(i);
			if (nodo.getNodeName().equals("bm:DataSet")) {
				NodeList listRespuesta = nodo.getChildNodes();
				for (int j = 0; j < listRespuesta.getLength(); j++) {
					nodo = listRespuesta.item(j);
					if (nodo.getNodeName().equals("bm:Series")) {
						String titulo = nodo.getAttributes().getNamedItem("TITULO").getNodeValue();
						if (titulo.indexOf("E.U.A.") > 0) {
							if (titulo.indexOf("FIX") > 0)
								tiposCambio += " USD:";
							else
								continue;
						} else if (titulo.indexOf("Euro") > 0) {
							tiposCambio += " EUR:";
						} else
							continue;
						NodeList listObs = nodo.getChildNodes();
						for (int k = 0; k < listObs.getLength(); k++) {
							nodo = listObs.item(k);
							if (nodo.getNodeName().equals("bm:Obs")) {
								String obs_value = nodo.getAttributes().getNamedItem("OBS_VALUE").getNodeValue();
								tiposCambio += obs_value + " ";
							}
						}
					}
				}
			} else
				continue;
		}
		return tiposCambio;
	}

	public Noticias getMyNoticias() {
		return myNoticias;
	}

	public void setMyNoticias(Noticias myNoticias) {
		this.myNoticias = myNoticias;
	}

	public Integer getAniosCumple() {
		return aniosCumple;
	}

	public void setAniosCumple(Integer aniosCumple) {
		this.aniosCumple = aniosCumple;
	}

	public String getProximosCumples() {
		return proximosCumples;
	}

	public void setProximosCumples(String proximosCumples) {
		this.proximosCumples = proximosCumples;
	}

	public String getTipoCambios() {
		return tipoCambios;
	}

	public void setTipoCambios(String tipoCambios) {
		this.tipoCambios = tipoCambios;
	}

	public List<String> getListDimencionesPrimero() {
		return listDimencionesPrimero;
	}

	public void setListDimencionesPrimero(List<String> listDimencionesPrimero) {
		this.listDimencionesPrimero = listDimencionesPrimero;
	}

}
