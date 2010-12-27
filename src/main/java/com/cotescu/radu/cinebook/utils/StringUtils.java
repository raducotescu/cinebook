package com.cotescu.radu.cinebook.utils;

public class StringUtils {
	public static boolean isEmpty(String string) {
		if(string != null && !"".equals(string)) {
			return false;
		}
		return true;
	}
}
