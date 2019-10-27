package de.abas.custom.owspart.utils.esdk;

import java.io.File;

import de.abas.custom.owspart.utils.infosystem.FopEventTypeToken;
import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public class CustomizationAccess {
	// eventType= SE,FX, SV, BA ,BB... inputPlace = BEFORE oder AFTER
	String eventType, infosystemSearchWord, eventField, inputPlace;

	public void handleCustomFops(AbasObject head, DbContext ctx, FopEventTypeToken fopEventTypeToken, String eventFieldEnglish, String inputPlace) {
		this.eventType = fopEventTypeToken.getToken();
		this.eventField = eventFieldEnglish;
		this.inputPlace = inputPlace;
		
		String fopName = createFopName(head);
		File fopFile = new File(fopName);

		boolean showFopName = head.getBoolean("y" + EsdkProperties.getAppId()+"showfopname");
		if (showFopName) {
			ctx.out().println(fopName);
		}

		callIndividualFopWhenItExist(fopName, fopFile);
	}

	private String createFopName(AbasObject head) {
		String fopName = head.getString("swd").toUpperCase() + ".";
		if (!eventField.trim().isEmpty()) {
			fopName += eventField.toUpperCase() + ".";
		}
		fopName += eventType.toUpperCase() + "." + inputPlace.toUpperCase();
		fopName = EsdkProperties.getWorkdir() + "/" + fopName;
		return fopName;
	}

	private void callIndividualFopWhenItExist(String fopName, File fopFile) {
		if (fopFile.exists()) {
			try {
				FOe.input(fopName);
			} catch (Exception e) {
				throw new RuntimeException(
						"bei Ausführung eines individuellen Programms ist ein Fehler aufgetreten, \n Bitte wenden Sie sich an Ihren Administrator");
			}
		}
	}

}
