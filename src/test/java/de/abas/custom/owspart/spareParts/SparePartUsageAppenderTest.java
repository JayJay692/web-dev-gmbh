package de.abas.custom.owspart.spareParts;

import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ErsatzteileEditor.class})
public class SparePartUsageAppenderTest {

    @Test
    public void appendTo() throws CommandException {

        ErsatzteileEditor ersatzteileEditor = PowerMockito.mock(ErsatzteileEditor.class);
        ErsatzteileEditor.Table mockTable = mock(ErsatzteileEditor.Table.class);
        ErsatzteileEditor.Row mockRow = mock(ErsatzteileEditor.Row.class);
        when(mockTable.appendRow()).thenReturn(mockRow);

        Ersatzteile mockErsatzteile = mock(Ersatzteile.class);
        when(mockErsatzteile.createEditor()).thenReturn(ersatzteileEditor);
        when(ersatzteileEditor.table()).thenReturn(mockTable);

        AbasDate abasDate = new AbasDate();
        UserEnumUsageReason userEnumUsageReason = UserEnumUsageReason.DEFECT;

        SparePartUsageAppender sparePartUsageAppender = new SparePartUsageAppender();
        sparePartUsageAppender.appendTo(mockErsatzteile, new AbasDate(), UserEnumUsageReason.DEFECT);

        verify(mockRow).setYspartusagedate(abasDate);
        verify(mockRow).setYspartusagereason(userEnumUsageReason);
        verify(ersatzteileEditor).commit();
    }
}