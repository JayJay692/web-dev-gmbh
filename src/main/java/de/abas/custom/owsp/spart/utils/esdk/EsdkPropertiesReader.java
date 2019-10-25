package de.abas.custom.owsp.spart.utils.esdk;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum EsdkPropertiesReader {
	INSTANCE;

	private final Properties properties;

	EsdkPropertiesReader() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("esdk.project.properties"));
		} catch (IOException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String getWorkdir() {
		return properties.getProperty("workdir");
	}

	public String getAppId() {
		return properties.getProperty("appid");
	}

	public String getCommandPlaceHolder() {
		return properties.getProperty("cmdPlaceHolder");
	}
}
