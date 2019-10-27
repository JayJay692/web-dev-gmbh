package de.abas.custom.owspart.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.abas.erp.api.gui.TextBox;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.EditorObject;
import de.abas.erp.db.Query;
import de.abas.erp.db.SelectableObject;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;

public class CodeTemplates {

	private static final int EXIT_ERROR_CODE = 1;
	SystemInformation systemInformation = new SystemInformation();

	public static void abortEditors(EditorObject editor) {
		if (editor != null && editor.active()) {
			editor.abort();
		}
	}

	public void displayNewTextBox(String boxMessage, DbContext ctx) {
		if (!systemInformation.getEdpMode()) {
			new TextBox(ctx, "Hinweis", boxMessage).show();
		}
	}

	public void actionIsProhibitedInGui() throws EventException {
		if (!systemInformation.getEdpMode()) {
			throw createEventException("nicht erlaubt");
		}
	}

	public EventException createEventException(String errorText) throws EventException {
		return new EventException(errorText, EXIT_ERROR_CODE);
	}

	public boolean isEventNewOrCopy(Event<? extends EventType> event) {
		return EnumEditorAction.New.equals(event.getCommand()) || EnumEditorAction.Copy.equals(event.getCommand());
	}

	public <C extends SelectableObject> List<C> getSelectList(Class<C> type, String field, String value,
			DbContext ctx) {
		SelectionBuilder<C> selectionBuilder = SelectionBuilder.create(type);
		selectionBuilder.add(Conditions.eq(field, value));
		Query<C> query = ctx.createQuery(selectionBuilder.build());
		List<C> execute = query.execute();
		return execute;
	}

	public Date addDaysToDate(int addedDays, Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, addedDays);
		return calendar.getTime();
	}

	public void validateDateRangeForInfosytem(AbasDate abasDateTo, AbasDate abasDateFrom) throws EventException {
		if (null == abasDateTo || null == abasDateFrom) {
			return;
		}
		if (abasDateTo.toDate().before(abasDateFrom.toDate())) {
			throw createEventException("Bis Datum muss größer oder gleich sein als Von Datum");
		}
	}
}
