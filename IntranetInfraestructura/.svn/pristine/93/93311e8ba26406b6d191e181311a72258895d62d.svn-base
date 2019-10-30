package com.land.back.util;

import java.lang.reflect.Field;
import java.util.Properties;

public class Constantes {

	/**
	 * Nombres de objetos en Session HTTP
	 */
	public static String ALGORITMO_SHA512 = "PBKDF2WithHmacSHA512";

	/**
	 * Nombres de objetos en Request
	 */
	public static String REQUEST_CLASS_NAME = "class_name";
	public static String REQUEST_WF_REALIZAR_BUSQUEDA_DETALLE = "wf_realizar_busqueda_detalle";

	/**
	 * Constantes funcionalidad
	 */
	public static final int AGREGAR = 1;
	public static final int MODIFICAR = 2;
	public static final int ELIMINAR = 3;

	public static final Integer NOTIFICACIONES_PENDIENTES = 1;
	public static final Integer SOLICITUDES_PENDIENTES = 2;

	public static final Integer ACTIVO = 1;
	public static final Integer INACTIVO = 0;

	public static final String ACTIVO_DES = "Activo";
	public static final String INACTIVO_DES = "Inactivo";

	public static final String BUSQUEDA_GENERICA_ENTERO = "ENTERO";
	public static final String BUSQUEDA_GENERICA_ALFANUMEICO = "ALFANUMERICO";

	public static String STYLE_BOLD = "bold";
	public static String STYLE_VACIO = "vacio";

	public static int EXPORT_TYPE_PDF = 0;
	public static int EXPORT_TYPE_MSEXCEL = 1;

	/**
	 * CONSTANTES MAPPING DB AOC
	 */
	public static int TIMEOUT_INACTIVIDAD = 900;// 15Min Valor en
												// Milisegundos = 1;
	public static String HTTP_AOC = "localhost";
	public static String EMAIL_CONTACTO_LANDSOFT = "contacto@landsoft.com.mx";

	public static final String SESSION_AOC = "userAoc";

	/**
	 * Constantes IMAGENES
	 */
	public static final int IMAGEN_PRINCIPAL = 1;
	public static final int IMAGEN_SECUNDARIA = 2;
	public static final int IMAGEN_3 = 3;
	public static final int IMAGEN_4 = 4;
	public static final int IMAGEN_5 = 5;
	
	public static final String IP_LOCAL = "0:0:0:0:0:0:0:1";
	
	/**
	 * CONSTANTES TIPO NOTICIA
	 */
	public static final int TIPO_PRINCIPAL = 1;
	public static final int TIPO_INTERES = 2;
	/**
	 * Constantes VACACIONES
	 */
	public static final int ESTATUS_DESPACHADO = 1; 
	public static final int ESTATUS_SINDESPACHAR = 0; 

	/**
	 * CONSTANTE
	 */
	public static final int PDF_PROTECCION = 1;
	public static final int PDF_POLITICAS_VIAJE = 2;
	public static final int PDF_POLITICAS_CAJA = 3;
	public static final int PDF_POLITICAS_VESTIMENTA = 4;
	public static final int PDF_POLITICAS_ACTIVO = 5;
	public static final int PDF_POLITICAS_CONVIVENCIA = 6;
	public static final int PDF_POLITICAS_PRODUCTOS = 7;
	public static final String TITULO = "TOP VICTORY ELECTRONICS DE MÉXICO, S.A. DE C.V.";
	
	public static final String FOLIOS_FORMULARIO = "FORMULARIOS";

	/**
	 * TODAS LAS CONSTATES QUE NO LLEVEN FINAL, SE PODRAN LLENAR DESDE LA TABLA
	 * DE CONSTANTES DE LA DB LGONZALEZ
	 * 
	 * 
	 * ______CONSTANTES___________ LEIDAS DE LA BASE DE DATOS
	 * 
	 */

	public static String PRUEBA_DB = "..";
	public static int NUM_EMPLEADO_RH = 38000059;

	/**
	 * LGONZALEZ LECTURA DE CONSTANTES
	 */
	public static final int ESTATUSCARGA = 1;


	public static void setConstantes(Properties properties) {
		int j = 0;
		try {
			Class<?> miClase = new Constantes().getClass();
			Field[] losCampos = miClase.getDeclaredFields();
			String propiedad = "";
			Object valor;
			Object temporal;

			for (Field campo : losCampos) {
				try {
					propiedad = campo.getName();
					// valor = properties.getProperty(propiedad);

					valor = properties.get(propiedad);
					if (valor == null) {
						continue;
					}
					j++;
					temporal = campo.get(miClase);
					if (temporal instanceof Integer) {
						campo.setInt(miClase, Integer.parseInt(valor.toString()));
					}
					if (temporal instanceof Long) {
						campo.setLong(miClase, new Long(Long.parseLong(valor.toString())));
					}
					if (temporal instanceof Double) {
						campo.setDouble(miClase, Double.parseDouble(valor.toString()));
					}
					if (temporal instanceof String) {
						campo.set(miClase, valor);
					}
					if (temporal instanceof Boolean) {
						campo.set(miClase, Boolean.valueOf(valor.toString()));
					}
				} catch (Exception e) {
					System.out.println(
							"El valor y el tipo de dato para la constante " + propiedad + " no son compatibles");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error al obtener las propiedades." + e);
		}
		System.out.println(j + "propiedades procesadas con Exito");
	}
}
