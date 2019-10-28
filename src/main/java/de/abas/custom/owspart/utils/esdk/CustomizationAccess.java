package de.abas.custom.owspart.utils.esdk;

import java.io.File;

import de.abas.custom.owspart.utils.infosystem.FopEventTypeToken;
import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public class CustomizationAccess {
	public static final int BEGINNING_OF_FOPNAME = 0;
	// eventType= SE,FX, SV, BA ,BB... inputPlace = BEFORE oder AFTER
	private String eventType, eventField;

	public CustomizationAccess(FopEventTypeToken eventType, String eventField) {
		this.eventType = eventType.getToken();
		this.eventField = eventField;
	}

	public void handleCustomFops(AbasObject head, DbContext databaseContext, String inputPlace) {
		
		String fopName = createFopName(head, inputPlace);
		File fopFile = new File(fopName);

		if (head.getBoolean(String.format("y%sshowfopname", EsdkProperties.getAppId()))) {
			databaseContext.out().println(fopName);
		}

		callIndividualFopWhenItExist(fopName, fopFile);
	}

	private String createFopName(AbasObject head, String inputPlace) {
		StringBuilder fopName = new StringBuilder();
		fopName.append(head.getString("swd").toUpperCase()).append(".");
		if (!eventField.trim().isEmpty()) {
			fopName.append(eventField.toUpperCase()).append(".");
		}
		fopName.append(eventType.toUpperCase()).append(".").append(inputPlace.toUpperCase());
		fopName.insert(BEGINNING_OF_FOPNAME, "/").insert(BEGINNING_OF_FOPNAME, EsdkProperties.getWorkdir());
		return fopName.toString();
	}

	private void callIndividualFopWhenItExist(String fopName, File fopFile) {
		if (fopFile.exists()) {
			try {
				FOe.input(fopName);
			} catch (Exception e) {
				throw new RuntimeException(
						"bei Ausführung eines individuellen Programms ist ein Fehler aufgetreten, \n Bitte wenden Sie sich an Ihren Administrator", e);
			}
		}
	}

}
