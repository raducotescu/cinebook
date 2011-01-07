package uk.ac.soton.ecs.rdc1g10.cinebook.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CineBookUtils {
	public static boolean isEmptyString(String string) {
		return null == string || "".equals(string);
	}

	public static String getFileNameWithoutExtension(String filename) {
		int dotIndex = filename.lastIndexOf('.');
		return filename.substring(0, dotIndex);
	}

	public static String getMD5Hash(String input)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(input.getBytes("UTF-8"));
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hash = bigInt.toString(16);
		while (hash.length() < 32) {
			hash = "0" + hash;
		}
		return hash;
	}
}
