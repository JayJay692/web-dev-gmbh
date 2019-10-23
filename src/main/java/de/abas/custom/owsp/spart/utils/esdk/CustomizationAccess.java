package de.abas.custom.owsp.spart.utils.esdk;

import java.io.File;

import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.db.DbContext;

public class CustomizationAccess {
	// eventType= SE,FX, SV, BA ,BB... inputPlace = BEFORE oder AFTER
	String eventType, infosystemSearchWord, eventField, inputPlace;
	boolean showFopName;

	public CustomizationAccess(String eventType, String infosystemSearchWord, String eventField, String inputPlace,
			DbContext ctx, boolean showFopName) {
		super();
		this.eventType = eventType;
		this.infosystemSearchWord = infosystemSearchWord;
		this.eventField = eventField;
		this.inputPlace = inputPlace;
		this.showFopName = showFopName;
	}

	public void handleCustomFops(DbContext ctx) {
		String fopName = createFopName();
		File fopFile = new File(fopName);

		if (showFopName) {
			ctx.out().println(fopName);
		}

		callIndividualFopWhenItExist(fopName, fopFile);
	}

	private String createFopName() {
		// TODO add workingDirectory to propertiesFile
		String workingDirectory = "owsp";
		String fopName;

		fopName = infosystemSearchWord.toUpperCase() + ".";
		if (eventField != "") {
			fopName += eventField.toUpperCase() + ".";
		}
		fopName += eventType.toUpperCase() + "." + inputPlace.toUpperCase();
		fopName = workingDirectory + fopName;

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
