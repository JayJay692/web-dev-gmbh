package de.abas.custom.owsp.spart.event;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public interface EsdkButtonEventLogic<T extends AbasObject> {
	void execute(T head, ButtonEvent event, ScreenControl screenControl, DbContext ctx) throws Exception;
}