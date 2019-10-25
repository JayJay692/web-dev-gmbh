package de.abas.custom.owspart.spart.utils.esdk;

import java.io.File;

import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.db.DbContext;

public class CustomizationAccess {
	// eventType= SE,FX, SV, BA ,BB... inputPlace = BEFORE oder AFTER
	String eventType, infosystemSearchWord, eventField, inputPlace;
	boolean showFopName;
	private DbContext ctx;
	
	public CustomizationAccess(String infosystemSearchWord, boolean showFopName, DbContext ctx) {
		super();
		this.infosystemSearchWord = infosystemSearchWord;
		this.showFopName = showFopName;
		this.ctx = ctx;
	}

	public void handleCustomFops( String eventType, String eventField, String inputPlace) {
		this.eventType = eventType;
		this.eventField = eventField;
		this.inputPlace = inputPlace;
		
		String fopName = createFopName();
		File fopFile = new File(fopName);

		if (showFopName) {
			ctx.out().println(fopName);
		}

		callIndividualFopWhenItExist(fopName, fopFile);
	}

	private String createFopName() {
		String fopName = infosystemSearchWord.toUpperCase() + ".";
		if (!eventField.trim().isEmpty()) {
			fopName += eventField.toUpperCase() + ".";
		}
		fopName += eventType.toUpperCase() + "." + inputPlace.toUpperCase();
		fopName = EsdkPropertiesReader.INSTANCE.getWorkdir() + fopName;
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
