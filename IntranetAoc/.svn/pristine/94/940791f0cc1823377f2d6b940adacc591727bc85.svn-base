package com.land.front.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.land.back.entities.Permiso;
import com.land.back.util.Constantes;
import com.land.front.dao.LoginDAO;
import com.land.front.util.UtilSesion;


@SessionScoped
@ManagedBean
public class BeanSession extends BeanComun {

	/**
	 * LGONZALEZ
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	private String correorestablecer;
	private String token;
	private String nombreUsuarioRestablece;
	private String nuevaContrasenia;
	private String nuevaContraseniaConfirma;
	private boolean invalido = true;
	private boolean restablecer = false;

	private String mesaje = "Identificate";
	private Integer progress = 5;
	private String ipDetectada;
	private Permiso permisoActual;
	private String mac;

	public static Locale SPANISH = new Locale("es", "Español");
	public static Locale ENGLISH = new Locale("en", "English");
	public static Locale localeActual = SPANISH;
	public static List<Locale> listLanguage;
	static {

		listLanguage = new ArrayList<Locale>();
		listLanguage.add(SPANISH);
		listLanguage.add(ENGLISH);
	}

	private LoginDAO dao;

	@PostConstruct
	public void init() {
		try {
			dao = new LoginDAO(null);
			dao.doConstantes();
			restablecer = false;
		} catch (Exception e) {
			e.printStackTrace();
			// alertError(texto.getString("generalInvalido")+"Error al iniciar
			// sistema.\n" + e.getMessage());
		}
	}

	public void localeChanged(ValueChangeEvent e) {
		Locale loc = (Locale) e.getNewValue();
		for (Locale language : listLanguage) {
			if (loc.getLanguage().equals(language.getLanguage())) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale(language);
				localeActual = language;
			}

		}

	}

	public void cambiaIdioma(String select) {
		if (ENGLISH.getLanguage().equals(select)) {
			localeActual = ENGLISH;
		}
		if (SPANISH.getLanguage().equals(select)) {
			localeActual = SPANISH;
		}
	}

	public String etiquetaVersion() {
		String versionEtiqueta = "RC";
		try {
			Properties properties = new Properties();
			properties.load(UtilSesion.getExternalContext().getResourceAsStream("/version/intranetaoc.version"));
			int guion = properties.toString().indexOf("-");
			int igual = properties.toString().indexOf("=");
			versionEtiqueta = "V" + properties.toString().substring(guion, igual);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return versionEtiqueta;
	}

	public String obtenMacFor() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getHeader("FORWARDED");
	}

	public String obtenMacReq() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getRemoteAddr();
	}

	public String obtenMacLocal() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getLocalAddr();
	}

	// public String obtenMac() {
	// HttpServletRequest request = (HttpServletRequest)
	// FacesContext.getCurrentInstance().getExternalContext()
	// .getRequest();
	// String ipAddress = request.getHeader("FORWARDED");
	// if (ipAddress == null) {
	// ipAddress = request.getRemoteHost();
	// }
	//
	// String response = "/template/sinpermisos.xhtml?faces-redirect=true";
	//
	// try {
	// if (ipAddress.equals(Constantes.IP_LOCAL)) {
	// InetAddress address = InetAddress.getLocalHost();
	// ipAddress = address.getHostAddress();
	// }
	// // Enumeration<NetworkInterface> eth =
	// // NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
	// // while (eth.hasMoreElements()) {
	// //
	// // NetworkInterface eth0 = eth.nextElement();
	// // eth0.isLoopback();
	// // System.out.println("Name " + eth0.getName());
	// // System.out.println("Display " + eth0.getDisplayName());
	// // byte[] mac = eth0.getHardwareAddress();
	// // StringBuilder macaddress = new StringBuilder();
	// // if (mac != null) {
	// // for (int i = 0; i < mac.length; i++) {
	// // macaddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ?
	// "-"
	// // : ""));
	// // }
	// // }
	// // System.out.println(macaddress.toString());
	// // System.out.println("etc" + eth);
	// // System.out.println("MAC " + macaddress.toString());
	// // System.out.println("########################");
	// //
	// // }
	//
	// ipDetectada = ipAddress;
	// permisoActual = dao.buscaPermisoIP(ipAddress);
	// dao.setEmpleadoActual(permisoActual.getEmpleado());
	// if (permisoActual == null)
	// return response;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return response;
	// }
	//
	// // try {
	// // InetAddress address = null;
	// // if (ipAddress.equals(Constantes.IP_LOCAL))
	// // address = InetAddress.getLocalHost();
	// // else {
	// // address = InetAddress.getByName(ipAddress);
	// // }
	// // NetworkInterface network = NetworkInterface.getByInetAddress(address);
	// // byte[] mac = network.getHardwareAddress();
	// //
	// // System.out.print("Current MAC address : ");
	// //
	// // StringBuilder macaddress = new StringBuilder();
	// // for (int i = 0; i < mac.length; i++) {
	// // macaddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ?
	// "-"
	// // : ""));
	// // }
	// // System.out.println(macaddress.toString());
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// System.out.println("ipAddress:" + ipAddress);
	// return "";
	// }

//	public URL urlApplet() {
//		return AppletMac.class.getResource("/");
//	}

	public String validaMac() {
		String sinpermisos = "/template/sinpermisos.xhtml?faces-redirect=true";
		try {
			if (permisoActual == null) {
				Permiso permiso = null;
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				String ipDesarrollo = request.getRemoteAddr();
				if (mac == null) {
					if (ipDesarrollo.equals(Constantes.IP_LOCAL)) {
						permiso = dao.buscaPermisoIP(ipDesarrollo);
						if (permiso == null)
							return sinpermisos;
						permisoActual = permiso;
						dao.setEmpleadoActual(permisoActual.getEmpleado());
						return "";
					} else {
						return sinpermisos;
					}
				}
				permiso = dao.buscaPermisoMac(mac);
				if (permiso == null)
					return sinpermisos;
				permisoActual = permiso;
				dao.setEmpleadoActual(permisoActual.getEmpleado());
				dao.iniciaConstantes();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sinpermisos;
		}
		return "";
	}

	private void limpiaCampos() {
		correorestablecer = "";
		nombreUsuarioRestablece = "";
		token = "";
		nuevaContrasenia = "";
		nuevaContraseniaConfirma = "";
		invalido = true;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTIMEOUT_INACTIVIDAD() {
		return Constantes.TIMEOUT_INACTIVIDAD;
	}

	public String getCorreorestablecer() {
		return correorestablecer;
	}

	public void setCorreorestablecer(String correorestablecer) {
		this.correorestablecer = correorestablecer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNuevaContrasenia() {
		return nuevaContrasenia;
	}

	public void setNuevaContrasenia(String nuevaContrasenia) {
		this.nuevaContrasenia = nuevaContrasenia;
	}

	public String getNuevaContraseniaConfirma() {
		return nuevaContraseniaConfirma;
	}

	public void setNuevaContraseniaConfirma(String nuevaContraseniaConfirma) {
		this.nuevaContraseniaConfirma = nuevaContraseniaConfirma;
	}

	public boolean isInvalido() {
		return invalido;
	}

	public String getNombreUsuarioRestablece() {
		return nombreUsuarioRestablece;
	}

	public void setNombreUsuarioRestablece(String nombreUsuarioRestablece) {
		this.nombreUsuarioRestablece = nombreUsuarioRestablece;
	}

	@Override
	public void datosIniciales() {
		// TODO Auto-generated method stub

	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public List<Locale> getListLanguage() {
		return listLanguage;
	}

	public void setListLanguage(List<Locale> listLanguage) {
		BeanSession.listLanguage = listLanguage;
	}

	public Locale getLocaleActual() {
		return localeActual;
	}

	public void setLocaleActual(Locale localeActual) {
		BeanSession.localeActual = localeActual;
	}

	public boolean isRestablecer() {
		return restablecer;
	}

	public void setRestablecer(boolean restablecer) {
		this.restablecer = restablecer;
	}

	public String getMesaje() {
		return mesaje;
	}

	public void setMesaje(String mesaje) {
		this.mesaje = mesaje;
	}

	public Permiso getPermisoActual() {
		return permisoActual;
	}

	public void setPermisoActual(Permiso permisoActual) {
		this.permisoActual = permisoActual;
	}

	public String getIpDetectada() {
		return ipDetectada;
	}

	public void setIpDetectada(String ipDetectada) {
		this.ipDetectada = ipDetectada;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}