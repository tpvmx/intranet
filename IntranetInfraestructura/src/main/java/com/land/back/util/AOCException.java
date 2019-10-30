package com.land.back.util;

public class AOCException extends Exception {
	/**
	 * @author LGONZALEZ
	 */
	private TypeAocException type = TypeAocException.CONTROLADO;
	private String action;

	public AOCException(Throwable e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;

	public AOCException(String mensaje) {
		super(mensaje);
	}

	public AOCException(String mensaje, String action) {
		super(mensaje);
		this.action = action;
	}

	public AOCException(String mensaje, TypeAocException type) {
		super(mensaje);
		this.type = type;
	}

	public AOCException(String mensaje, Exception e) {
		super(mensaje, e);
	}

	public String getMensajeControlado() {
		return super.getMessage();
	}

	public TypeAocException getType() {
		return type;
	}

	public String getAction() {
		return action;
	}
}