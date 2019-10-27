package de.abas.custom.owspart.infosystems.spureason;

import de.abas.custom.owspart.infosystems.spureason.buttonevents.StartButtonAfter;
import de.abas.custom.owspart.infosystems.spureason.fieldevents.DateFromFieldExit;
import de.abas.custom.owspart.infosystems.spureason.fieldevents.DateToFieldExit;
import de.abas.custom.owspart.infosystems.spureason.screenevents.ScreenEnter;
import de.abas.custom.owspart.utils.infosystem.FopEventTypeToken;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = UsageReasonSparePart.class, row = UsageReasonSparePart.Row.class)

@RunFopWith(EventHandlerRunner.class)

public class UsageReasonSparePartEventHandler {

	@ButtonEventHandler(field = "start", type = ButtonEventType.AFTER)
	public void startAfter(Event<ButtonEventType> event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws Exception {
		new StartButtonAfter().handleEvent(event, FopEventTypeToken.BUTTON_AFTER, screenControl, ctx, head, null);
	}

	@FieldEventHandler(field = "yspartdatefrom", type = FieldEventType.EXIT)
	public void yspartdatefromExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws Exception {
		new DateFromFieldExit().handleEvent(event, FopEventTypeToken.FIELD_EXIT, screenControl, ctx, head, null);
	}

	@FieldEventHandler(field = "yspartdateto", type = FieldEventType.EXIT)
	public void yspartdatetoExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws Exception {
		new DateToFieldExit().handleEvent(event, FopEventTypeToken.FIELD_EXIT, screenControl, ctx, head, null);
	}

	@ButtonEventHandler(field = "ysparttsetusage", type = ButtonEventType.AFTER, table = true)
	public void ysparttsetusageAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head, UsageReasonSparePart.Row currentRow) throws Exception {
		new StartButtonAfter().handleEvent(event, FopEventTypeToken.BUTTON_AFTER, screenControl, ctx, head, currentRow);
	}

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head)
			throws Exception {
		new ScreenEnter().handleEvent(event, FopEventTypeToken.SCREEN_ENTER, screenControl, ctx, head, null);
	}

}
