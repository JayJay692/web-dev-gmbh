package de.abas.custom.owspart.utils.esdk;

import java.io.*;
import java.util.Properties;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;

public class EsdkProperties {

	private static Properties properties;

	static {
		esdkProperties();
	}

	private static void esdkProperties() {
		properties = new Properties();

		try {
			properties.load(new InputStreamReader(new FileInputStream("./owspart/esdk.project.properties")));
		} catch (IOException e) {
			throw new RuntimeException("Could not load esdk.properties file", e);
		}
	}

	public static String getWorkdir() {
		return properties.getProperty("WORKDIR");
	}

	public static String getAppId() {
		return properties.getProperty("APPID");
	}

	public static String getUcmPlaceHolder() {
		return properties.getProperty("UCM_PLACE_HOLDER");
	}

	public static File getUcmFile() throws EventException {
		return new File(properties.getProperty("UCM_FILE"));
	}
}
