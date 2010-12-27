package com.cotescu.radu.cinebook.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class PasswordDigester {
	
	private static final int ITER = 1000;
	
	public static String getHash(String password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.reset();
		digest.update(salt.getBytes("UTF-8"));
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for(int i = 0; i < ITER; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return byteToBase64(input);
	}
	
	public static String byteToBase64(byte[] input) {
		// null defines the line separator
		Base64 encoder = new Base64(76, null);
		return encoder.encodeToString(input);
	}
}