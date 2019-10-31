package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import de.abas.custom.owspart.spareParts.SparePartUsageAppender;
import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.Event;
import de.abas.erp.axi2.type.EventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.userenums.UserEnumUsageReason;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SetUsageButtonAfterTest {

    @Mock
    private SparePartUsageAppender sparePartUsageAppender;

    @Mock
    private CodeTemplates codeTemplates;

    @Mock
    private UsageReasonSparePart.Row sparepartUsageRow;

    private SetUsageButtonAfter setUsageButtonAfter;

    @Before
    public void prepare(){
        setUsageButtonAfter = new SetUsageButtonAfter();
        setUsageButtonAfter.setCodeTemplates(codeTemplates);
        setUsageButtonAfter.setSparePartUsageAppender(sparePartUsageAppender);
    }

    @Test
    public void rowIsAppendedAndHintIsShownToTheUser() throws EventException, CommandException {
        when(sparepartUsageRow.getYsparttnewreasonus()).thenReturn(UserEnumUsageReason.DEFECT);

        setUsageButtonAfter.handleEventImpl(mock(Event.class), mock(ScreenControl.class), mock(DbContext.class), PowerMockito.mock(UsageReasonSparePart.class), sparepartUsageRow );

        verify(sparePartUsageAppender).appendTo(any(), any(), any());
        verify(codeTemplates).displayNewTextBox(anyString(), any());
    }

    @Test
    public void hintIsShownWhenSparepartUsageIsEmpty() throws EventException, CommandException {
        when(sparepartUsageRow.getYsparttnewreasonus()).thenReturn(UserEnumUsageReason.Empty);

        setUsageButtonAfter.handleEventImpl(mock(Event.class), mock(ScreenControl.class), mock(DbContext.class), PowerMockito.mock(UsageReasonSparePart.class), sparepartUsageRow );

        verify(sparePartUsageAppender, times(0)).appendTo(any(), any(), any());
        verify(codeTemplates).displayNewTextBox(anyString(), any());
    }
}