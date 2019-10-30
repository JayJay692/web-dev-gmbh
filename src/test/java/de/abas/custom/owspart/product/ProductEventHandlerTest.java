package de.abas.custom.owspart.product;

import de.abas.custom.owspart.utils.PropertiesLoader;
import de.abas.erp.common.type.IdImpl;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.EditorAction;
import de.abas.erp.db.EditorObject;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductEventHandlerTest {

    public static final String CREATE_SPAREPART = "yspartcreatespart";
    private static PropertiesLoader propertiesLoader;
    private static DbContext databaseContext;
    private static ProductEditor productEditor;

    @BeforeClass
    public static void setup(){
        propertiesLoader = new PropertiesLoader();
        databaseContext = propertiesLoader.getCtx();
    }

    @Before
    public void setupEditor() throws CommandException {
        productEditor = databaseContext.load(ProductEditor.class, new IdImpl("(174,2,0)"));
        productEditor.open(EditorAction.COPY);
        productEditor.commitAndReopen();
    }

    @After
    public void tearDownEditor(){
        productEditor.commit();
    }

    @Test
    public void testClickOnCreateSparePartButtonChecksTheCheckBox(){
        productEditor.invokeButton(CREATE_SPAREPART);
        assertTrue(productEditor.getYspartspartcreated());
    }

    @Test
    public void testClickOnCreateSparePartButtonCreatesSparePart(){
        productEditor.invokeButton(CREATE_SPAREPART);
        assertEquals(1, createSelection(productEditor).size());
    }

    private List<Ersatzteile> createSelection(ProductEditor productEditor){
        SelectionBuilder<Ersatzteile> selectionBuilder = SelectionBuilder.create(Ersatzteile.class);
        selectionBuilder.add(Conditions.eq(Ersatzteile.META.yspartpart.toString(), productEditor.getIdno()));
        return databaseContext.createQuery(selectionBuilder.build()).execute();
    }

}
