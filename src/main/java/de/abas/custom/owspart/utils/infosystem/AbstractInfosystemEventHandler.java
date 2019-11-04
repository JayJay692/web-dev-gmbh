package de.abas.custom.owspart.utils.infosystem;

import de.abas.custom.owspart.utils.esdk.CustomizationAccess;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.event.ScreenElementEvent;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.SelectableRow;

public abstract class AbstractInfosystemEventHandler<T extends AbasObject, R extends SelectableRow> {

	protected CustomizationAccess customizationAccess;

	public void handleEvent(Event<? extends EventType> event, FopEventTypeToken fopEventTypeToken, ScreenControl screenControl, DbContext ctx, T head,
							R currentRow) throws Exception {
		customizationAccess = new CustomizationAccess(fopEventTypeToken, getFieldName(event));
//		deaktiviert wegen nicht lizenziertem Docker
//		RuntimerLicenseChecker.validateLicense();
		customizationAccess.handleCustomFops(head, ctx, "before");
		handleEventImpl(event, screenControl, ctx, head, currentRow);
		customizationAccess.handleCustomFops(head, ctx, "after");
	}

	private String getFieldName(Event<? extends EventType> event) {
		if (event instanceof ScreenElementEvent) {
			ScreenElementEvent<? extends EventType> screenElementEvent = (ScreenElementEvent<? extends EventType>) event;
			return screenElementEvent.getFieldName();
		}
		return "";
	}

	protected abstract void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl,
											DbContext ctx, T head, R currentRow)
			throws Exception;

}
