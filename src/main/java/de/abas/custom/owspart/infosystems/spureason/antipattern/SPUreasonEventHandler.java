package de.abas.custom.owspart.infosystems.spureason.antipattern;

import java.util.ArrayList;
import java.util.List;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart.Table;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Row;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@EventHandler(head = UsageReasonSparePart.class, row = UsageReasonSparePart.Row.class)

@RunFopWith(EventHandlerRunner.class)

public class SPUreasonEventHandler {

	
	
	/*NEGATIVBEISPIEL HIER**/
	
	
	
	
	@ButtonEventHandler(field = "start", type = ButtonEventType.AFTER)
	public void startAfter(Event<ButtonEventType> event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) {
		Table table = head.table();
		table.clear();
		
		SelectionBuilder<Ersatzteile> sparePartSelectionBuilder = SelectionBuilder.create(Ersatzteile.class);
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.id, head.getYspartsparepart()));
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.yspartpart, head.getYspartproduct()));
		List<Ersatzteile> selectedSpareParts = ctx.createQuery(sparePartSelectionBuilder.build()).execute();
		ArrayList<Ersatzteile> selectedSparePartsByTableFields = new ArrayList<Ersatzteile>();
		for (Ersatzteile sparePart : selectedSpareParts) {
			if(sparePart.table().getRowCount() > 0) {
				Ersatzteile.Row row = sparePart.table().getRow(sparePart.getRowCount());
				if(isSelectedByInfosystem(row, head)){
					selectedSparePartsByTableFields.add(sparePart);
				}
			}else {
				selectedSparePartsByTableFields.add(sparePart);
			}
		}
		
		for (Ersatzteile sparePart : selectedSparePartsByTableFields) {
			UsageReasonSparePart.Row newRow = table.appendRow();
			newRow.setYsparttsparepart(sparePart);
			newRow.setYsparttnamespart(sparePart);
			newRow.setYsparttproduct(sparePart.getYspartpart());
			newRow.setYsparttnewdateuse(new AbasDate());
			if(sparePart.table().getRowCount() > 0){
				de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Row lastSparePartRow = sparePart.table().getRow(table.getRowCount());
				newRow.setYsparttdatelastuse(lastSparePartRow.getYspartusagedate());
				newRow.setYsparttreasonlastu(lastSparePartRow.getYspartusagereason());
			}
		}
	}

	private boolean isSelectedByInfosystem(Row row, UsageReasonSparePart head) {
		return (head.getYspartusagereason().equals(UserEnumUsageReason.Empty) || row.getYspartusagereason().equals(head.getYspartusagereason())) 
				& (head.getYspartdatefrom() == null || (row.getYspartusagedate().toDate().getTime() >= head.getYspartdatefrom().toDate().getTime()))
				& (head.getYspartdateto() == null || (row.getYspartusagedate().toDate().getTime() <= head.getYspartdateto().toDate().getTime()));
	}

	@FieldEventHandler(field = "yspartdatefrom", type = FieldEventType.EXIT)
	public void yspartdatefromExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws EventException {
		if (head.getYspartdateto() == null || head.getYspartdatefrom() == null) {
			return;
		}
		if (head.getYspartdateto().toDate().before(head.getYspartdatefrom().toDate())) {
			throw new EventException("Bis Datum muss größer oder gleich sein als Von Datum", 1);
		}
	}

	@FieldEventHandler(field = "yspartdateto", type = FieldEventType.EXIT)
	public void yspartdatetoExit(FieldEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head) throws EventException {
		if (head.getYspartdateto() == null || head.getYspartdatefrom() == null) {
			return;
		}
		if (head.getYspartdateto().toDate().before(head.getYspartdatefrom().toDate())) {
			throw new EventException("Bis Datum muss größer oder gleich sein als Von Datum", 1);
		}
	}

	@ButtonEventHandler(field = "ysparttsetusage", type = ButtonEventType.AFTER, table = true)
	public void ysparttsetusageAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			UsageReasonSparePart head, UsageReasonSparePart.Row currentRow) throws EventException {
	
	}

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head)
			throws EventException {
		// TODO Auto-generated method stub
	}

}
