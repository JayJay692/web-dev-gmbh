package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.esdk.DatabaseMetaData;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KonfigurationEditor.class)
public class ScreenEventsConfigurationTest {

    @Test
    public void screenEnter() throws EventException {

        ScreenEvent screenEvent = mock(ScreenEvent.class);
        CodeTemplates codeTemplates = mock(CodeTemplates.class);
        DatabaseMetaData mockDatabaseMeta = mock(DatabaseMetaData.class);
        when(codeTemplates.isEventNewOrCopy(screenEvent)).thenReturn(true);

        ScreenEventsConfiguration screenEventsConfiguration = new ScreenEventsConfiguration(mock(DbContext.class), screenEvent, PowerMockito.mock(KonfigurationEditor.class));
        screenEventsConfiguration.setCodeTemplates(codeTemplates);
        screenEventsConfiguration.setDatabaseMetaData(mockDatabaseMeta);
        screenEventsConfiguration.screenEnter();

        verify(codeTemplates).actionIsProhibitedInGui();
    }
}