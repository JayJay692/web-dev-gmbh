package de.abas.custom.owspart.infosystem.spureason;

import de.abas.erp.db.EditorAction;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import de.abas.esdk.test.util.EsdkIntegTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsageReasonSparePartEventHandlerTest extends EsdkIntegTest {

    private Ersatzteile sparepart;

    @Before
    public void prepareSparepart() throws CommandException {
        sparepart = SparePartFactory.createTestSparepart(ctx);
    }

    @Test
    public void canCreateNewRowInSparepart() throws CommandException {
        int rowCountBefore = sparepart.getRowCount();
        addUsageReasonViaInfosystem(UserEnumUsageReason.DEFECT);
        assertEquals(rowCountBefore + 1, getUpdatedRowCount());
    }

    @Test
    public void cannotCreateNewRowInSparepartWhenReasonIsEmpty() throws CommandException {
        int rowCountBefore = sparepart.getRowCount();
        addUsageReasonViaInfosystem(UserEnumUsageReason.Empty);
        assertEquals(rowCountBefore, getUpdatedRowCount());
    }

    private void addUsageReasonViaInfosystem(UserEnumUsageReason userEnumUsageReason) {
        UsageReasonSparePart usageReasonSparePart = ctx.openInfosystem(UsageReasonSparePart.class);
        try {
            usageReasonSparePart.setYspartsparepart(sparepart);
            usageReasonSparePart.invokeStart();
            usageReasonSparePart.table().getRows().get(0).setYsparttnewreasonus(userEnumUsageReason);
            usageReasonSparePart.table().getRows().get(0).invokeYsparttsetusage();
        } finally {
            if (usageReasonSparePart.active()) {
                usageReasonSparePart.abort();
            }
        }
    }

    private int getUpdatedRowCount() throws CommandException {
        ErsatzteileEditor editor = sparepart.createEditor();
        editor.open(EditorAction.VIEW);
        int newRowCount = editor.getRowCount();
        editor.abort();
        return newRowCount;
    }
}
