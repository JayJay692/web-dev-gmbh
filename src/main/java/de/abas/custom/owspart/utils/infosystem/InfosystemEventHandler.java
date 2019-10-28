package de.abas.custom.owspart.utils.infosystem;

import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.standard.BaseInfosystem;

public interface InfosystemEventHandler<T extends AbasObject> {

	void handleEvent(Event<? extends EventType> event, FopEventTypeToken fopEventTypeToken, ScreenControl screenControl, DbContext ctx, T head,
			BaseInfosystem.Row<? extends T> currentRow) throws Exception;

}
