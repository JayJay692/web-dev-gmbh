package de.abas.custom.owspart.spart.infosystems;

import de.abas.custom.owspart.spart.utils.esdk.CustomizationAccess;
import de.abas.custom.owspart.spart.utils.esdk.EsdkProperties;
import de.abas.custom.owspart.spart.utils.esdk.RuntimerLicenseChecker;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.infosystem.Infosystem;

public abstract class InfosystemEventHandler {
	CustomizationAccess customizationAcess;

	public InfosystemEventHandler(DbContext ctx, Infosystem head) {
		super();
		boolean showFopName = head.getBoolean("y" + EsdkProperties.getAppId() + "showfopname");
		customizationAcess = new CustomizationAccess(head.getSwd(), showFopName, ctx);
	}

	protected void registerCustomFOPsInFieldEvent(EsdkFieldEventLogic<Infosystem> logic, DbContext ctx, Infosystem head,
			FieldEvent event, String eventName, String fieldname, ScreenControl screenControl) throws Exception {
		
		// TODO muss geprüft werden ob mein Ansatz hier sich damit verträgt, dass UsageReasonSparePartEvenhandler hiervon erben soll
		// entweder nicht über vererbeung lösen oder wieder auf Patricks Version zurück schrauben
		customizationAcess.handleCustomFops(eventName, fieldname, "before");
		logic.execute(head, event, screenControl, ctx);
		customizationAcess.handleCustomFops(eventName, fieldname, "after");
	}

	protected void registerCustomFOPsInButtonEvent(EsdkButtonEventLogic<Infosystem> logic, DbContext ctx,
			Infosystem head, ButtonEvent event, String eventName, String fieldname, ScreenControl screenControl)
			throws Exception {
		customizationAcess.handleCustomFops(eventName, fieldname, "before");
		logic.executeButtonLogic(head, event, screenControl, ctx);
		customizationAcess.handleCustomFops(eventName, fieldname, "after");
	}

	protected void registerCustomFOPsInScreenEvent(EsdkScreenEventLogic<Infosystem> logic, DbContext ctx,
			Infosystem head, ScreenEvent event, String eventName, ScreenControl screenControl) throws Exception {
		RuntimerLicenseChecker.validateLicense();
		customizationAcess.handleCustomFops(eventName, "", "before");
		logic.execute(head, event, screenControl, ctx);
		customizationAcess.handleCustomFops(eventName, "", "after");
	}
}
