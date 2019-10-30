package com.land.front.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import com.land.back.util.AOCException;

/**
 * A Class class.
 * <P>
 * 
 * @author LANSOFT COMPANY
 */
public class UtilTimestamp {
	protected transient TimeZone timeZone = null;
	protected transient boolean validaFecha;

	public static String FORMATO_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static String FORMATO_DB2 = "YYYY-MM-DD-HH.mm.ss";
	public static String FORMATO_DATE = "dd-MM-yyyy";
	public static String FORMATO_DATE_SLASH = "dd/MM/yyyy";
	public static String FORMATO_TIME = "HH:mm";
	public static String FORMATO_TIME_COMPLETO = "HH:mm:ss";
	public static String FORMATO_AMPM = "hh:mm:ss";
	public static String FORMATO_AMPM_COMPLETO = "hh:mm:ss aa";
	public static String FORMATO_DATE_TIME = "dd-MM-yyyy HH:mm";
	public static String FORMATO_DATE_TIME_SLASH = "dd/MM/yyyy HH:mm";
	public static String FORMATO_DATE_COMPLETO = "dd-MM-yyyy HH:mm:ss";
	public static String DATE_CERO_1970 = "01-01-1970 00:00:00";
	public static String DATE_CERO_1969 = "31-12-1969 18:00:00";
	public static String DATE_ANIO_1969_CERO = "31-12-1969";
	public static String DATE_CERO_1970_UNO = "01-01-1970";
	public static String FORMATO_TIME_DATE = "HH:mm dd-MM-yyyy";
	public static String FORMATO_SDIA_FECHA_TRESDIGITOS = "dd/MMM/yyyy";
	public static String FORMATO_DIA_FECHA_TRESDIGITOS = "EEE/dd/MMM/yyyy";
	public static String FORMATO_DIA_FECHA_TRESDIGITOS_CONHORA = "EEE/dd/MMM/yyyy HH:mm";
	public static String FORMATO_ANO_MES_DIA = "yyyyMMdd";
	public static String FORMATO_TIME_JUNTO = "HHmmss";

	public static Locale ES_MX = new Locale("es", "MX");
	public static TimeZone AMERICA_MEXICO_CITY = TimeZone.getTimeZone("America/Mexico_City");
	public static TimeZone GMT = TimeZone.getTimeZone("GMT");

	protected static transient UtilTimestamp tiempo = null;

	/**
	 * Constructor
	 * 
	 * @param miTZ
	 */
	public UtilTimestamp(TimeZone miTZ) {
		this.timeZone = miTZ;
		this.validaFecha = false;
	}

	public UtilTimestamp(UtilTimestamp instance) {
		tiempo = instance;
	}

	public static synchronized UtilTimestamp getInstance() {
		// return getInstance(TimeZone.getTimeZone("America/Mexico_City"));
		if (tiempo == null) {
			tiempo = new UtilTimestamp(AMERICA_MEXICO_CITY);
		}
		return tiempo;
	}

	public String fechaToString(Date fecha) {
		return fechaToString(fecha, FORMATO_DATE_SLASH);
	}

	public String fechaToString(Date fecha, String formato) {
		return fechaToString(fecha, formato, ES_MX);
	}

	public String fechaToString(Date fecha, String formato, Locale locale) {
		SimpleDateFormat formatoDate = new SimpleDateFormat(formato, locale);
		formatoDate.setTimeZone(timeZone);
		return formatoDate.format(fecha);
	}

	public String fechaToStringCompleto(Date myDate) {
		return fechaToString(myDate, FORMATO_DATE_TIME);
	}

	public String fechaToQuery(Date fecha) {
		if (fecha != null) {
			String lSFecha = fechaToString(fecha, FORMATO_yyyyMMddHHmmss);
			return "TO_DATE('" + lSFecha + "','YYYYMMDDHH24MISS')";
		} else {
			return "NULL";
		}
	}

	public String fechaHoraToString(Date fecha, Date hora) {
		return fechaToStringCompleto(stringToDate(fechaToString(fecha) + " " + horaToString(hora)));
	}

	public String fechaHoraToStringRelativo(Date fecha, Date hora) {
		return fechaToStringCompleto(stringToDate(fechaToString(fecha) + " " + horaToStringRelativo(hora)));
	}

	public String horaToString(Timestamp timestamp) {
		return horaToString(new Date(timestamp.getTime()));
	}

	public String horaToString(Date myDate) {
		try {
			SimpleDateFormat formatoDate = new SimpleDateFormat(FORMATO_TIME);
			formatoDate.setTimeZone(timeZone);
			return formatoDate.format(myDate);
		} catch (Exception e) {
			System.out.println("Exception de fecha" + e);
		}
		return null;
	}

	public String horaToStringRelativo(Date myDate) {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat(FORMATO_TIME, ES_MX);
			myFormat.setTimeZone(GMT);

			String miString = myFormat.format(myDate);
			return miString;
		} catch (Exception e) {
			System.out.println("Exception de fecha" + e);
		}
		return null;
	}

	/**
	 * Regresa las horas (mayores a 24) y minutos de una fecha en formato HH:mm.
	 * 
	 * @param fecha
	 *            Una fecha en formato Date.
	 * @return La cadena en formato HH:mm representado por la fecha y la cadena
	 *         "null" si la fecha es nula.
	 * @throws Exception
	 *             En caso de no poder procesar las horas y minutos.
	 */
	public String tiempoToString(Date fecha) throws Exception {
		try {
			if (fecha == null)
				return "null";
			int mins = (int) Math.ceil(fecha.getTime() / (60 * 1000));
			return String.format("%02d", mins / 60) + ":" + String.format("%02d", mins % 60);
		} catch (Exception e) {
			System.out.println("Exception de fecha" + e);
		}
		return null;
	}

	/* Conversiones a String a Date */

	public Date stringToHora(String hora, boolean relativo) {
		if (relativo) {
			return stringToDate(hora, FORMATO_TIME, ES_MX, GMT);
		} else {
			return stringToDate(hora, FORMATO_TIME);
		}
	}

	public Date stringToHoraCompleto(String hora, boolean relativo) {
		if (relativo) {
			return stringToDate(DATE_ANIO_1969_CERO + " " + hora, FORMATO_DATE_COMPLETO);
		} else {
			return stringToDate(hora, FORMATO_TIME_COMPLETO);
		}
	}

	public Date stringToDate(String fecha) {
		return stringToDate(fecha, FORMATO_DATE_TIME);
	}

	public Date stringToDate(String fecha, String formato) {
		return stringToDate(fecha, formato, ES_MX);
	}

	public Date stringToDate(String fecha, String formato, Locale locale) {
		return stringToDate(fecha, formato, locale, timeZone);
	}

	public Date stringToDate(String fecha, String formato, Locale locale, TimeZone timeZone) {
		SimpleDateFormat formatoDate = new SimpleDateFormat(formato, locale);
		formatoDate.setTimeZone(timeZone);
		try {
			return formatoDate.parse(fecha);
		} catch (ParseException e) {
			System.out.println("No se pudo parsear: " + fecha);
			;
		}
		return null;
	}

	public Timestamp stringToTimestamp(String timestamp) {
		Date myDate = stringToDate(timestamp, FORMATO_DATE_COMPLETO);
		Timestamp miTimestamp = new Timestamp(myDate.getTime());
		return miTimestamp;
	}

	public Date fechaHoraToDate(Date fecha, Date hora) {
		return stringToDate(fechaToString(fecha) + " " + horaToString(hora));
	}

	public Date fechaHoraToDateRelativo(Date fecha, Date hora) {
		return stringToDate(fechaToString(fecha) + " " + horaToStringRelativo(hora));
	}

	/* Conversiones entre fechas */
	/**
	 * Se envia el Date y se le quita la fecha. "29-11-2012 05:21" ->
	 * "31-12-1969 05:21"
	 * 
	 * @param fecha
	 *            - La fecha que sera convertida
	 * @return
	 */
	public Date getDateSinFecha(Date fecha) {
		if (fecha == null) {
			return null;
		}
		String hora = horaToString(fecha);
		return stringToDate(DATE_ANIO_1969_CERO + " " + hora);
	}

	public Date getDateSinFechaUno(Date fecha) {
		if (fecha == null) {
			return null;
		}
		String hora = horaToString(fecha);
		return stringToDate(DATE_CERO_1970_UNO + " " + hora);
	}

	public static Date getDateCeroHoras(Date fecha) {
		if (fecha == null) {
			return null;
		}
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(fecha.getTime());
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}

	public static Date getDate24Horas(Date fec) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(fec.getTime());
		cl.set(Calendar.HOUR_OF_DAY, 23);
		cl.set(Calendar.MINUTE, 59);
		cl.set(Calendar.SECOND, 59);
		return cl.getTime();
	}

	public Date getDateCero(boolean isRelativo) {
		return stringToDate(DATE_CERO_1970, FORMATO_DATE_COMPLETO, ES_MX, isRelativo ? GMT : timeZone);
	}

	public Date sumaFechaHoras(Date unaFecha, String unasHoras) {
		Date miSuma = null;
		String misHoras = unasHoras.substring(0, 2);
		String misMinutos = unasHoras.substring(3, 5);
		Calendar miC = Calendar.getInstance();
		miC.clear();
		miC.setTimeInMillis(unaFecha.getTime());
		miC.add(Calendar.HOUR, Integer.parseInt(misHoras));
		miC.add(Calendar.MINUTE, Integer.parseInt(misMinutos));
		miSuma = miC.getTime();
		return miSuma;
	}

	public Date agregaFechaHoras(Date unaFecha, String unasHoras) {
		Date miSuma = null;
		String misHoras = unasHoras.substring(0, 2);
		String misMinutos = unasHoras.substring(3, 5);
		Calendar miC = Calendar.getInstance();
		miC.clear();
		miC.setTimeInMillis(unaFecha.getTime());
		miC.set(Calendar.HOUR_OF_DAY, Integer.parseInt(misHoras));
		miC.set(Calendar.MINUTE, Integer.parseInt(misMinutos));
		miSuma = miC.getTime();
		return miSuma;
	}

	public Date sumaMinutos(Date unaFecha, int unosMinutos, boolean unaSuma) {
		Calendar miCal = Calendar.getInstance();
		miCal.setTimeInMillis(unaFecha.getTime());
		if (!unaSuma)
			unosMinutos *= -1;
		miCal.add(Calendar.MINUTE, unosMinutos);
		return miCal.getTime();
	}

	public long calculaDiasDeDiff(Date cIni, Date cFin) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(cIni.getTime());
		c2.setTimeInMillis(cFin.getTime());
		return calculaDiasDeDiff(c1, c2);
	}

	public long calculaDiasDeDiff(Calendar cIni, Calendar cFin) {
		long horas = calculaHorasDeDiff(cIni, cFin);
		long dias = horas / 24;
		return dias;
	}

	public long calculaHorasDeDiff(Calendar cIni, Calendar cFin) {
		long horas = calculaMinutosDeDiff(cIni, cFin) / 60;
		return horas;
	}

	public long calculaMinutosDeDiff(Calendar cIni, Calendar cFin) {
		cIni.setTime(getDateCeroHoras(cIni.getTime()));
		cFin.setTime(getDateCeroHoras(cFin.getTime()));
		long diffMilis = cFin.getTime().getTime() - cIni.getTime().getTime();
		long mins = diffMilis / 60;
		mins = mins / 1000;
		return mins;
	}

	public long calculaMinutosDeDiffReal(Date cIni, Date cFin) {
		long diffMilis = cFin.getTime() - cIni.getTime();
		long mins = diffMilis / 60;
		mins = mins / 1000;
		return mins;

	}

	public int getIntDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public int getMinutosDesdeFechaCero(Date dia) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dia);
		int minutos = cal.get(Calendar.MINUTE);
		int horas = (cal.get(Calendar.HOUR_OF_DAY) - 18);
		int dias = (cal.get(Calendar.DATE) == 31 ? 0 : cal.get(Calendar.DATE));
		return minutos + (horas * 60) + (dias * 24 * 60);
	}

	// TODO - deprecated
	@Deprecated
	public String retornaHorasResta(Date unaFechaMayor, Date unaFechaMenor) {
		String miHora;
		long miResta = this.restaFechas(unaFechaMayor.getTime(), unaFechaMenor.getTime());
		long misHoras = ((miResta / 1000) / 60) / 60;
		long misMinutos = ((miResta / 1000) / 60) % 60;
		if (misHoras < 10)
			miHora = "0" + misHoras;
		else
			miHora = misHoras + "";
		miHora = miHora + ":";
		if (misMinutos < 10)
			miHora = miHora + "0" + misMinutos;
		else
			miHora = miHora + misMinutos;
		return miHora;
	}

	// TODO - deprecated
	@Deprecated
	public long restaFechas(long unaFechaMayor, long unaFechaMenor) {
		long miResultado;
		miResultado = unaFechaMayor - unaFechaMenor;
		return miResultado;
	}

	// TODO - revisar implementacion
	public long sumaFechas(long unaFechaMayor, long unaFechaMenor) {
		long miResultado;
		miResultado = unaFechaMayor + unaFechaMenor;
		return miResultado;
	}

	// TODO - revisar implementacion
	public Date restaDias(Timestamp fechaTimestamp, int i) {
		Date fechaDias = new Date((fechaTimestamp.getTime() - i * (86400000L)));
		return fechaDias;
	}

	public static Date armaDateHoraMilitar(Date fecha, String horaMilitar) throws AOCException {

		horaMilitar = horaMilitar.replaceAll(":", "");
		if (horaMilitar.length() != 4) {
			throw new AOCException("El campo de hora debe de ser de 4 caracteres numericos");
		}
		fecha = getDateCeroHoras(fecha);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		String hora = horaMilitar.substring(0, 2);
		String min = horaMilitar.substring(2, 4);
		try {
			int h = Integer.parseInt(hora);
			int m = Integer.parseInt(min);
			cal.set(Calendar.HOUR_OF_DAY, h);
			cal.set(Calendar.MINUTE, m);

		} catch (Exception e) {
			throw new AOCException("El campo de hora debe de ser de 4 caracteres numericos");
		}

		return cal.getTime();

	}

	public Date sumarMesesAFecha(Date f, int meses) {
		Calendar c = Calendar.getInstance();
		c.setTime(f);
		c.add(Calendar.MONTH, meses);
		return c.getTime();
	}

	public int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (ParseException ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (ParseException ex) {
		}

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public String getDiaSemanaString(Date date) {
		String dia = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 2:
			dia = "L";
			break;

		case 3:
			dia = "M";
			break;

		case 4:
			dia = "W";
			break;

		case 5:
			dia = "J";
			break;

		case 6:
			dia = "V";
			break;

		case 7:
			dia = "S";
			break;

		case 1:
			dia = "D";
			break;

		default:
			break;
		}

		return dia;
	}

	public String getFechaLetra(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);

		String cadena = cal.get(Calendar.DAY_OF_MONTH) + " de ";

		switch (cal.get(Calendar.MONTH)) {
		case 0:
			cadena += "Enero";
			break;
		case 1:
			cadena += "Febrero";
			break;
		case 2:
			cadena += "Marzo";
			break;
		case 3:
			cadena += "Abril";
			break;
		case 4:
			cadena += "Mayo";
			break;
		case 5:
			cadena += "Junio";
			break;
		case 6:
			cadena += "Julio";
			break;
		case 7:
			cadena += "Agosto";
			break;
		case 8:
			cadena += "Septiembre";
			break;
		case 9:
			cadena += "Octubre";
			break;
		case 10:
			cadena += "Noviembre";
			break;
		case 11:
			cadena += "Diciembre";
			break;
		}
		cadena += " del " + cal.get(Calendar.YEAR);

		return cadena.toUpperCase();
	}

	public String getFechaMesAnio(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);

		String cadena = "";

		switch (cal.get(Calendar.MONTH)) {
		case 0:
			cadena += "Ene.";
			break;
		case 1:
			cadena += "Feb.";
			break;
		case 2:
			cadena += "Mar.";
			break;
		case 3:
			cadena += "Abri.";
			break;
		case 4:
			cadena += "May.";
			break;
		case 5:
			cadena += "Jun.";
			break;
		case 6:
			cadena += "Jul.";
			break;
		case 7:
			cadena += "Ago.";
			break;
		case 8:
			cadena += "Sep.";
			break;
		case 9:
			cadena += "Oct.";
			break;
		case 10:
			cadena += "Nov.";
			break;
		case 11:
			cadena += "Dic.";
			break;
		}
		String anio = "" + cal.get(Calendar.YEAR);
		cadena += " - " + anio.substring(2, 4);

		return cadena.toUpperCase();
	}

	/**
	 * @param fecHoraSalida
	 * @param fORMATO_DATE_COMPLETO2
	 * @return
	 */
	public static String format(Date fecha, String formato) {
		if (fecha == null || formato == null) {
			return null;
		} else {
			UtilTimestamp t = UtilTimestamp.getInstance();
			return t.fechaToString(fecha, formato);
		}
	}

	public String getMesLetra(int mes) {
		String mesLetra = "";
		switch (mes) {
		case 0:
			mesLetra += "Enero";
			break;
		case 1:
			mesLetra += "Febrero";
			break;
		case 2:
			mesLetra += "Marzo";
			break;
		case 3:
			mesLetra += "Abril";
			break;
		case 4:
			mesLetra += "Mayo";
			break;
		case 5:
			mesLetra += "Junio";
			break;
		case 6:
			mesLetra += "Julio";
			break;
		case 7:
			mesLetra += "Agosto";
			break;
		case 8:
			mesLetra += "Septiembre";
			break;
		case 9:
			mesLetra += "Octubre";
			break;
		case 10:
			mesLetra += "Noviembre";
			break;
		case 11:
			mesLetra += "Diciembre";
			break;
		}
		return mesLetra;
	}

}