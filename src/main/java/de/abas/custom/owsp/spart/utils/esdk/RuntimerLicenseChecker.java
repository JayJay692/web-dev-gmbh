package de.abas.custom.owsp.spart.utils.esdk;

import de.abas.eks.jfop.FOPException;
import de.abas.erp.axi.event.EventException;
import de.abas.esdk.client.api.license.LicenseChecker;

public class RuntimerLicenseChecker {
	public static void validateLicense() throws FOPException, EventException {
		boolean isValidLicense = LicenseChecker.instance().validate();
		if (!isValidLicense) {
			throw new EventException(1);
		}		
	}
}
