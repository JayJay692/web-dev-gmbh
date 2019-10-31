package de.abas.custom.owspart.spareParts;

import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.EditorAction;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;

public class SparePartUsageAppender {

    public void appendTo(Ersatzteile sparepart, AbasDate usageTime, UserEnumUsageReason reason) throws CommandException {

        ErsatzteileEditor editor = sparepart.createEditor();
        editor.open(EditorAction.UPDATE);
        ErsatzteileEditor.Row newRow = editor.table().appendRow();
        newRow.setYspartusagedate(usageTime);
        newRow.setYspartusagereason(reason);
        editor.commit();
        if(editor.active()){
            editor.abort();
        }
    }
}
