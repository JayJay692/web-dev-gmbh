package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import de.abas.custom.owspart.spareParts.SparePartUsageAppender;
import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.infosystem.AbstractInfosystemEventHandler;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.standard.BaseInfosystem.Row;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;

public class SetUsageButtonAfter extends AbstractInfosystemEventHandler<UsageReasonSparePart, UsageReasonSparePart.Row> {

	private SparePartUsageAppender sparePartUsageAppender = new SparePartUsageAppender();
	private CodeTemplates codeTemplates = new CodeTemplates();

	@Override
	protected void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx,
								   UsageReasonSparePart head, UsageReasonSparePart.Row currentRow) throws EventException {

		if (isSparepartUsageEmptyIn(currentRow)) {
			showNoUsageReasonHint(ctx);
			return;
		}
		addSparepartUsageFor(currentRow);
		showSuccessHint(ctx);
	}

	private boolean isSparepartUsageEmptyIn(UsageReasonSparePart.Row row) {
		return UserEnumUsageReason.Empty == row.getYsparttnewreasonus();
	}

	private void addSparepartUsageFor(UsageReasonSparePart.Row row) throws EventException {
		try {
			sparePartUsageAppender.appendTo(row.getYsparttsparepart(), row.getYsparttnewdateuse(), row.getYsparttnewreasonus());
		} catch (CommandException e) {
			throw codeTemplates.createEventException("Ersatzteil konnte nicht angelegt werden");
		}
	}

	private void showSuccessHint(DbContext ctx) {
		codeTemplates.displayNewTextBox("Verwendung erfolgreich angelegt.", ctx);
	}

	private void showNoUsageReasonHint(DbContext ctx) {
		codeTemplates.displayNewTextBox("Achtung! Bitte Verwendungsgrund angeben!", ctx);
	}

	void setSparePartUsageAppender(SparePartUsageAppender sparePartUsageAppender){
		this.sparePartUsageAppender = sparePartUsageAppender;
	}

	void setCodeTemplates(CodeTemplates codeTemplates){
		this.codeTemplates = codeTemplates;
	}
}
