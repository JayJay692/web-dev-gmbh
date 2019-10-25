package de.abas.custom.owspart.spart.infosystems.spureason;

import de.abas.custom.owspart.spart.infosystems.InfosystemEventHandler;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.schema.infosystem.Infosystem;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = UsageReasonSparePart.class, row = UsageReasonSparePart.Row.class)

@RunFopWith(EventHandlerRunner.class)

public class UsageReasonSparePartEventHandler extends InfosystemEventHandler {

	DbContext ctx;

	public UsageReasonSparePartEventHandler(DbContext ctx, Infosystem head) {
		super(ctx, head);
	}

	@ButtonEventHandler(field = "start", type = ButtonEventType.AFTER)
	public void startAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head)
			throws EventException {
		
	}

	@FieldEventHandler(field = "yspartdatefrom", type = FieldEventType.EXIT)
	public void yspartdatefromExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws EventException {
		// TODO Auto-generated method stub
	}

	@FieldEventHandler(field = "yspartdateto", type = FieldEventType.EXIT)
	public void yspartdatetoExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws EventException {
		// TODO Auto-generated method stub
	}

	@ButtonEventHandler(field = "ysparttsetusage", type = ButtonEventType.AFTER, table = true)
	public void ysparttsetusageAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head, UsageReasonSparePart.Row currentRow) throws EventException {
		// TODO Auto-generated method stub
	}

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head)
			throws EventException {
		// TODO Auto-generated method stub
	}

}
