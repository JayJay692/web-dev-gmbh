package de.abas.custom.owspart.spart.utils.esdk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;

public class EsdkProperties {

	private static Properties properties;

	private static void esdkProperties() {
		properties = new Properties();
		File propertiesFile = new File("./owspart/esdk.project.properties");
		
		try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFile))) {
			properties.load(inputStream);
		} catch (final FileNotFoundException e) {
			throw new RuntimeException("Could not find esdk.properties file");
		} catch (IOException e) {
			throw new RuntimeException("Could not load esdk.properties file");
		}
	}

	public static String getWorkdir() {
		esdkProperties();
		return properties.getProperty("WORKDIR");
	}

	public static String getAppId() {
		esdkProperties();
		return properties.getProperty("APPID");
	}

	public static String getUcmPlaceHolder() {
		esdkProperties();
		return properties.getProperty("UCM_PLACE_HOLDER");
	}

	public static File getUcmFile() throws EventException {
		esdkProperties();
		return new File(properties.getProperty("UCM_FILE"));
	}
}
