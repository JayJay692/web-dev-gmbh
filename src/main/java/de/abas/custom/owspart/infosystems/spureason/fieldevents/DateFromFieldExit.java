package de.abas.custom.owspart.infosystems.spureason.fieldevents;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.infosystem.AbstractInfosystemEventHandler;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.standard.BaseInfosystem.Row;

public class DateFromFieldExit extends AbstractInfosystemEventHandler<UsageReasonSparePart> {

	@Override
	protected void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head, Row<? extends UsageReasonSparePart> currentRow) throws EventException {
		new CodeTemplates().validateDateRangeForInfosytem(head.getYspartdateto(), head.getYspartdatefrom());
	}
}
