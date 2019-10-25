package de.abas.custom.owspart.spart.configuration;

import de.abas.custom.owspart.spart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;

public class ScreenEventsConfiguration {

	DbContext ctx; 
	ScreenEvent event;
	
	public ScreenEventsConfiguration(DbContext ctx, ScreenEvent event) {
		this.ctx = ctx;
		this.event = event;
	}

	public void screenEnter() throws EventException {
		if (isEventNewOrCopy() ) {
			new CodeTemplates().actionIsProhibitedInGui();
		}
	}

	private boolean isEventNewOrCopy() {
		return EnumEditorAction.New.equals(event.getCommand()) || EnumEditorAction.Copy.equals(event.getCommand());
	}

}
