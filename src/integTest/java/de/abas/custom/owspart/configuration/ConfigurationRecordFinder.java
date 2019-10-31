package de.abas.custom.owspart.configuration;

import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.Konfiguration;
import de.abas.erp.db.selection.SelectionBuilder;

public class ConfigurationRecordFinder {

    public static Konfiguration findRecord(DbContext context) {
        SelectionBuilder<Konfiguration> selectionBuilder = SelectionBuilder.create(Konfiguration.class);
        return context.createQuery(selectionBuilder.build()).execute().get(0);
    }
}
