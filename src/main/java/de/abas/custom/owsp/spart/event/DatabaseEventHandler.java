package de.abas.custom.owsp.spart.event;

import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.EditorObject;

public abstract class DatabaseEventHandler {
	protected void registerCustomFOPsInFieldEvent(EsdkFieldEventLogic<EditorObject> logic, DbContext ctx, EditorObject head, FieldEvent event, String eventName, String fieldname, ScreenControl screenControl) throws Exception {
		logic.execute(head, event, screenControl, ctx);
	}
	
	protected void registerCustomFOPsInButtonEvent(EsdkButtonEventLogic<EditorObject> logic, DbContext ctx, EditorObject head, ButtonEvent event, String eventName, String fieldname, ScreenControl screenControl) throws Exception {
		logic.execute(head, event, screenControl, ctx);
	}
	
	protected void registerCustomFOPsInScreenEvent(EsdkScreenEventLogic<EditorObject> logic, DbContext ctx, EditorObject head, ScreenEvent event, String eventName, ScreenControl screenControl) throws Exception {
		logic.execute(head, event, screenControl, ctx);
	}
}
