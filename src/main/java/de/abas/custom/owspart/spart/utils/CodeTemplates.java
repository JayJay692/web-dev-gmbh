package de.abas.custom.owspart.spart.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.abas.erp.api.gui.TextBox;
import de.abas.erp.axi.event.EventException;
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
			throw new EventException("nicht erlaubt",1);			
		}
	}
	
	public void throwEventException(String errorText) throws EventException {
		if (errorText != "") {
			throw new EventException(errorText,EXIT_ERROR_CODE);
		}
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
}
