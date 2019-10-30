package com.land.back.util;

import java.util.UUID;

public class UtilToken {

	public static UtilToken getInstance() {
		return new UtilToken();
	}

	public String generaToken(Long idUsuario) {
		String token = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "") + idUsuario;
		return token;
	}

	public String generaTokenWorkflow() {
		String token = UUID.randomUUID().toString().toUpperCase();
		return token;
	}

	public static void main(String[] args) {
		UtilToken.getInstance().generaToken(0L);
	}

}
