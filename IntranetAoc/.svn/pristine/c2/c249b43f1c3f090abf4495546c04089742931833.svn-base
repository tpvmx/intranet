package com.land.front.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.land.back.entities.Empleado;
import com.land.back.util.AOCException;
import com.land.back.util.Constantes;
import com.land.back.util.UtilRegEx;
import com.land.front.util.CreateJasper;
import com.land.front.util.UtilDownload;
import com.land.front.util.UtilSesion;

public abstract class BeanComun implements Serializable {

	private static final long serialVersionUID = 1L;

	protected SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat formatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	protected DecimalFormat formatDecimal2 = new DecimalFormat("$ ###,###,###,###,###,###,###,###,###.00");
	protected Empleado empleadoActual = null;

	public abstract void datosIniciales();

	protected Boolean lanzaReportePDF(String pathReporte, Map<String, Object> paramsHeader,
			List<Map<String, Object>> listDetailsReport, String nombreDownload) throws Exception {

		if (nombreDownload.indexOf(".") != -1) {
			throw new AOCException(getTexto("dicArchivosinextencion"));
		}
		byte[] byteArr = CreateJasper.createByteArrayPdf(paramsHeader, listDetailsReport, pathReporte);

		UtilDownload.exportPdf(byteArr, nombreDownload + ".pdf");
		return Boolean.TRUE;
	}

	public void alertGlow(String header, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(header, mensaje));
	}

	public void alertInfo(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, (getTexto("dicInformacion")), mensaje));
	}

	public void alertInfoHeader(String header, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, header, mensaje));
	}

	public void alertAlerta(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, (getTexto("dicAlerta")), mensaje));
	}

	public void alertError(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "!Error!", mensaje));
	}

	public String getTexto(String key) {
		FacesContext context = UtilSesion.getFacesContext();
		return context.getApplication().evaluateExpressionGet(context, "#{texto['" + key + "']}", String.class);
	}

	@SuppressWarnings("static-access")
	public Object getSessionObj(String s) {
		Map<String, Object> mSession = FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
				.getSessionMap();
		return mSession.get(s);
	}

	public void showDialog(Map<String, List<String>> params, String nombreDialog) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("resizable", false);
		options.put("dragable", false);
		options.put("width", 750);
		options.put("height", 400);
		options.put("contentWidth", 715);
		options.put("contentHeight", 370);
		options.put("includeViewParams", true);
		/// options.put("headerElement", "customheader");

		RequestContext request = RequestContext.getCurrentInstance();
		request.openDialog(nombreDialog, options, params);
	}

	public void showBusquedaComun(String className) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> values = new ArrayList<String>();
		values.add(className);
		params.put(Constantes.REQUEST_CLASS_NAME, values);
		showDialog(params, "busquedaComun");
	}

	public String etiquetaVersion() {
		String versionEtiqueta = "RC";
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Properties properties = new Properties();
			properties.load(externalContext.getResourceAsStream("/version/scas.version"));
			System.out.println(properties.toString());
			int guion = properties.toString().indexOf("-");
			int igual = properties.toString().indexOf("=");
			versionEtiqueta = "V" + properties.toString().substring(guion, igual);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return versionEtiqueta;
	}

	protected Integer convertEstatus(Boolean status) {
		return status.booleanValue() ? 1 : 0;
	}

	protected Boolean convertEstatus(int status) {
		return status == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	//protected Boolean lanzaReportePDF(String pathReporte, Map<String, Object> paramsHeader,
		//	List<Map<String, Object>> listDetailsReport, String nombreDownload) throws Exception {

		//if (nombreDownload.indexOf(".") != -1) {
			//throw new AOCException("Solo indicar el nombre del archivo, sin extencion.");
		//}
		// byte[] byteArr = CreateJasper.createByteArrayPdf(paramsHeader,
		// listDetailsReport, pathReporte);
		//
		// UtilDownload.exportPdf(byteArr, nombreDownload + ".pdf");
		//return Boolean.TRUE;
	//}

	protected Boolean lanzaReporteExcel(String pathReporte, Map<String, Object> paramsHeader,
			List<Map<String, Object>> listDetailsReport, String nombreDownload) throws Exception {

		if (nombreDownload.indexOf(".") != -1) {
			throw new AOCException("Solo indicar el nombre del archivo, sin extencion.");
		}
		// byte[] byteArr = CreateJasper.createByteArrayPdf(paramsHeader,
		// listDetailsReport, pathReporte);
		//
		// UtilDownload.exportExcel(byteArr, nombreDownload + ".xls");
		return Boolean.TRUE;
	}

	protected Boolean valiteRegex(String valor, UtilRegEx regex) {
		Pattern pattern = Pattern.compile(regex.getValor());
		if (!pattern.matcher(valor).matches()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	protected String cerosIzquierda(int dato, int numeroCeros) {
		return String.format("%0" + numeroCeros + "d", dato);
	}
	
	protected String espaciosIzquierda(String dato, int numeroEspacios) {
		return String.format("%" + numeroEspacios + "s", dato);
	}
	
	protected String espaciosIzquierdaDecimal(Double dato, int numeroEspacios) {
		return String.format("%" + numeroEspacios + "s", dato);
	}
	
}
