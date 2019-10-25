package de.abas.custom.owspart.spart.spareParts;

import de.abas.custom.owspart.spart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;

public class ScreenEventsSpareParts {
	DbContext ctx; 
	ScreenEvent event;
	
	public ScreenEventsSpareParts(DbContext ctx, ScreenEvent event) {
		this.ctx = ctx;
		this.event = event;
	}
	
	
	// TODO: ist 1:1 so in ScreenEventsConfiguration, kann zusammen gefasst werden
	public void screenEnter() throws EventException {
		if (isEventNewOrCopy() ) {
			new CodeTemplates().actionIsProhibitedInGui();
		}
	}

	private boolean isEventNewOrCopy() {
		return EnumEditorAction.New.equals(event.getCommand()) || EnumEditorAction.Copy.equals(event.getCommand());
	}
}
