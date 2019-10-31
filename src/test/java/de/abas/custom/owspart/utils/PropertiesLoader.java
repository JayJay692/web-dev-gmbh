package de.abas.custom.owspart.utils;

import de.abas.erp.db.*;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.exception.FieldAccessException;
import de.abas.erp.db.schema.company.Configuration;
import de.abas.erp.db.schema.company.ConfigurationEditor;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;
import de.abas.erp.db.util.ContextHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PropertiesLoader {
    public static DbContext ctx;

    static File configFile = new File("gradle.properties");
    static String host;
    static String client;
    static int port;
    static String password;

    public PropertiesLoader() {
        loadProperties();
    }

    static String message = "";

    public String getMessage() {
        return message;
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static void setConfigFile(File configFile) {
        PropertiesLoader.configFile = configFile;
    }

    public void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(configFile));
            getEdpProperties(properties, "EDP_HOST", "EDP_CLIENT", "EDP_PORT", "EDP_PASSWORD");
        } catch (final FileNotFoundException e) {
            throw new RuntimeException("Could not find configuration file " + configFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration file " + configFile.getAbsolutePath());
        }
    }

    public void getEdpProperties(Properties properties, String edpH, String edpC, String edpP, String edpPW) {
//		properties.load(new FileReader(configFile));
        host = properties.getProperty(edpH);
        client = properties.getProperty(edpC);
        port = Integer.parseInt(properties.getProperty(edpP));
        password = properties.getProperty(edpPW);
    }

    public DbContext getCtx() {
        loadProperties();
        ctx = ContextHelper.createClientContext(host, port, client, password, "abas-qm");
        return ctx;
    }

    public void addDefaultMessageListener() {
        ctx.addMessageListener(new MessageListener() {
            // Displays all text, status and error messages
            @Override
            public void receiveMessage(DbMessage dbMessage) {
                ctx.out().println("|" + dbMessage + "|");
                message = message + dbMessage + "\n";
            }
        });
    }

    public void updateErpConfigFile() {
        SelectionBuilder<Configuration> selectionBuilder = SelectionBuilder.create(Configuration.class);
        selectionBuilder.add(Conditions.eq("swd", "KONFIG"));
        Query<Configuration> query = ctx.createQuery(selectionBuilder.build());
        List<Configuration> execute = query.execute();
        Configuration configuration = execute.get(0);
        ConfigurationEditor configurationEditor = configuration.createEditor();
        try {
            configurationEditor.open(EditorAction.UPDATE);
            configurationEditor.setOperLangE(true);
            configurationEditor.setPDC(true);
            configurationEditor.setPDCOrderTimes(true);
            configurationEditor.commit();
        } catch (CommandException | FieldAccessException e) {
            e.printStackTrace();
        }finally {
            CodeTemplates.abortEditors(configurationEditor);
        }

    }

    public Configuration readConfiguration() {
        return new CodeTemplates().getSelectList(Configuration.class, "swd", "KONFIG", ctx).get(0);
    }
}
