package com.land.back.util;

import org.apache.log4j.Logger;

/**
 * Clase que permite imprimir mensajes en el log
 *
 * @author  Salvador López
 * @version 1.0, 2013-10-15mm
 */
public class PrintLog {
	/*
	 * Objeto para el registro del log
	 */
	//private static final Log log = LogFactory.getLog(PrintLog.class);
	protected static Logger log = Logger.getLogger("WebService-wsUniversalPLD");
	/* Constante con el nombre del servicio */
	private static final String SERVICE_NAME = "WebService-wsUniversalPLD";
    
    /** Constructor de la clase */
    public PrintLog() {
    }
      
	/**
	 * Permite imprimir mensaje indicando que algun m?todo a iniciado
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   metodo Nombre del m?todo iniciado
	 */
    public void startMethod(String metodo) {
    	log.info(SERVICE_NAME + " Inicia metodo:[" + metodo + "]");
	}
        
	/**
	 * Permite imprimir mensaje indicando que algun m?todo a terminado
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   metodo Nombre del m?todo terminado
	 */
	public void endMethod(String metodo) {
    	log.info(SERVICE_NAME + " Termina metodo:[" + metodo + "]");
	}

	/**
	 * Permite imprimir mensaje informativo
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   mensaje Mensaje a imprimir
	 */
	public void logInfo(String mensaje) {
		log.info(SERVICE_NAME + " " + mensaje);
	}
	
	/**
	 * Permite imprimir una excepci?n y la clase donde se origin?
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   clase     Clase donde se origin? la excepci?n
     * @param   mensaje   Mensaje con el error
	 */
	public void logError(String clase, String mensaje) {
		log.info(SERVICE_NAME + " Excepción en la clase:[" + clase + "] descripción: " + mensaje);                
	}

	/**
	 * Permite imprimir un warning y la clase donde se origin?
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   clase      Clase donde se origin? el warning
     * @param   mensaje    Mensaje con el warning
	 */
	public void logWarn(String clase, String mensaje) {
		log.info(SERVICE_NAME + " Atención en la clase:[" + clase + "] descripción: " + mensaje);                
	}

	/**
	 * Permite imprimir un error fatal y la clase donde se origin?
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   clase   Clase donde se origin? el error fatal
     * @param   mensaje Mensaje con el error
	 */
	public void logFatal(String clase, String mensaje) {
		log.info(SERVICE_NAME + " Error fatal en la clase:[" + clase + "] descripción: " + mensaje);                
	}

	/**
	 * Permite imprimir mensaje de debug
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
     * @param   mensaje Mensaje a imprimir
	 */
	public void logDebug(String mensaje) {
		log.info(SERVICE_NAME + " " + mensaje);
	}

	/**
	 * Permite imprimir mensaje simple
	 *
	 * @author  Salvador López
	 * @version 1.0, 2013-10-15
	 * @param   mensaje Mensaje a imprimir
	 */
	public void println(String mensaje){
		log.info(mensaje);
	}
}
