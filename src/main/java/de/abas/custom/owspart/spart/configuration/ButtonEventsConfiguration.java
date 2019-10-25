package de.abas.custom.owspart.spart.configuration;

import de.abas.custom.owspart.spart.utils.CodeTemplates;
import de.abas.custom.owspart.spart.utils.esdk.AbasFilesFactoryTODO;
import de.abas.custom.owspart.spart.utils.esdk.DatabaseMetaData;
import de.abas.custom.owspart.spart.utils.esdk.EsdkProperties;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;

public class ButtonEventsConfiguration {

	public ButtonEventsConfiguration(DbContext ctx, KonfigurationEditor configurationEditor) {
		this.ctx = ctx;
		this.configurationEditor = configurationEditor;
	}

	DbContext ctx;
	KonfigurationEditor configurationEditor;
	AbasFilesFactoryTODO ucmFileFactory = new AbasFilesFactoryTODO();
	CodeTemplates codeTemplates = new CodeTemplates();

	void finishConfigButtonAfter() throws EventException {
		try {
			String databaseCommand = new DatabaseMetaData().getDatabaseCommand(ctx, configurationEditor.getDBNo());
			ucmFileFactory.replaceContentInFile(EsdkProperties.getUcmFile(), EsdkProperties.getUcmPlaceHolder(),
					databaseCommand, "UTF16");
			codeTemplates.displayNewTextBox("Konfiguration abgeschlossen", ctx);
		} catch (RuntimeException e) {
			codeTemplates.throwEventException(e.getMessage());
		}
	}
}
