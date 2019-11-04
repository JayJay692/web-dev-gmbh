package de.abas.custom.owspart.configuration;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = KonfigurationEditor.class)

@RunFopWith(EventHandlerRunner.class)

public class ConfigurationEventHandler {

	@ButtonEventHandler(field = "yspartfinishconfig", type = ButtonEventType.AFTER)
	public void yspartfinishconfigAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			KonfigurationEditor head) throws EventException {
		new ButtonEventsConfiguration(ctx, head).finishConfigButtonAfter();
	}

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, KonfigurationEditor head)
			throws EventException {
		new ScreenEventsConfiguration(ctx, event, head).screenEnter();
	}

}
