package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.esdk.DatabaseMetaData;
import de.abas.custom.owspart.utils.esdk.UCMFileProcessor;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;

public class ButtonEventsConfiguration {

	public ButtonEventsConfiguration(DbContext ctx, KonfigurationEditor configurationEditor) {
		this.ctx = ctx;
		this.configurationEditor = configurationEditor;
	}

	private DbContext ctx;
	private KonfigurationEditor configurationEditor;
	private UCMFileProcessor ucmFileProcessor = new UCMFileProcessor();
	private CodeTemplates codeTemplates = new CodeTemplates();
	private DatabaseMetaData databaseMetaData = new DatabaseMetaData();

	void finishConfigButtonAfter() throws EventException {
		try {
			String databaseCommand = databaseMetaData.getDatabaseCommand(ctx, configurationEditor.getDBNo());
			ucmFileProcessor.replaceCommandInUCMFile(databaseCommand);
			codeTemplates.displayNewTextBox("Konfiguration abgeschlossen", ctx);
			this.configurationEditor.setYspartdbcomname(databaseCommand);
		} catch (RuntimeException e) {
			throw codeTemplates.createEventException(e.getMessage());
		}
	}

	void setCodeTemplates(CodeTemplates codeTemplates) {
		this.codeTemplates = codeTemplates;
	}

	void setDatabaseMetaData(DatabaseMetaData databaseMetaData) {
		this.databaseMetaData = databaseMetaData;
	}

	void setUcmFileProcessor(UCMFileProcessor ucmFileProcessor) {
		this.ucmFileProcessor = ucmFileProcessor;
	}
}
