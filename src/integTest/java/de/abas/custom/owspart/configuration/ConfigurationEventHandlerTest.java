package de.abas.custom.owspart.configuration;

import de.abas.erp.db.EditorAction;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.schema.custom.ersatzteileapp.Konfiguration;
import de.abas.erp.db.schema.custom.ersatzteileapp.KonfigurationEditor;
import de.abas.esdk.test.util.EsdkIntegTest;
import org.junit.Test;

public class ConfigurationEventHandlerTest extends EsdkIntegTest {

    @Test
    public void finishConfigurationCanBeInvoked() throws CommandException {
        Konfiguration configuration = ConfigurationRecordFinder.findRecord(ctx);
        KonfigurationEditor editor = configuration.createEditor();
        try {
            editor.open(EditorAction.UPDATE);
            editor.invokeYspartfinishconfig();
        } finally {
            if (editor.active()) {
                editor.abort();
            }
        }
    }
}
