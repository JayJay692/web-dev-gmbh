package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import java.util.ArrayList;
import java.util.List;
import de.abas.custom.owspart.utils.infosystem.AbstractInfosystemEventHandler;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart.Row;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart.Table;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;

public class StartButtonAfter extends AbstractInfosystemEventHandler<UsageReasonSparePart> {

	@Override
	protected void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head,
											de.abas.erp.db.infosystem.standard.BaseInfosystem.Row<? extends UsageReasonSparePart> currentRow) {
		Table table = head.table();
		table.clear();
		fill(table, selectSpareParts(ctx, head));
	}
	
	private void fill(Table table, List<Ersatzteile> spareParts) {
		for (Ersatzteile sparePart : spareParts) {
			appendRow(table, sparePart);
		}
	}

	private List<Ersatzteile> selectSpareParts(DbContext ctx, UsageReasonSparePart head) {
		List<Ersatzteile> selectedSpareParts = executeSelectSparePartsByHeadFields(ctx, head);
		return selectSparePartsByTableFields(selectedSpareParts, head);
//		return selectLastSparePartRows(selectedSpareParts);
	}
	
	private List<Ersatzteile> selectSparePartsByTableFields(List<Ersatzteile> selectedSpareParts, UsageReasonSparePart head) {
		return null;
	}

	private List<Ersatzteile> executeSelectSparePartsByHeadFields(DbContext ctx, UsageReasonSparePart head) {
		SelectionBuilder<Ersatzteile> sparePartSelectionBuilder = SelectionBuilder.create(Ersatzteile.class);
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.id, head.getYspartsparepart()));
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.yspartpart, head.getYspartproduct()));
//		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.Row.META.yspartusagereason, head.getYspartusagereason()));
//		sparePartSelectionBuilder.add(Conditions.between(Ersatzteile.Row.META.yspartusagedate, head.getYspartdatefrom(), head.getYspartdateto()));
		return ctx.createQuery(sparePartSelectionBuilder.build()).execute();
	}

	private List<Ersatzteile.Row> selectLastSparePartRows(List<Ersatzteile.Row> selectedSparePartRows) {
		ArrayList<Ersatzteile.Row> selectedLastSparePartRows = new ArrayList<Ersatzteile.Row>();
		for (Ersatzteile.Row sparePartRow : selectedSparePartRows) {
			if(isLastRowInSparePartTable(sparePartRow)) {
				selectedLastSparePartRows.add(sparePartRow);
			}
		}
		return selectedLastSparePartRows;
	}

	private boolean isLastRowInSparePartTable(Ersatzteile.Row sparePartRow) {
		return sparePartRow.getRowNo() == sparePartRow.header().getRowCount();
	}

	private void appendRow(Table table, de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile sparePart) {
		Row newRow = table.appendRow();
		newRow.setYsparttproduct(sparePart.getYspartpart());
		newRow.setYsparttnewdateuse(new AbasDate());
//		newRow.setYsparttdatelastuse(getDateFrom(sparePart));
//		newRow.setYsparttreasonlastu(getReasonFrom(sparePart));
		if(wasEverUsed(sparePart)){
			de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Row lastSparePartRow = selectLastRowFrom(sparePart.table());
			newRow.setYsparttdatelastuse(getDateFrom(lastSparePartRow));
			newRow.setYsparttreasonlastu(getReasonFrom(lastSparePartRow));
		}
	}

	private boolean wasEverUsed(Ersatzteile sparePart) {
		return sparePart.table().getRowCount() > 0;
	}

	private UserEnumUsageReason getReasonFrom(de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Row lastSparePartRow) {
		return lastSparePartRow.getYspartusagereason();
	}

	private AbasDate getDateFrom(de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Row lastSparePartRow) {
		return lastSparePartRow.getYspartusagedate();
	}

	private Ersatzteile.Row selectLastRowFrom(de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile.Table table) {
		return table.getRow(table.getRowCount());
	}
}
