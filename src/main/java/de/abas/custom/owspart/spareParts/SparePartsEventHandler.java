package de.abas.custom.owspart.spareParts;

import de.abas.custom.owspart.events.ScreenEvents;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = ErsatzteileEditor.class)

@RunFopWith(EventHandlerRunner.class)

public class SparePartsEventHandler {
	
	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, ErsatzteileEditor head) throws EventException {
		new ScreenEvents(event).screenEnter();
	}

}
