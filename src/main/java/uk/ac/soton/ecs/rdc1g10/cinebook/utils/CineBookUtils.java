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
	
	public static String getExtension(String filename) {
		int dotIndex = filename.lastIndexOf('.');
		return filename.substring(dotIndex + 1, filename.length());
	}

	public static String getMD5Hash(Object input)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		if(input instanceof String) {
			m.update(((String)input).getBytes("UTF-8"));
		} else if (input instanceof byte[]) {
			m.update((byte [])input);
		} else {
			throw new UnsupportedEncodingException("Unkown object type for hashing.");
		}
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hash = bigInt.toString(16);
		while (hash.length() < 32) {
			hash = "0" + hash;
		}
		return hash;
	}
}
