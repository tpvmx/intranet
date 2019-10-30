/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.land.back.util;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author LuisGlz &COPY;
 *         https://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
 */
public class ServicePassword implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int ITERATIONS = 4096;

	private byte[] encryptedPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String algorithm = Constantes.ALGORITMO_SHA512;
		int derivedKeyLength = 512;
		int iterations = ITERATIONS;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		return f.generateSecret(spec).getEncoded();
	}

	public byte[] getPasswordCrypted(String username, String password) throws Exception {
		return encryptedPassword(password, generateSaltByUsername(username.toUpperCase()));
	}

	private byte[] generateSaltByUsername(String username) {
		int size = username.length();
		int divide = Math.round(size / 2);
		String cad = (username.substring(0, 1) + username.substring(size - 1, size)).toUpperCase();
		byte[] salt = new byte[8];
		salt = cad.getBytes();
		return salt;
	}
}
