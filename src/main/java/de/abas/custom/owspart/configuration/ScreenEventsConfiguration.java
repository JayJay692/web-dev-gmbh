package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;

public class ScreenEventsConfiguration {

	DbContext ctx;
	ScreenEvent event;
	CodeTemplates codeTemplate = new CodeTemplates();

	public ScreenEventsConfiguration(DbContext ctx, ScreenEvent event) {
		this.ctx = ctx;
		this.event = event;
	}

	public void screenEnter() throws EventException {
		if (codeTemplate.isEventNewOrCopy(event)) {
			codeTemplate.actionIsProhibitedInGui();
		}
	}

}
