package de.abas.custom.owsp.spart.event;

import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public interface EsdkScreenEventLogic<T extends AbasObject> {
	void execute(T head, ScreenEvent event, ScreenControl screenControl, DbContext ctx) throws Exception;
}
