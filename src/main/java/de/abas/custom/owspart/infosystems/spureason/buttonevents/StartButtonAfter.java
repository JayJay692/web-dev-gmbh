package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import java.util.List;
import de.abas.custom.owspart.spareParts.SparePartsSelector;
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

public class StartButtonAfter extends AbstractInfosystemEventHandler<UsageReasonSparePart> {
	private SparePartsSelector sparePartsSelector = new SparePartsSelector();
	
	@Override
	protected void handleEventImpl(Event<? extends EventType> event, ScreenControl screenControl, DbContext ctx, UsageReasonSparePart head,
											de.abas.erp.db.infosystem.standard.BaseInfosystem.Row<? extends UsageReasonSparePart> currentRow) {
		Table table = head.table();
		table.clear();
		fill(table, sparePartsSelector.selectBy(head, ctx));
	}
	
	private void fill(Table table, List<Ersatzteile> spareParts) {
		for (Ersatzteile sparePart : spareParts) {
			appendRow(table, sparePart);
		}
	}

	private void appendRow(Table table, de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile sparePart) {
		Row newRow = table.appendRow();
		newRow.setYsparttsparepart(sparePart);
		newRow.setYsparttnamespart(sparePart);
		newRow.setYsparttproduct(sparePart.getYspartpart());
		newRow.setYsparttnewdateuse(new AbasDate());
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
	
	public void setSparePartsSelector(SparePartsSelector sparePartsSelector) {
		this.sparePartsSelector = sparePartsSelector;
	}
}
