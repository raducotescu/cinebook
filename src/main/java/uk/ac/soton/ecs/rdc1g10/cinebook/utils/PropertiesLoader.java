package uk.ac.soton.ecs.rdc1g10.cinebook.utils;

import java.net.URL;
import java.util.Properties;

public class PropertiesLoader {

	public static Properties getPropertiesFromFile(String propertiesFile) {
		Properties p = new Properties();
		ClassLoader loader = PropertiesLoader.class.getClassLoader();
		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}
		URL url = loader.getResource(propertiesFile);
		try {
			p.load(url.openStream());
		} catch (Exception e) {
			System.err.println("Could not load configuration file: "
					+ propertiesFile);
		}
		return p;
	}
}