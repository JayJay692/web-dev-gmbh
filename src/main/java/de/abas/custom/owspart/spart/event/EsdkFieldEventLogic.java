package de.abas.custom.owspart.spart.event;

import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public interface EsdkFieldEventLogic<T extends AbasObject> {
	void execute(T head, FieldEvent event, ScreenControl screenControl, DbContext ctx) throws Exception;
}
