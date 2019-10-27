package de.abas.custom.owspart.spareParts;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;

public class ScreenEventsSpareParts {
	DbContext ctx; 
	ScreenEvent event;
	CodeTemplates codeTemplates = new CodeTemplates();
	
	public ScreenEventsSpareParts(DbContext ctx, ScreenEvent event) {
		this.ctx = ctx;
		this.event = event;
	}
	
	
	// TODO: ist 1:1 so in ScreenEventsConfiguration, kann zusammen gefasst werden
	public void screenEnter() throws EventException {
		if (codeTemplates.isEventNewOrCopy(event) ) {
			codeTemplates.actionIsProhibitedInGui();
		}
	}

}
