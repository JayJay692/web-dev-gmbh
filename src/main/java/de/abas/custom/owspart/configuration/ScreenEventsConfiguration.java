package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;

public class ScreenEventsConfiguration {

	private DbContext ctx;
	private ScreenEvent event;
	private CodeTemplates codeTemplates = new CodeTemplates();

	public ScreenEventsConfiguration(DbContext ctx, ScreenEvent event) {
		this.ctx = ctx;
		this.event = event;
	}

	public void screenEnter() throws EventException {
		if (codeTemplates.isEventNewOrCopy(event)) {
			codeTemplates.actionIsProhibitedInGui();
		}
	}

	void setCodeTemplates(CodeTemplates codeTemplates) {
		this.codeTemplates = codeTemplates;
	}
}
