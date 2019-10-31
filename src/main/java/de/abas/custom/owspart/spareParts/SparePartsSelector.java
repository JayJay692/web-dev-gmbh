package de.abas.custom.owspart.spareParts;

import java.util.ArrayList;
import java.util.List;

import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;

public class SparePartsSelector {
	public List<Ersatzteile> selectBy(UsageReasonSparePart head, DbContext ctx) {
		List<Ersatzteile> selectedSpareParts = executeSelectSparePartsByHeadFields(ctx, head);
		return selectSparePartsByTableFields(selectedSpareParts, head);
	}
	
	private List<Ersatzteile> executeSelectSparePartsByHeadFields(DbContext ctx, UsageReasonSparePart head) {
		SelectionBuilder<Ersatzteile> sparePartSelectionBuilder = SelectionBuilder.create(Ersatzteile.class);
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.id, head.getYspartsparepart()));
		sparePartSelectionBuilder.add(Conditions.eq(Ersatzteile.META.yspartpart, head.getYspartproduct()));
		return ctx.createQuery(sparePartSelectionBuilder.build()).execute();
	}
	
	private List<Ersatzteile> selectSparePartsByTableFields(List<Ersatzteile> selectedSpareParts, UsageReasonSparePart head) {
		ArrayList<Ersatzteile> selectedSparePartsByTableFields = new ArrayList<Ersatzteile>();
		for (Ersatzteile sparePart : selectedSpareParts) {
			if(wasEverUsed(sparePart)) {
				Ersatzteile.Row row = sparePart.table().getRow(sparePart.getRowCount());
				if(isSelectedByInfosystem(row, head)){
					selectedSparePartsByTableFields.add(sparePart);
				}
			}else {
				selectedSparePartsByTableFields.add(sparePart);
			}
		}
		
		return selectedSparePartsByTableFields;
	}
	
	private boolean isSelectedByInfosystem(Ersatzteile.Row row, UsageReasonSparePart head) {
		return (head.getYspartusagereason().equals(UserEnumUsageReason.Empty) || row.getYspartusagereason().equals(head.getYspartusagereason())) 
				& (head.getYspartdatefrom() == null || (row.getYspartusagedate().toDate().getTime() >= head.getYspartdatefrom().toDate().getTime()))
				& (head.getYspartdateto() == null || (row.getYspartusagedate().toDate().getTime() <= head.getYspartdateto().toDate().getTime()));
	}
	
	private boolean wasEverUsed(Ersatzteile sparePart) {
		return sparePart.table().getRowCount() > 0;
	}
}
