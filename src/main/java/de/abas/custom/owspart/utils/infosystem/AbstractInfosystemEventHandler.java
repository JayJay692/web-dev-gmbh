package de.abas.custom.owspart.utils.infosystem;

import de.abas.custom.owspart.utils.esdk.CustomizationAccess;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.event.ScreenElementEvent;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.AbasObject;
import de.abas.erp.db.DbContext;

public abstract class AbstractInfosystemEventHandler<T extends AbasObject> implements InfosystemEventHandler<T> {

	protected CustomizationAccess customizationAccess;

	public AbstractInfosystemEventHandler() {
		customizationAccess = new CustomizationAccess();
	}

	@Override
	public void handleEvent(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx, T head,
			de.abas.erp.db.infosystem.standard.BaseInfosystem.Row<? extends T> currentRow) throws Exception {
//		deaktiviert wegen nicht lizenziertem Docker
//		RuntimerLicenseChecker.validateLicense();
		customizationAccess.handleCustomFops(head, ctx, event.getType().toString(), getFieldName(event), "before");
		handleEventImpl(event, screenControl, ctx, head, currentRow);
		customizationAccess.handleCustomFops(head, ctx, event.getType().toString(), getFieldName(event), "after");
	}

	private String getFieldName(Event<? extends EventType> event) {
		if (event instanceof ScreenElementEvent) {
			ScreenElementEvent<? extends EventType> screenElementEvent = (ScreenElementEvent<? extends EventType>) event;
			return screenElementEvent.getFieldName();
		}
		return "";
	}

	protected abstract void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl,
			DbContext ctx, T head, de.abas.erp.db.infosystem.standard.BaseInfosystem.Row<? extends T> currentRow)
			throws Exception;

}
