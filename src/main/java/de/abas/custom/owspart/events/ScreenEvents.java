package de.abas.custom.owspart.events;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;

public class ScreenEvents {

	private de.abas.erp.axi2.event.ScreenEvent event;
	private CodeTemplates codeTemplates = new CodeTemplates();

	public ScreenEvents(de.abas.erp.axi2.event.ScreenEvent event) {
		this.event = event;
	}

	public void screenEnter() throws EventException {
		if (codeTemplates.isEventNewOrCopy(event)) {
			codeTemplates.actionIsProhibitedInGui();
		}
	}

	public void setCodeTemplates(CodeTemplates codeTemplates) {
		this.codeTemplates = codeTemplates;
	}
}
