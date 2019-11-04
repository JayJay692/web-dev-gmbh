package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.events.ScreenEvents;
import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ScreenEventsTest {

    @Test
    public void screenEnter() throws EventException {

        de.abas.erp.axi2.event.ScreenEvent screenEvent = mock(de.abas.erp.axi2.event.ScreenEvent.class);
        CodeTemplates codeTemplates = mock(CodeTemplates.class);
        when(codeTemplates.isEventNewOrCopy(screenEvent)).thenReturn(true);

        ScreenEvents screenEventsConfiguration = new ScreenEvents(screenEvent);
        screenEventsConfiguration.setCodeTemplates(codeTemplates);
        screenEventsConfiguration.screenEnter();

        verify(codeTemplates).actionIsProhibitedInGui();
    }
}