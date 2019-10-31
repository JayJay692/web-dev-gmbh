package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ScreenEventsConfigurationTest {

    @Test
    public void screenEnter() throws EventException {

        ScreenEvent screenEvent = mock(ScreenEvent.class);
        CodeTemplates codeTemplates = mock(CodeTemplates.class);
        when(codeTemplates.isEventNewOrCopy(screenEvent)).thenReturn(true);

        ScreenEventsConfiguration screenEventsConfiguration = new ScreenEventsConfiguration(mock(DbContext.class), screenEvent);
        screenEventsConfiguration.setCodeTemplates(codeTemplates);
        screenEventsConfiguration.screenEnter();

        verify(codeTemplates).actionIsProhibitedInGui();
    }
}