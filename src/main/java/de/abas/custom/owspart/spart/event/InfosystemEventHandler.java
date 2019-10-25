package de.abas.custom.owspart.spart.event;

import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.infosystem.Infosystem;

public abstract class InfosystemEventHandler {
	protected void registerCustomFOPsInFieldEvent(EsdkFieldEventLogic<Infosystem> logic, DbContext ctx, Infosystem head,
			FieldEvent event, String eventName, String fieldname, ScreenControl screenControl) throws Exception {

		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(), fieldname, "before", head.getBoolean("yqmappshowfopname"));
		logic.execute(head, event, screenControl, ctx);
		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(), fieldname, "after", head.getBoolean("yqmappshowfopname"));
	}

	protected void registerCustomFOPsInButtonEvent(EsdkButtonEventLogic<Infosystem> logic, DbContext ctx,
			Infosystem head, ButtonEvent event, String eventName, String fieldname, ScreenControl screenControl)
			throws Exception {
		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(),
		// fieldname, "before", head.getBoolean("yqmappshowfopname"));
		logic.executeButtonLogic(head, event, screenControl, ctx);
		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(),
		// fieldname, "after", head.getBoolean("yqmappshowfopname"));
	}

	protected void registerCustomFOPsInScreenEvent(EsdkScreenEventLogic<Infosystem> logic, DbContext ctx,
			Infosystem head, ScreenEvent event, String eventName, ScreenControl screenControl) throws Exception {
		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(), "",
		// "before", head.getBoolean("yqmappshowfopname"));
		logic.execute(head, event, screenControl, ctx);
		// EsdkHelper.createCustomizingFo(eventName, ctx, head.getSwd(), "",
		// "after", head.getBoolean("yqmappshowfopname"));
	}
}
