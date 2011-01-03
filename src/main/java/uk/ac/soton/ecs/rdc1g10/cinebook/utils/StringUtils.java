package uk.ac.soton.ecs.rdc1g10.cinebook.utils;

public class StringUtils {
	public static boolean isEmpty(String string) {
		if(string != null && !"".equals(string)) {
			return false;
		}
		return true;
	}
}
