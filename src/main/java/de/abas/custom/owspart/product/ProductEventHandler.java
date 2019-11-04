package de.abas.custom.owspart.product;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = ProductEditor.class)

@RunFopWith(EventHandlerRunner.class)

public class ProductEventHandler {

	@ButtonEventHandler(field="yspartcreatespart", type = ButtonEventType.AFTER)
	public void yspartcreatespartAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx, ProductEditor head) throws EventException {
		new CreateNewSparePartButtonEvent(ctx, event).createSpartButtonAfter(head);
	}

}
