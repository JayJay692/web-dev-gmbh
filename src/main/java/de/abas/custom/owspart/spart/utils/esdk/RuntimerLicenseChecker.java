package de.abas.custom.owspart.spart.utils.esdk;

import de.abas.eks.jfop.FOPException;
import de.abas.erp.axi.event.EventException;
import de.abas.esdk.client.api.license.LicenseChecker;

public class RuntimerLicenseChecker {
	private static final int EXIT_ERROR_CODE = 1;

	public static void validateLicense() throws FOPException, EventException {
		boolean isValidLicense = LicenseChecker.instance().validate();
		if (!isValidLicense) {
			throw new EventException(EXIT_ERROR_CODE);
		}		
	}
}
