package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import de.abas.custom.owspart.utils.infosystem.AbstractInfosystemEventHandler;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.standard.BaseInfosystem.Row;

public class SetUsageButtonAfter extends AbstractInfosystemEventHandler<UsageReasonSparePart>{

	@Override
	protected void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head, Row<? extends UsageReasonSparePart> currentRow) {
		// TODO issue #33 from Github - Verwendung in Ersatzteil wegschreiben und Plausiprüfung
		
	}

}
