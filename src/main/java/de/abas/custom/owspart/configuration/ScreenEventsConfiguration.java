package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.esdk.DatabaseMetaData;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;

public class ScreenEventsConfiguration {

	private DbContext ctx;
	private ScreenEvent event;
	private KonfigurationEditor configurationEditor;
	private CodeTemplates codeTemplates = new CodeTemplates();
	private DatabaseMetaData databaseMetaData = new DatabaseMetaData();

	public ScreenEventsConfiguration(DbContext ctx, ScreenEvent event, KonfigurationEditor configurationEditor) {
		this.ctx = ctx;
		this.event = event;
		this.configurationEditor = configurationEditor;
	}

	public void screenEnter() throws EventException {
		if (codeTemplates.isEventNewOrCopy(event)) {
			codeTemplates.actionIsProhibitedInGui();
		}
		if(event.getCommand().equals(EnumEditorAction.Edit)) {
			this.configurationEditor.setYspartdbno(databaseMetaData.getDatabaseNumberAsString(this.configurationEditor.getDBNo()));
			this.configurationEditor.setYspartdbname(this.configurationEditor.getDBDescr());
		}
	}

	void setCodeTemplates(CodeTemplates codeTemplates) {
		this.codeTemplates = codeTemplates;
	}
}
