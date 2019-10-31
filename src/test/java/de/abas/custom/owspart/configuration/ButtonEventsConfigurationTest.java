package de.abas.custom.owspart.configuration;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.esdk.DatabaseMetaData;
import de.abas.custom.owspart.utils.esdk.UCMFileProcessor;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KonfigurationEditor.class)
public class ButtonEventsConfigurationTest {

    @Mock
    UCMFileProcessor ucmFileProcessor;

    @Mock
    DatabaseMetaData databaseMetaData;

    @Mock
    CodeTemplates codeTemplates;

    private ButtonEventsConfiguration buttonEventsConfiguration;

    private String testDbCommand = "test";

    @Before
    public void prepare() {
        when(codeTemplates.createEventException(any())).thenCallRealMethod();
        when(databaseMetaData.getDatabaseCommand(any(), any())).thenReturn(testDbCommand);
        buttonEventsConfiguration = new ButtonEventsConfiguration(mock(DbContext.class), PowerMockito.mock(KonfigurationEditor.class));
        buttonEventsConfiguration.setUcmFileProcessor(ucmFileProcessor);
        buttonEventsConfiguration.setCodeTemplates(codeTemplates);
        buttonEventsConfiguration.setDatabaseMetaData(databaseMetaData);
    }

    @Test
    public void finishConfigButtonAfter() throws EventException {

        buttonEventsConfiguration.finishConfigButtonAfter();

        verify(ucmFileProcessor).replaceCommandInUCMFile(anyString());
        verify(codeTemplates).displayNewTextBox(anyString(), any());
    }

    @Test(expected = EventException.class)
    public void finishConfigButtonHandlesExceptionCorrectly() throws EventException {
        doThrow(new RuntimeException()).when(ucmFileProcessor).replaceCommandInUCMFile(testDbCommand);
        buttonEventsConfiguration.finishConfigButtonAfter();
    }
}